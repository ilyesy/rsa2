package com.talan.rsa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="chapters")
public class Chapter{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	private long id;
	
	private String title;
	
	private String description;
	
	@ManyToOne
	@JsonIgnoreProperties({"description", "chapters" })
	private Theme theme;
	
	@OneToMany(mappedBy="chapter")
	@JsonIgnoreProperties({"implementations", "chapter"})
	private List<Rule> rules;
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	
	public Theme getTheme() {
		return theme;
	}
	
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public Chapter(String title, String description, Theme theme, List<Rule> rules) {
		this.title = title;
		this.description = description;
		this.theme = theme;
		this.rules = rules;
	}

	public Chapter() {		
	}
	
	
}
