package com.vanhack.api.shorturl.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "url")
public class URLModel {
	     
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 
	private String longURL;
	 
	private String shortURL;
	
	private URLModel() {
	}

	public URLModel(String longURL, String shortURL) {
		this();
		this.longURL = longURL;
		this.shortURL = shortURL;
	}
	
	public long getId() {
		return id;
	}
	
	public String getLongURL() {
		return longURL;
	}
	
	public String getShortURL() {
		return shortURL;
	}
}
