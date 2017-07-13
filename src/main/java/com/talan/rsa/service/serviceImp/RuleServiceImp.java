package com.talan.rsa.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.rsa.entity.Rule;
import com.talan.rsa.repository.RuleRepository;
import com.talan.rsa.service.RuleService;

@Service
public class RuleServiceImp implements RuleService{
	
	private RuleRepository ruleRepository;

	@Autowired
	public RuleServiceImp(RuleRepository ruleRepository) {
		this.ruleRepository = ruleRepository;
	}

	@Override
	public List<Rule> findAll() {
		
		return ruleRepository.findAll();
	}
	
	@Override
	public Rule add(Rule rule) {
	return ruleRepository.save(rule);
	}

	@Override
	public Rule getbyId(long id) {
	return ruleRepository.findOne(id);
	}

}
