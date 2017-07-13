package com.talan.rsa.restController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.talan.rsa.entity.Chapter;
import com.talan.rsa.entity.resourceSupport.RSAResourceSupport;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.service.ChapterService;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
	
	private ChapterService chapterService;

	@Autowired
	public ChapterController(ChapterService chapterService) {
		this.chapterService = chapterService;
	}

	@RequestMapping(produces="application/hal+json")
	public Resources<RSAResourceSupport> getChapters(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/chapters/").build().toUri();
		List<RSAResourceSupport> chapsResourceSuppurt = chapterService.findAll().stream()
				.map(chap -> {
					String path = uriComponent.toString()+chap.getId();
					return new RSAResourceSupport(chap, path);
				}).collect(Collectors.toList());
		Resources<RSAResourceSupport> resources = new Resources<RSAResourceSupport>(chapsResourceSuppurt);
		resources.add(linkTo(methodOn(ChapterController.class).getChapters(null)).withSelfRel());
		return resources;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Chapter save(@RequestBody Chapter chapter){
		return chapterService.add(chapter);
	}
	
	@RequestMapping(value="/{id}")
	public Chapter getChapterById(@PathVariable(value="id") long id){
		Chapter chap = chapterService.getById(id);
		if(chap == null){
			throw new EntityNotFoundException(id, "chapter");
		}
		return chap;
	}
	
}
