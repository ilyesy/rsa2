package com.talan.rsa.service;

import java.util.List;

import com.talan.rsa.entity.Theme;

public interface ThemeService {

	 List<Theme> findAll();
	
	 Theme save(Theme theme);
	
	 Theme getById(long id);
}
