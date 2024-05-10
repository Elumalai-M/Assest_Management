package com.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorData {
	
	private long id;
	private String vendorId;
	private String vendorName;
	private String email;
	private String poc;
	private String contactNumber;
	private boolean status;
	

}
