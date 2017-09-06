package com.talan.rsa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.talan.rsa.entity.Chapter;

public interface ChapterService {
	
	 List<Chapter> findAll();
	 
	 Chapter save(Chapter chapter);
	 
	 Chapter getById(long id);
	 
	 Page<Chapter> findPage(Pageable p);

}
