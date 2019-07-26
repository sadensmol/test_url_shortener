package me.sadensmol.urlshortener.repository;

import me.sadensmol.urlshortener.model.URL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository  extends CrudRepository<URL, Long> {
}
