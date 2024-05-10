package com.assetmanagement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VendorResponseDto {
	
	private List<VendorData> vendordatalist;
	
	private Integer pageNo;
	
	private Integer pageSize;
	private Long totalElements;
    private Integer totalPages;
    private Boolean last;
	
}
