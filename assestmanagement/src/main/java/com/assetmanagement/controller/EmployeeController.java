package com.assetmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assetmanagement.constant.AssetManagementConstants;
import com.assetmanagement.dto.EmployeeData;
import com.assetmanagement.dto.ErrorResponseDto;
import com.assetmanagement.dto.ResponseDto;
import com.assetmanagement.service.EmployeeService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// private static final Logger logger =
	// LoggerFactory.getLogger(EmployeeController.class);

	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> createEmployee(@RequestBody EmployeeData employeeData) {
		employeeService.createEmployee(employeeData);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AssetManagementConstants.STATUS_201, AssetManagementConstants.MESSAGE_201));
	}

	@RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeData>> getEmployeeList() {
		List<EmployeeData> employeeList = employeeService.getEmployeeList();
		return ResponseEntity.ok().body(employeeList);
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> deleteEmployee(@RequestBody List<String> employeeIds) {
		employeeService.deleteEmployee(employeeIds);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AssetManagementConstants.STATUS_204, AssetManagementConstants.MESSAGE_204));
	}

	@RequestMapping(value = "/importEmployeeData", method = RequestMethod.POST)
	public ResponseEntity<String> importEmployee(@RequestParam MultipartFile fileData) {
		String importEmployeeData = employeeService.importEmployeeData(fileData);
		return ResponseEntity.ok(importEmployeeData);
	}

	@RequestMapping(value = "/getEmployeeById/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeData> getAssetByid(@PathVariable("employeeId") String employeeId) {
		EmployeeData assetById = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok().body(assetById);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeData employeeData) {
		employeeService.updateEmployee(employeeData);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AssetManagementConstants.STATUS_200, AssetManagementConstants.MESSAGE_200));
	}

}
