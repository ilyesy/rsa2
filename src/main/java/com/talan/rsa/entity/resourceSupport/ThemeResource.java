package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.talan.rsa.entity.Theme;


public class ThemeResource extends Resource<Theme> {

	public ThemeResource(final Theme theme, String path) {
		super(theme);
		this.add(new Link(path).withSelfRel());
	}

}
