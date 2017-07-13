package com.talan.rsa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.talan.rsa.entity.other.ImplementationEnum;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name="implementations")
public class Implementation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	private long id;
	
	private String snippet;
	
	private String comment;
	
	@Enumerated(EnumType.STRING)
	private ImplementationEnum type;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"description", "implementations", "chapter" })
	private Rule rule;
	
	
	public String getSnippet() {
		return snippet;
	}
	
	public void setSnippet(String code) {
		this.snippet = code;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public ImplementationEnum getType() {
		return type;
	}
	
	public void setType(ImplementationEnum type) {
		this.type = type;
	}
	
	public long getId() {
		return id;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
	public Implementation() {
		
	}

	public Implementation(String snippet, String comment, ImplementationEnum type, Rule rule) {
		super();
		this.snippet = snippet;
		this.comment = comment;
		this.type = type;
		this.rule = rule;
	}
	
	

}
