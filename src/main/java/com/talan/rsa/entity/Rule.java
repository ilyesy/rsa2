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


@Entity(name="rules")
public class Rule{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	private long id;
	
	private String title;
	
	private String description;
	
	@ManyToOne
	@JsonIgnoreProperties({ "description", "rules", "theme" })
	private Chapter chapter;
	
	@OneToMany(mappedBy = "rule")
	@JsonIgnoreProperties({"rule" })
	private List<Implementation> implementations;
		
	
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

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public List<Implementation> getImplementations() {
		return implementations;
	}

	public void setImplementations(List<Implementation> implementations) {
		this.implementations = implementations;
	}

	public Rule(String title, String description, Chapter chapter, List<Implementation> implementations) {
		this.title = title;
		this.description = description;
		this.chapter = chapter;
		this.implementations = implementations;
	}

	public Rule() {
	}
	
	
	
}
