package com.talan.rsa.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.rsa.entity.Theme;
import com.talan.rsa.repository.ThemeRepository;
import com.talan.rsa.service.ThemeService;

@Service
public class ThemeServiceImp implements ThemeService{
	
	private ThemeRepository themeRepository;

	@Autowired
	public ThemeServiceImp(ThemeRepository themeSRepository) {
		this.themeRepository = themeSRepository;
	}

	@Override
	public List<Theme> findAll() {
		return themeRepository.findAll();
	}

	@Override
	public Theme add(Theme theme) {
		return themeRepository.save(theme);
	}

	@Override
	public Theme getById(long id) {
		Theme th = themeRepository.findOne(id);
		return th;
	}
}
