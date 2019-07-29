package me.sadensmol.urlshortener;

import me.sadensmol.urlshortener.model.URL;
import me.sadensmol.urlshortener.repository.UrlRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUrlRepository {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void urlRepositoryTest() {
        assertThat(urlRepository).isNotNull();
    }

    @Test
    public void findUrlById() {
        String fullUrl = "www.ya.ru";
        URL savedUrl = urlRepository.save(new URL(fullUrl));
        Optional<URL> byId = urlRepository.findById(savedUrl.getId());

        Assert.assertTrue(byId.isPresent());
        Assert.assertTrue(byId.get().getFullUrl().equals(fullUrl));
    }
}
