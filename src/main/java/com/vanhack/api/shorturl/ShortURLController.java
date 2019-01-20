package com.vanhack.api.shorturl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanhack.api.shorturl.data.URLModel;

@SpringBootApplication
@RestController
public class ShortURLController {

	private final ShortURLService service;

	@Autowired
	public ShortURLController(ShortURLService service) {
		this.service = service;
	}

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public URLModel index(@RequestParam(name = "url", required = true) String url,
			@RequestParam(name = "host", defaultValue = NewURL.FIXED_HOST) String host) {

		return this.service.getShort(url, host);
	}

	@GetMapping(path = "/forward")
	protected void forward(@RequestParam(name = "url", required = true) String url, HttpServletResponse response) {

		try {
			response.sendRedirect(this.service.getLong(url).getLongURL());

		} catch (Exception e) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ShortURLController.class, args);
	}
}
