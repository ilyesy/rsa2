package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Resource;

import com.talan.rsa.entity.Theme;


public class ThemeResource extends Resource<Theme> {

	ThemeResource(final Theme theme) {
		super(theme);
	}

}
