package com.talan.rsa.restController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.talan.rsa.exception.EntityNotFoundException;
import com.talan.rsa.entity.Theme;
import com.talan.rsa.entity.resourceSupport.ThemeResource;
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
	public Resources<ThemeResource> getThemes(UriComponentsBuilder ucb){
		URI uriComponent = ucb.path("/themes/").build().toUri();
		List<ThemeResource> themesResourceSupport = themeService.findAll().stream().map(theme -> {
			String path = uriComponent.toString() + theme.getId();
			return new ThemeResource(theme, path);
		}).collect(Collectors.toList());
		Resources<ThemeResource> resources = new Resources<ThemeResource>(themesResourceSupport);
		resources.add(new Link(uriComponent.toString(), "self"));
		return resources;
	}
	
	@RequestMapping(value="/new",method=RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Theme save(@RequestBody @Valid Theme theme){
		return themeService.save(theme);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = "application/json")
	public Theme getById(@PathVariable("id") long id){
		Theme foundTheme = themeService.getById(id);
		if(foundTheme==null){
			throw new EntityNotFoundException(Arrays.asList(id), THEME);
		}
		return foundTheme;
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
	public Theme update(@PathVariable("id") long id, @RequestBody Theme theme){
		Theme themeToUpdate = themeService.getById(id);
		if(themeToUpdate == null){
			throw new EntityNotFoundException(Arrays.asList(id), "rule");
		}
		themeToUpdate.copy(theme);
		return themeService.save(themeToUpdate);
	}
}
