package com.talan.rsa.restController;

import java.net.URI;
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

import com.talan.rsa.entity.Rule;
import com.talan.rsa.entity.resourceSupport.RSAResourceSupport;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.service.RuleService;

@RestController
@RequestMapping("rules")
public class RuleController {
	
	private RuleService ruleService;
	
	@Autowired
	public RuleController(RuleService ruleService){
		this.ruleService = ruleService;
	}
	
	@RequestMapping(produces="application/json")
	public Resources<RSAResourceSupport> getRules(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/rules/").build().toUri();
		List<RSAResourceSupport> rulesResourcesSupport = ruleService.findAll().stream()
				.map(rule -> {
					String path = uriComponent.toString() + rule.getId();
					return new RSAResourceSupport(rule, path);
				}).collect(Collectors.toList());
		Resources<RSAResourceSupport> resources = new Resources<>(rulesResourcesSupport);
		resources.add(new Link(uriComponent.toString(), "self"));
		return resources;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Rule add(@RequestBody Rule rule){
		return ruleService.add(rule);
	}
	
	@RequestMapping(value="/{id}")
	public Rule fetById(@PathVariable("id") long id){
		Rule foundRule = ruleService.getbyId(id);
		if(foundRule == null){
			throw new EntityNotFoundException(id, "rule");
		}
		return foundRule;
	}

}
