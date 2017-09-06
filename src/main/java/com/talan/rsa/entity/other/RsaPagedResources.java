package com.talan.rsa.entity.other;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedResources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RsaPagedResources<T>{
	
	
	private final Collection<T> content;
	
	private Object pagedMetadata;
	
	public RsaPagedResources(PagedResources<T> pr){
		this.content = pr.getContent();
		this.pagedMetadata = pr.getMetadata();
	}

	public Collection<T> getContent() {
		return content;
	}

	public Object getPagedMetadata() {
		return pagedMetadata;
	}

	public void setPagedMetadata(Object pagedMetadata) {
		this.pagedMetadata = pagedMetadata;
	}
	
	

}
