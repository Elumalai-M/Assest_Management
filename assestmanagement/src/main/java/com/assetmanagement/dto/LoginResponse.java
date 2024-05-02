package com.assetmanagement.dto;

import java.util.List;


public record LoginResponse(String email,List<String> role,String jwt) {
	
}
