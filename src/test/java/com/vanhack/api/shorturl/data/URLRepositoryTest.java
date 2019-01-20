package com.vanhack.api.shorturl.data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vanhack.api.shorturl.data.URLModel;
import com.vanhack.api.shorturl.data.URLRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class URLRepositoryTest {

	@Autowired
	private URLRepository urlRepository;

	@DisplayName("Return URLModel when find by short URL")
	@Test
	public void returnURLModelWhenFindByShortURL() {
		String longURL = "http://www.vanhack.com/testing/a/long/url/address";
		String shortURL = "http://van.hack/M6N6HA";
		
		urlRepository.save(new URLModel(longURL, shortURL));
		
		List<URLModel> urls = urlRepository.findByShortURL(shortURL);
		
		assertEquals(urls.get(0).getLongURL(), longURL);
	}

	@DisplayName("Return URLModel when find by long URL")
	@Test
	public void returnURLModelWhenFindByLongURL() {
		String longURL = "http://www.vanhack.com/testing/a/long/url/address";
		String shortURL = "http://van.hack/M6N6HA";
		
		urlRepository.save(new URLModel(longURL, shortURL));
		
		List<URLModel> urls = urlRepository.findByLongURL(longURL);
		
		assertEquals(urls.get(0).getShortURL(), shortURL);
	}
	
}
