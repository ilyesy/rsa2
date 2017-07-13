package com.talan.rsa.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.rsa.entity.Implementation;
import com.talan.rsa.repository.ImplementationRepository;
import com.talan.rsa.service.ImplementationService;

@Service
public class ImplementationServiceImp implements ImplementationService {
	
	private ImplementationRepository implementationRepository;
	
	
	@Autowired
	public ImplementationServiceImp(ImplementationRepository implementationRepository) {
		this.implementationRepository = implementationRepository;
	}

	@Override
	public List<Implementation> findAll() {
		return implementationRepository.findAll();
	}
	
	@Override
	public Implementation add(Implementation imp) {
		return implementationRepository.save(imp);
	}

	@Override
	public Implementation getById(long id) {
		return implementationRepository.findOne(id);
	}

}
