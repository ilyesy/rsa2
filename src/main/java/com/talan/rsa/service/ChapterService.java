package com.talan.rsa.service;

import java.util.List;

import com.talan.rsa.entity.Chapter;

public interface ChapterService {
	
	 List<Chapter> findAll();
	 
	 Chapter add(Chapter chapter);
	 
	 Chapter getById(long id);

}
