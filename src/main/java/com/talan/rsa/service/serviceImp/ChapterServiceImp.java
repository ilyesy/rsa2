package com.talan.rsa.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	
	
	public ChapterServiceImp(ChapterRepository chapterRepository) {
		this.chapterRepository = chapterRepository;
	}


	@Override
	public List<Chapter> findAll() {
		return chapterRepository.findAll();
	}


	@Override
	public Chapter save(Chapter chapter) {
		return chapterRepository.save(chapter);
	}


	@Override
	public Chapter getById(long id) {
		return chapterRepository.findOne(id);
	}


	@Override
	public Page<Chapter> findPage(Pageable p) {
		return chapterRepository.findAll(p);
	}
	
	

}
