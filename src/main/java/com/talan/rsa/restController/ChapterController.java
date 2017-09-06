package com.talan.rsa.restController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.talan.rsa.entity.Chapter;
import com.talan.rsa.entity.resourceSupport.ChapterResource;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.repository.ChapterRepository;
import com.talan.rsa.service.ChapterService;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
	
	private ChapterService chapterService;
	
	@Autowired
	public ChapterController(ChapterService chapterService, ChapterRepository chapterRepo) {
		this.chapterService = chapterService;
	}
	
	@RequestMapping(produces="application/hal+json")
	public Resources<ChapterResource> getChapters(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/chapters/").build().toUri();
		List<ChapterResource> chapsResourceSuppurt = chapterService.findAll().stream()
				.map(chap -> {
					String path = uriComponent.toString()+chap.getId();
					return new ChapterResource(chap, path);
				}).collect(Collectors.toList());
		Resources<ChapterResource> resources = new Resources<ChapterResource>(chapsResourceSuppurt);
		resources.add(linkTo(methodOn(ChapterController.class).getChapters(null)).withSelfRel());
		return resources;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Chapter save(@RequestBody Chapter chapter){
		return chapterService.save(chapter);
	}
	
	@RequestMapping(value="/{id}")
	public Chapter getChapterById(@PathVariable(value="id") long id){
		Chapter chap = chapterService.getById(id);
		if(chap == null){
			throw new EntityNotFoundException(Arrays.asList(id), "chapter");
		}
		return chap;
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/update")
	public Chapter update(@RequestBody Chapter chap, @PathVariable("id") long id){
		Chapter chapToUpdate = chapterService.getById(id);
		if(chapToUpdate == null){
			throw new EntityNotFoundException(Arrays.asList(id), "chapter");
		}
		chapToUpdate.copy(chap);
		return chapterService.save(chapToUpdate);
	}
	
	@RequestMapping(value="/page", produces="application/hal+json")
	public Page<Chapter> page(Pageable p){
		
		return chapterService.findPage(p);
	}
	
	@RequestMapping(value="/slice", produces="application/json", method = RequestMethod.GET)
	public PagedResources<Chapter> slice(Pageable p, PagedResourcesAssembler assembler, UriComponentsBuilder ucb){
		Page<Chapter> chapters = chapterService.findPage(p);
		URI uriComponent = ucb.path("/chapters/").build().toUri();
		Page<Resource<Chapter>> resourcePage = chapters.map(chap -> {
			String path = uriComponent.toString() + "/" + chap.getId();
			return new Resource<Chapter>(chap, new Link(path));
		}); 
	    return assembler.toResource(resourcePage);
	}
}
