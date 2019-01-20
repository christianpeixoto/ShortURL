package com.vanhack.api.shorturl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ShortURLControllerTest {

	@Autowired
    private MockMvc mvc;

	@DisplayName("Get Short URL when passes a URL as parameter")
    @Test
    public void getShortURL() throws Exception {
        mvc.perform(MockMvcRequestBuilders
        				.get("/")
        				.param("url", "http://www.vanhack.com/testing/a/long/url/address"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"longURL\":\"http://www.vanhack.com/testing/a/long/url/address\",\"shortURL\":\"http://van.hack/CYJeDw\"}")));
    }
	
	@DisplayName("Get Short URL when passes a URL and a host as parameters")
	@Test
	public void getShortURLWithHost() throws Exception {
		mvc.perform(MockMvcRequestBuilders
						.get("/")
						.param("url", "http://www.vanhack.com/testing/a/long/different/address")
						.param("host", "foo.host"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"longURL\":\"http://www.vanhack.com/testing/a/long/different/address\",\"shortURL\":\"http://foo.host/5fPnyF\"}")));
	}

	@DisplayName("Redirect to long URL when receives short URL as argument")
    @Test
    public void redirectToLongURL() throws Exception {
        mvc.perform(MockMvcRequestBuilders
						.get("/")
						.param("url", "http://www.vanhack.com/testing/a/long/url/address"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(equalTo("{\"longURL\":\"http://www.vanhack.com/testing/a/long/url/address\",\"shortURL\":\"http://van.hack/CYJeDw\"}")));
		
        mvc.perform(MockMvcRequestBuilders
        				.get("/forward")
        				.param("url", "http://van.hack/CYJeDw"))
                .andExpect(status().isFound());
    }
	
}
