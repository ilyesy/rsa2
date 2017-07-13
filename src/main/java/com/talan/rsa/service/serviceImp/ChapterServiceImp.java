package com.talan.rsa.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.rsa.entity.Chapter;
import com.talan.rsa.repository.ChapterRepository;
import com.talan.rsa.service.ChapterService;
/**
 * 
 * @author iyoussef
 *
 */
@Service
public class ChapterServiceImp implements ChapterService {
	
	private ChapterRepository chapterRepository;
	
	
	@Autowired
	public ChapterServiceImp(ChapterRepository chapterRepository) {
		this.chapterRepository = chapterRepository;
	}


	@Override
	public List<Chapter> findAll() {
		return chapterRepository.findAll();
	}


	@Override
	public Chapter add(Chapter chapter) {
		return chapterRepository.save(chapter);
	}


	@Override
	public Chapter getById(long id) {
		return chapterRepository.findOne(id);
	}
	
	

}
