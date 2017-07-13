package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.talan.rsa.entity.Chapter;


public class RSAResource extends Resource<Object> {

	public RSAResource(final Object chap, String path) {
		super(chap);
		this.add(new Link(path).withSelfRel());
	}

}
