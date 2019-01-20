package com.vanhack.api.shorturl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanhack.api.shorturl.data.URLModel;
import com.vanhack.api.shorturl.data.URLRepository;

@Service
public class ShortURLService {

	private final URLRepository repository;
	
	@Autowired
	public ShortURLService(URLRepository repository) {
		this.repository = repository;
	}
	
	public URLModel getShort(String url, String host) {
		List<URLModel> listOfURLs = this.repository.findByLongURL(url);
		
		return listOfURLs
				.stream()
				.findFirst().orElse(create(url, host));
	}

	private URLModel create(String url, String host) {
		URLModel model = new URLModel(url, NewURL.getShorter(url, host));
		
		this.repository.save(model);
		
		return model;
	}

	public URLModel getLong(String shortURL) throws Exception {
		List<URLModel> listOfURLs = this.repository.findByShortURL(shortURL);
		
		return listOfURLs
				.stream()
				.findFirst().orElseThrow(() -> new Exception("URL not found."));
				
	}

}
