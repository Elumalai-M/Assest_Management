package com.assestmanagement.controller;

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

import com.assestmanagement.constant.AssestManagementConstants;
import com.assestmanagement.dto.AssestData;
import com.assestmanagement.dto.EmployeeData;
import com.assestmanagement.dto.ErrorResponseDto;
import com.assestmanagement.dto.FixedAssestData;
import com.assestmanagement.dto.ResponseDto;
import com.assestmanagement.model.FixedAssestDataResponse;
import com.assestmanagement.service.AssestService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/assest")
public class AssestController {
	
		@Autowired
		private AssestService assestService;
	
		
	  @ApiResponses({
		        @ApiResponse(
		                responseCode = "201",
		                description = "HTTP Status CREATED"
		        ),
		        @ApiResponse(
		                responseCode = "500",
		                description = "HTTP Status Internal Server Error",
		                content = @Content(
		                        schema = @Schema(implementation = ErrorResponseDto.class)
		                )
		        )
		}
		)
	   @RequestMapping(value = "/create", method = RequestMethod.POST)
		public ResponseEntity<ResponseDto> createAssest(@RequestBody AssestData assestData) {
		   assestService.createAssest(assestData);
	    	return ResponseEntity.status(HttpStatus.CREATED)
	    			.body(new ResponseDto(AssestManagementConstants.STATUS_201, AssestManagementConstants.MESSAGE_201));
		}
	  
	   @RequestMapping(value = "/update", method = RequestMethod.PUT)
			public ResponseEntity<ResponseDto> updateAssest(@RequestBody AssestData assestData) {
			   assestService.updateAssest(assestData);
		    	return ResponseEntity.status(HttpStatus.CREATED)
		    			.body(new ResponseDto(AssestManagementConstants.STATUS_200, AssestManagementConstants.MESSAGE_200));
			}
	   
	    @RequestMapping(value = "/getAssestList", method = RequestMethod.GET)
		public ResponseEntity<List<FixedAssestDataResponse>> getAssestList() {
	    	List<FixedAssestDataResponse> fetchAssestList = assestService.fetchAssestList();
	    	return ResponseEntity.ok().body(fetchAssestList);
		}
	    
	    @RequestMapping(value="/getAssestById/{assetId}", method = RequestMethod.GET)
	    public ResponseEntity<FixedAssestDataResponse> getAssestByid(@PathVariable("assetId") long assetId){
	    	FixedAssestDataResponse assestById = assestService.getAssestById(assetId);
	    	return ResponseEntity.ok().body(assestById);
	    }

}
