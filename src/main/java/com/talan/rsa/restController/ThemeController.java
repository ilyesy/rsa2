package com.talan.rsa.restController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.entity.Theme;
import com.talan.rsa.entity.resourceSupport.RSAResourceSupport;
import com.talan.rsa.service.ThemeService;

@RestController
@RequestMapping("/themes")
public class ThemeController {

	private ThemeService themeService;
	
	public final String THEME="theme";
	
	@Autowired
	public ThemeController(ThemeService themeService){
		this.themeService = themeService;
	}
	
	@RequestMapping(produces="application/json")
	public Resources<RSAResourceSupport> getThemes(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/themes/").build().toUri();
		List<RSAResourceSupport> themesResourceSupport = themeService.findAll().stream().map(theme -> {
			String path = uriComponent.toString() + theme.getId();
			return new RSAResourceSupport(theme, path);
		}).collect(Collectors.toList());
		Resources<RSAResourceSupport> resources = new Resources<>(themesResourceSupport);
		resources.add(new Link(uriComponent.toString(), "self"));
		return resources;
	}
	
	@RequestMapping(value="/new",method=RequestMethod.POST, consumes="application/json")
	public Theme save(@RequestBody Theme theme){
		return themeService.add(theme);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = "application/json")
	public Theme getById(@PathVariable("id") long id){
		Theme foundTheme = themeService.getById(id);
		if(foundTheme==null){
			throw new EntityNotFoundException(id, THEME);
		}
		return foundTheme;
	}
}
