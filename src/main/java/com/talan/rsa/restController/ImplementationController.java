package com.talan.rsa.restController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.resourceSupport.RSAResourceSupport;
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
	
	@RequestMapping(produces="application/json")
	public Resources<RSAResourceSupport> getImplementations(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/imps/").build().toUri();
		List<RSAResourceSupport> impsResourceSupport = implementationService.findAll().stream()
				.map((imp) -> {
					String path = uriComponent.toString()+imp.getId();
					return new RSAResourceSupport(imp, path);
				})
				.collect(Collectors.toList());
		Resources<RSAResourceSupport> resources = new Resources<RSAResourceSupport>(impsResourceSupport);
		resources.add(linkTo(methodOn(ImplementationController.class).getImplementations(null)).withSelfRel());
		return resources;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Implementation add(@RequestBody Implementation imp){
		return implementationService.add(imp);
	}
	
	@RequestMapping("/{id}")
	public Implementation getById(@PathVariable("id") long id){
		Implementation imp = implementationService.getById(id);
		if(imp == null){
			throw new EntityNotFoundException(id, "implementation");
		}
		return imp ;
	}
}
