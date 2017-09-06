package com.talan.rsa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.talan.rsa.entity.Implementation;

public interface ImplementationService {

	 List<Implementation> findAll();
	 
	 Implementation save(Implementation imp);
	
	 Implementation getById(long id);
	 
	 List<Long> isImpsExist(List<Implementation> imps);
	 
	 void delete(long id);
	 
	 List<Implementation> findImpsByRule(long rule);
	 
	 Page<Implementation> findPage(Pageable p);

}
