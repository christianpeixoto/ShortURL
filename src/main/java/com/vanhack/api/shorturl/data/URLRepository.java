package com.vanhack.api.shorturl.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends CrudRepository<URLModel, Long> {

	List<URLModel> findByShortURL(String shortURL);
	
	List<URLModel> findByLongURL(String longURL);
	
}
