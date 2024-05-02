package com.assetmanagement.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assetmanagement.controller.EmployeeController;
import com.assetmanagement.dto.EmployeeData;
import com.assetmanagement.dto.LoginRequestData;
import com.assetmanagement.dto.LoginResponse;
import com.assetmanagement.exception.EmailAlreadyExistsException;
import com.assetmanagement.model.EmployeeModel;
import com.assetmanagement.model.RoleModel;
import com.assetmanagement.repository.EmployeeRepository;
import com.assetmanagement.repository.RoleRepository;
import com.assetmanagement.service.EmployeeService;
import com.assetmanagement.utils.JwtUtil;
import org.springframework.core.io.Resource;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EmployeeDetailsServiceImpl employeeDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("csvJob")
	private Job job;

	@Override
	public EmployeeData createUser(EmployeeData employeeData) {
		if (employeeRepository.existsByEmailId(employeeData.getEmailId())) {
			return null;
		}
		EmployeeModel employeeModel = new EmployeeModel();
		BeanUtils.copyProperties(employeeData, employeeModel);
		String hashPassword = passwordEncoder.encode(employeeData.getPassword());
		employeeModel.setPassword(hashPassword);
		Optional<RoleModel> defaultRole = roleRepository.findByName("ROLE_USER");
		if (defaultRole != null) {
			employeeModel.getRoles().add(defaultRole.get());
		}
		employeeRepository.save(employeeModel);
		BeanUtils.copyProperties(employeeModel, employeeData);
		return employeeData;
	}

	@Override
	public void fetchByEmployeeId(String id) {
		Optional<EmployeeModel> findById = employeeRepository.findById(null);
	}

	@Override
	public void createEmployee(EmployeeData employeeData) {
		if (employeeData == null) {
			throw new IllegalArgumentException("Employee data cannot be null");
		}
		employeeRepository.findByEmailId(employeeData.getEmailId()).ifPresent(existingUser -> {
			throw new EmailAlreadyExistsException("Email ID already exist for employee");
		});
		EmployeeModel employeeModel = new EmployeeModel();
		BeanUtils.copyProperties(employeeData, employeeModel);
		String hashPassword = passwordEncoder.encode(employeeData.getPassword());
		employeeModel.setPassword(hashPassword);
		Optional<RoleModel> defaultRole = roleRepository.findByName("ROLE_USER");
		if (defaultRole != null) {
			employeeModel.getRoles().add(defaultRole.get());
		}
		employeeRepository.save(employeeModel);
	}

	@Override
	public LoginResponse authenticateGenerateToken(LoginRequestData employeeData) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(employeeData.getEmailId(), employeeData.getPassword()));
			// authenticate.getPrincipal();
			final UserDetails employeeDetails = employeeDetailsServiceImpl
					.loadUserByUsername(employeeData.getEmailId());
			final String jwt = jwtUtil.generateToken(employeeDetails.getUsername(), employeeDetails.getAuthorities());
			List<String> role = employeeDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
			return new LoginResponse(employeeDetails.getUsername().toString(), role, jwt);
		} catch (BadCredentialsException e) {
			logger.error("Login failed for user: {} the {}", employeeData.getEmailId(), e.getMessage());
			throw new BadCredentialsException("Login failed for employee");
		}
	}

	@Override
	public String importEmployeeData(MultipartFile fileData) {
		try {

			// Get the resource directory path
			Resource resourceDir = resourceLoader.getResource("classpath:");
			Path resourcePath = Paths.get(resourceDir.getURI());

			// Create a new file in the resource directory
			Path filePath = resourcePath.resolve(fileData.getOriginalFilename());
			Files.copy(fileData.getInputStream(), filePath);

			JobParameters jobParameters = new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis())
					// .addString("fileData", new String(fileData.getBytes()))
					.toJobParameters();
			JobExecution run = jobLauncher.run(job, jobParameters);
			return run.getStatus().toString();
		} catch (Exception e) {
			throw new RuntimeException("Failed to start batch job: " + e.getMessage(), e);
		}
	}

	@Override
	public List<EmployeeData> getEmployeeList() {
		List<EmployeeModel> employeeModels = employeeRepository.findAllEmployee();
		List<EmployeeData> employeeDataList = new ArrayList<>();

		for (EmployeeModel employeeModel : employeeModels) {
			EmployeeData employeeData = new EmployeeData();
			BeanUtils.copyProperties(employeeModel, employeeData);
			employeeDataList.add(employeeData);
		}

		return employeeDataList;
	}

	@Override
	public void deleteEmployee(List<String> employeeIds) {
		employeeRepository.disableEmployes(employeeIds);
	}

	@Override
	public EmployeeData getEmployeeById(String employeeId) {
		Optional<EmployeeModel> employeeModel = employeeRepository.findByEmployeeId(employeeId);
		EmployeeData employeeData = new EmployeeData();
		BeanUtils.copyProperties(employeeModel.get(), employeeData);
		return employeeData;
	}

	@Override
	public void updateEmployee(EmployeeData employeeData) {
		EmployeeModel existingEmployee = employeeRepository.findByEmployeeId(employeeData.getEmployeeId())
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		BeanUtils.copyProperties(employeeData, existingEmployee);
		employeeRepository.save(existingEmployee);
	}

}
