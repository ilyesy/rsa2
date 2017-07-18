package com.talan.rsa.restController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;
import com.talan.rsa.entity.resourceSupport.RuleResource;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.service.ImplementationService;
import com.talan.rsa.service.RuleService;

@RestController
@RequestMapping("rules")
public class RuleController {
	
	private RuleService ruleService;
	private ImplementationService implementationService;
	
	@Autowired
	public RuleController(RuleService ruleService, ImplementationService implementationService){
		this.ruleService = ruleService;
		this.implementationService = implementationService;
	}
	
	@RequestMapping(produces="application/json")
	public Resources<RuleResource> getRules(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/rules/").build().toUri();
		List<RuleResource> rulesResourcesSupport = ruleService.findAll().stream()
				.map(rule -> {
					String path = uriComponent.toString() + rule.getId();
					return new RuleResource(rule, path);
				}).collect(Collectors.toList());
		Resources<RuleResource> resources = new Resources<>(rulesResourcesSupport);
		resources.add(new Link(uriComponent.toString(), "self"));
		return resources;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Rule add(@RequestBody Rule rule){
		ruleService.save(rule);
		if(!rule.getImplementations().isEmpty()){
			List<Long> notFoundIds = implementationService.isImpsExist(rule.getImplementations());
			if(!notFoundIds.isEmpty()){
				throw new EntityNotFoundException(notFoundIds, "implementation");
			}
			ruleService.addImpsToRule(rule.getImplementations(), rule); 
		}
		return ruleService.getById(rule.getId());
	}
	
	@RequestMapping(value="/{id}")
	public Rule getById(@PathVariable("id") long id){
		Rule foundRule = ruleService.getById(id);
		isRuleExist(id, foundRule);
		return foundRule;
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
	public Rule update(@PathVariable("id") long id, @RequestBody Rule rule){
		Rule ruleToUpdate = ruleService.getById(id);
		isRuleExist(id, ruleToUpdate);
		//check if chapter exists, if yes load it like is(chapter is not modifiable here but changeable). if chap not found ==> exception
		//check if imps exist and have the same ids as the current ones.if yes modify with new values else ==> exception. chaps are not changeable here		
		ruleToUpdate.copy(rule);// +copy imps and chap
		return ruleService.save(ruleToUpdate);
	}
	
	@RequestMapping(value = "/{id}/update/addImps", method = RequestMethod.POST)
	public Rule addImps(@RequestBody List<Implementation> imps, @PathVariable("id") long id){
		Rule ruleToUpdate = ruleService.getById(id);
		isRuleExist(id, ruleToUpdate);
		List<Long> notFoundIds = implementationService.isImpsExist(imps);
		if(!notFoundIds.isEmpty()){
			throw new EntityNotFoundException(notFoundIds, "implementation");
		}
		return ruleService.addImpsToRule(imps, ruleToUpdate);
	}

	private void isRuleExist(long id, Rule rule) {
		if(rule == null){
			throw new EntityNotFoundException(Arrays.asList(id), "rule");
		}
	}
}
