package com.talan.rsa.service;

import java.util.List;

import com.talan.rsa.entity.Rule;

public interface RuleService {

	 List<Rule> findAll();
	 
	 Rule add(Rule rule);
	 
	 Rule getbyId(long id);
}
