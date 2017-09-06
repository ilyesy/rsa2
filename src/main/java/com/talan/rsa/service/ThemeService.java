package com.talan.rsa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.talan.rsa.entity.Theme;

public interface ThemeService {

	 List<Theme> findAll();
	
	 Theme save(Theme theme);
	
	 Theme getById(long id);
	 
	 Page<Theme> findPage(Pageable p);
}
