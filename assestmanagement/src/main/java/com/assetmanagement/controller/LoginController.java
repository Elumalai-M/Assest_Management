package com.assetmanagement.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.dto.ErrorResponseDto;
import com.assetmanagement.dto.LoginRequestData;
import com.assetmanagement.dto.LoginResponse;
import com.assetmanagement.service.EmployeeService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private EmployeeService employeeService;

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status Success"),
			@ApiResponse(responseCode = "401", description = "HTTP Status Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping
	public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequestData employeeData) throws IOException {
		LoginResponse loginResponse = employeeService.authenticateGenerateToken(employeeData);
		return ResponseEntity.ok(loginResponse);
	}

}
