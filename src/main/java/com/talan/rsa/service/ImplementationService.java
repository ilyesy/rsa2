package com.talan.rsa.service;

import java.util.List;

import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;

public interface ImplementationService {

	 List<Implementation> findAll();
	 
	 Implementation save(Implementation imp);
	
	 Implementation getById(long id);
	 
	 List<Long> isImpsExist(List<Implementation> imps);

}
