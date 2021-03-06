package com.talan.rsa.restController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.talan.rsa.entity.Chapter;
import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;
import com.talan.rsa.entity.resourceSupport.RuleResource;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.repository.RuleRepository;
import com.talan.rsa.service.ChapterService;
import com.talan.rsa.service.ImplementationService;
import com.talan.rsa.service.RuleService;
import com.talan.rsa.utilities.DataTablesUtility;
import com.talan.rsa.utilities.DataWrappingUtility;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
@RestController
@RequestMapping("rules")
public class RuleController {

	private RuleService ruleService;
	
	private ImplementationService implementationService;
	
	private ChapterService chapterService;
	
	private DataWrappingUtility<Rule> dataWrappingUtility;
	
	@Autowired
	private RuleRepository ruleRepository;
	

	@Autowired
	public RuleController(RuleService ruleService, ImplementationService implementationService,
			ChapterService chapterService, DataWrappingUtility dataWrappingUtility) {
		this.ruleService = ruleService;
		this.implementationService = implementationService;
		this.chapterService = chapterService;
		this.dataWrappingUtility = dataWrappingUtility;
	}

	@RequestMapping(produces = "application/json")
	public Resources<RuleResource> getRules(UriComponentsBuilder ucb) {
		URI uriComponent = ucb.path("/rules/").build().toUri();
		List<RuleResource> rulesResourcesSupport = ruleService.findAll().stream().map(rule -> {
			String path = uriComponent.toString() + rule.getId();
			RuleResource rR = new RuleResource(rule, path);
			if(rule.getChapter() != null){
				rR.add(linkTo(methodOn(ChapterController.class).getChapterById(rule.getChapter().getId())).withRel("chapter"));
			}
			return  rR;
		}).collect(Collectors.toList());
		Resources<RuleResource> resources = new Resources<>(rulesResourcesSupport);
		resources.add(new Link(uriComponent.toString(), "self"));
		return resources;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Rule add(@RequestBody Rule rule) {
		ruleService.save(rule);
		if (!rule.getImplementations().isEmpty()) {
			List<Long> notFoundIds = implementationService.isImpsExist(rule.getImplementations());
			if (!notFoundIds.isEmpty()) {
				throw new EntityNotFoundException(notFoundIds, "implementation");
			}
			ruleService.addImpsToRule(rule.getImplementations(), rule);
		}
		return ruleService.getById(rule.getId());
	}

	@RequestMapping(value = "/{id}", produces = "application/json")
	public RuleResource getById(@PathVariable("id") long id) {
		Rule foundRule = ruleService.getById(id);
		isRuleExist(id, foundRule);
		RuleResource rR = new RuleResource(foundRule, "self");
		if(foundRule.getChapter() != null){
			rR.add(linkTo(methodOn(ChapterController.class).getChapterById(foundRule.getChapter().getId())).withRel("chapter"));
		}
		return rR;
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
	public Rule update(@PathVariable("id") long id, @RequestBody Rule rule) {
		Rule ruleToUpdate = ruleService.getById(id);
		isRuleExist(id, ruleToUpdate);
		if (rule.getChapter() != null) {
			Chapter newChapter = chapterService.getById(rule.getChapter().getId());
			if (newChapter == null) {
				throw new EntityNotFoundException(Arrays.asList(rule.getChapter().getId()), "chapter");
			}
			chapterService.save(rule.getChapter());
		}
		ruleToUpdate.copy(rule);
		return ruleService.addImpsToRule(ruleToUpdate.getImplementations(), ruleToUpdate);
	}

	@RequestMapping(value = "/{id}/update/addImps", method = RequestMethod.POST)
	public Rule addImps(@RequestBody List<Implementation> imps, @PathVariable("id") long id) {
		Rule ruleToUpdate = ruleService.getById(id);
		isRuleExist(id, ruleToUpdate);
		List<Long> notFoundIds = implementationService.isImpsExist(imps);
		if (!notFoundIds.isEmpty()) {
			throw new EntityNotFoundException(notFoundIds, "implementation");
		}
		return ruleService.addImpsToRule(imps, ruleToUpdate);
	}
	
	
	@RequestMapping(value = "/slice", produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedResources<Resource<Rule>> slice(@PageableDefault(value=2) Pageable p, PagedResourcesAssembler assembler, UriComponentsBuilder ucb, DataTablesUtility dt){
		
		System.out.println(dt.getLength());
		System.out.println(dt.getStart());
//		System.out.println(dt.getSearch());
		Pageable ppp = new PageRequest(dt.getStart()/dt.getLength(), dt.getLength());
		
		Page<Rule> page = ruleRepository.findAll(ppp);
		
//		page.forEach(e -> System.out.println(e.getTitle()));
		String urlBase = ucb.path("/rules/").build().toString();
		return dataWrappingUtility.WrapPage(page, assembler, urlBase);
	}

	private void isRuleExist(long id, Rule rule) {
		if (rule == null) {
			throw new EntityNotFoundException(Arrays.asList(id), "rule");
		}
	}
	
	@RequestMapping(value = "/s", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rule> all(){
		return ruleService.findAll();
	}
}
