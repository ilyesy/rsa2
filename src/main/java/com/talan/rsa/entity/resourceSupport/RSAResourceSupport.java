package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import com.talan.rsa.entity.Chapter;
import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;
import com.talan.rsa.entity.Theme;

public class RSAResourceSupport extends ResourceSupport{
	
	private final Object data;

	public RSAResourceSupport(Implementation imp, String uri) {
		this.data =  imp;
		this.add(new Link(uri).withSelfRel());
		//this.add(linkTo(methodOn(ImplementationController.class).getImplementations(null, null)).withSelfRel());
	}
	
	public RSAResourceSupport(Rule rule, String uri) {
		this.data =  rule;
		this.add(new Link(uri).withSelfRel());
		//this.add(linkTo(methodOn(ImplementationController.class).getImplementations(null, null)).withSelfRel());
	}
	
	public RSAResourceSupport(Chapter chap, String uri) {
		this.data =  chap;
		this.add(new Link(uri).withSelfRel());
		//this.add(linkTo(methodOn(ImplementationController.class).getImplementations(null, null)).withSelfRel());
	}
	
	public RSAResourceSupport(Theme theme, String uri) {
		this.data =  theme;
		this.add(new Link(uri).withSelfRel());
		//this.add(linkTo(methodOn(ImplementationController.class).getImplementations(null, null)).withSelfRel());
	}
	
	public Object getData() {
		return data;
	}
}
