package com.vanhack.api.shorturl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NewURLTest {

	@DisplayName("Must throw exception when argument is invalid")
	@Test
	void mustThrowExceptionWhenArgumentIsInvalid() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> NewURL.getShorter(null));
	    assertEquals(exception.getMessage(), "URL address is not valid. Please verify.");
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> NewURL.getShorter("http://www. vanhack.com"));
	    assertEquals(exception.getMessage(), "URL address is not valid. Please verify.");	
    }

	@DisplayName("Must return a short URL when argument is valid")
	@Test
	void mustReturnShortURLWhenArgumentIsValid(){
		assertEquals("http://van.hack/CYJeDw", NewURL.getShorter("http://www.vanhack.com/testing/a/long/url/address"));
		
		assertEquals("http://van.hack/JX73L4", NewURL.getShorter("http://www.vanhack.com/testing/a/different/url/address"));
		
		assertEquals("http://van.hack/2Upnbt", NewURL.getShorter("http://www.vanhack.com"));
	}
		
	@DisplayName("Must return a short URL when argument has a diferente host")
	@Test
	void mustReturnShortURLWhenArgumentHasADifferentHost(){
		assertEquals("http://foo.host/CYJeDw", NewURL.getShorter("http://www.vanhack.com/testing/a/long/url/address", "foo.host"));
		
		assertEquals("http://foo.host/JX73L4", NewURL.getShorter("http://www.vanhack.com/testing/a/different/url/address", "foo.host"));
		
		assertEquals("http://foo.host/2Upnbt", NewURL.getShorter("http://www.vanhack.com", "foo.host"));
	}
	
}
