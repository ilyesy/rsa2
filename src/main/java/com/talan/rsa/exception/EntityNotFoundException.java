package com.talan.rsa.exception;

public class EntityNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	private long entityId;
	
	private String entity;

	public long getThemeId() {
		return entityId;
	}

	public String getEntity() {
		return entity;
	}

	public EntityNotFoundException(long id, String entity) {
		this.entityId = id;
		this.entity = entity;
	}
}
