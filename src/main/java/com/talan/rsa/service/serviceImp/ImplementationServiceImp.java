package com.talan.rsa.service.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;
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
	public Implementation save(Implementation imp) {
		return implementationRepository.save(imp);
	}
	
	@Override
	public Implementation getById(long id) {
		return implementationRepository.findOne(id);
	}
	
	@Override
	public List<Long> isImpsExist(List<Implementation> imps){
		List<Long> notFound = new ArrayList<>();
		imps.stream().forEach(imp -> {
			if(implementationRepository.findOne(imp.getId()) == null){
				notFound.add(imp.getId());
			}
		});
		return notFound;
	}

	@Override
	public void delete(long id) {
		implementationRepository.delete(id);
	}

	@Override
	public List<Implementation> findImpsByRule(long rule) {
		return implementationRepository.findImpsByRule(rule);
	}

}
