package com.talan.rsa.service;

import java.util.List;

import com.talan.rsa.entity.Implementation;

public interface ImplementationService {

	 List<Implementation> findAll();
	 
	 Implementation add(Implementation imp);
	
	 Implementation getById(long id);
}
