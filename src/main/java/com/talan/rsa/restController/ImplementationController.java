package com.talan.rsa.restController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.resourceSupport.ImplementationResource;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.service.ImplementationService;

@RestController
@RequestMapping("/imps")
public class ImplementationController {

	private ImplementationService implementationService;
	
	@Autowired
	public ImplementationController(ImplementationService implementationService) {
		this.implementationService = implementationService;
	}
	
	
	@RequestMapping(produces="application/hal+json")
	public Resources<ImplementationResource> getImplementations(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/imps/").build().toUri();
		List<ImplementationResource> impsResourceSupport = implementationService.findAll().stream()
				.map((imp) -> {
					String path = uriComponent.toString()+imp.getId();
					return new ImplementationResource(imp, path);
				})
				.collect(Collectors.toList());
		Resources<ImplementationResource> resources = new Resources<ImplementationResource>(impsResourceSupport);
		resources.add(linkTo(methodOn(ImplementationController.class).getImplementations(null)).withSelfRel());
		return resources;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Implementation add(@RequestBody Implementation imp){
		return implementationService.save(imp);
	}
	
	@RequestMapping("/{id}")
	public Implementation getById(@PathVariable("id") long id){
		Implementation imp = implementationService.getById(id);
		if(imp == null){
			throw new EntityNotFoundException(Arrays.asList(id), "implementation");
		}
		return imp ;
	}
	
	@Transactional
	@RequestMapping(value = "/{id}/update", method=RequestMethod.PUT)
	public Implementation update(@PathVariable("id") long id, @RequestBody Implementation imp){
		Implementation impToUpdate = implementationService.getById(id);
		if(impToUpdate == null){
			throw new EntityNotFoundException(Arrays.asList(id), "implementation");
		}
		impToUpdate.copy(imp);
		return implementationService.save(impToUpdate);
	}
}
