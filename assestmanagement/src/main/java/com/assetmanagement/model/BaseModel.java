package com.assetmanagement.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.assetmanagement.audit.CustomAuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(CustomAuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseModel {
	@CreationTimestamp 
	@Column(updatable = false)
	@JsonIgnore
	private Date createdAt;

	@CreatedBy
	@Column(updatable = false)
	@JsonIgnore
	private String createdBy;

	@UpdateTimestamp
	@Column(insertable = false)
	@JsonIgnore
	private Date updatedAt;

	@LastModifiedBy
	@Column(insertable = false)
	@JsonIgnore
	private String updatedBy;

}
