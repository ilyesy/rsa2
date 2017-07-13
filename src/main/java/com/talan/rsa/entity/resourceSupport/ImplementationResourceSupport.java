package com.talan.rsa.entity.resourceSupport;

import org.springframework.hateoas.Resource;
import com.talan.rsa.entity.Implementation;

public class ImplementationResourceSupport extends Resource<Implementation>{

	public ImplementationResourceSupport(final Implementation imp) {
		super(imp);
	}
	

}
