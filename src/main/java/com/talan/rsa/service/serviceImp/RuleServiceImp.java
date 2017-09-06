package com.talan.rsa.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;
import com.talan.rsa.repository.ImplementationRepository;
import com.talan.rsa.repository.RuleRepository;
import com.talan.rsa.service.RuleService;

@Service
public class RuleServiceImp implements RuleService{
	
	private RuleRepository ruleRepository;
	
	private ImplementationRepository implementationRepository;

	@Autowired
	public RuleServiceImp(RuleRepository ruleRepository, ImplementationRepository implementationRepository) {
		this.ruleRepository = ruleRepository;
		this.implementationRepository = implementationRepository;
	}

	@Override
	public List<Rule> findAll() {
		
		return ruleRepository.findAll();
	}
	
	@Override
	public Rule save(Rule rule) {
	return ruleRepository.save(rule);
	}

	@Override
	public Rule getById(long id) {
	return ruleRepository.findOne(id);
	}
	
	@Override
	public Rule addImpsToRule(List<Implementation> imps, Rule ruleToUpdate) {
		imps.forEach(imp -> {
			Implementation impToAddId = implementationRepository.findOne(imp.getId());
			impToAddId.setRule(ruleToUpdate);
			implementationRepository.save(impToAddId);
		});
		return ruleToUpdate;
	}
	
	@Override
	public Page<Rule> findPage(Pageable p) {
		return ruleRepository.findAll(p);
	}

}
