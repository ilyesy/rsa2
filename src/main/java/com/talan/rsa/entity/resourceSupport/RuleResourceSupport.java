package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Resource;

import com.talan.rsa.entity.Rule;

public class RuleResourceSupport extends Resource<Rule> {

	RuleResourceSupport(Rule rule) {
		super(rule);
	}
	
	

}
