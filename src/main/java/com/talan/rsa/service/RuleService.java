package com.talan.rsa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;

public interface RuleService {

	 List<Rule> findAll();
	 
	 Rule save(Rule rule);
	 
	 Rule getById(long id);
	 
	 Rule addImpsToRule(List<Implementation>imps, Rule ruleToUpdate);
	 
	 Page<Rule> findPage(Pageable p);
	 
}
