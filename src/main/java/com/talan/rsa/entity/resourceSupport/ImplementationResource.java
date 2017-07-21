package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Link;
import com.talan.rsa.entity.Implementation;

public class ImplementationResource extends Resource<Implementation>{

	public ImplementationResource(final Implementation imp, Link link) {
		super(imp);
		this.add(link.withSelfRel());
	}
	

}
