package com.talan.rsa.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="themes")
public class Theme{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	private long id;
	
	private String title;
	
	private String description;
	
	@OneToMany(mappedBy="theme")
//	@JsonSerialize(using = CustomListSerializr.class)
	@JsonIgnoreProperties({"rules", "theme"})
	private List<Chapter> chapters;
	
	
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

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Theme(String title, String description, List<Chapter> chapters) {
		this.title = title;
		this.description = description;
		this.chapters = chapters;
	}

	public Theme() {
	}
	
	
	
	
}
