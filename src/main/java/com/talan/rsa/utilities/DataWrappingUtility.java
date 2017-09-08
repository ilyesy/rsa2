package com.talan.rsa.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
public class DataWrappingUtility<T> {
	
	public Resource<T> wrapSingle(T single, String urlBase) {
		try{
			Method getId = single.getClass().getMethod("getId", null);
			String path = urlBase + getId.invoke(single);
			return new Resource<T>(single, new Link(path));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return new Resource<T>(single);
	}
	
	public PagedResources<Resource<T>> WrapPage(Page<T> page, PagedResourcesAssembler assembler, String urlBase){
		Page<Resource<T>> resourcePage = page.map(e -> {
			return this.wrapSingle(e, urlBase);
		});
		return assembler.toResource(resourcePage);
	}

}
