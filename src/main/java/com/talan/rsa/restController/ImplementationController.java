package com.talan.rsa.restController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.talan.rsa.entity.other.RsaResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.resourceSupport.ImplementationResource;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.service.ImplementationService;import com.talan.rsa.service.RuleService;

@RestController
@RequestMapping("/imps")
public class ImplementationController {

	private ImplementationService implementationService;
	
	private RuleService ruleService;
	
	@Autowired
	public ImplementationController(ImplementationService implementationService, RuleService ruleService) {
		this.implementationService = implementationService;
		this.ruleService = ruleService;
	}	
	
	@RequestMapping(produces = "application/hal+json")
	public Resources<ImplementationResource> getImplementations(){
		List<ImplementationResource> impsResourceSupport = implementationService.findAll().stream()
				.map((imp) -> {
					Link link = linkTo(methodOn(ImplementationController.class).getById(imp.getId())).withSelfRel();
					ImplementationResource impResource = new ImplementationResource(imp, link);
					if (imp.getRule() != null){
						impResource.add(linkTo(methodOn(RuleController.class).getById(imp.getRule().getId())).withRel("rule"));
					}
					return impResource;
				})
				.collect(Collectors.toList());
		Resources<ImplementationResource> resources = new Resources<ImplementationResource>(impsResourceSupport);
		resources.add(linkTo(methodOn(ImplementationController.class).getImplementations()).withSelfRel());
		return resources;
	}
	
	@RequestMapping("/{id}")
	public ImplementationResource getById(@PathVariable("id") long id){
		Implementation imp = implementationService.getById(id);
		if(imp == null){
			throw new EntityNotFoundException(Arrays.asList(id), "implementation");
		}
		Link link = linkTo(methodOn(ImplementationController.class).getById(imp.getId())).withSelfRel();
		ImplementationResource impResource = new ImplementationResource(imp, link);
		if(imp.getRule() != null){
			impResource.add(linkTo(methodOn(RuleController.class).getById(imp.getRule().getId())).withRel("rule"));
		}
		return impResource ;
	}
	
	@RequestMapping(value="/new", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Implementation add(@RequestBody Implementation imp){
		return implementationService.save(imp);
	}
		
	@RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
	public Implementation update(@PathVariable("id") long id, @RequestBody Implementation imp){
		Implementation impToUpdate = implementationService.getById(id);
		if(impToUpdate == null){
			throw new EntityNotFoundException(Arrays.asList(id), "implementation");
		}
		impToUpdate.copy(imp);
		return implementationService.save(impToUpdate);
	}
	
	@RequestMapping(value="/{id}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<RsaResponseBody> delete(@PathVariable("id") long id){
		Implementation impToDelete = implementationService.getById(id);
		if(impToDelete==null){
			throw new EntityNotFoundException(Arrays.asList(id), "implementaiton");
		}
		implementationService.delete(id);
		return new ResponseEntity<>(new RsaResponseBody(200, "good"), HttpStatus.OK );
	}
	
	@RequestMapping(value = "/rule/{id}", method = RequestMethod.GET)
	public List<Implementation> getImpsByRule(@PathVariable("id") long id){
		if(ruleService.getById(id) == null){
			System.out.println("not found");
			throw new EntityNotFoundException(Arrays.asList(id), "rule");
		}
		
		return implementationService.findImpsByRule(id);
	}
	
	
	
}
