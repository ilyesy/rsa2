package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.talan.rsa.entity.Rule;

public class RuleResource extends Resource<Rule> {

	public RuleResource(Rule rule, String path) {
		super(rule);
		this.add(new Link(path).withSelfRel());
	}
	
	

}
