package com.talan.rsa.exception;

import java.util.List;

public class EntityNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	private List<Long> entityIds;
	
	private String entity;

	public List<Long> getEntityIds() {
		return entityIds;
	}

	public String getEntity() {
		return entity;
	}

	public EntityNotFoundException(List<Long> ids, String entity) {
		this.entityIds = ids;
		this.entity = entity;
	}
}
