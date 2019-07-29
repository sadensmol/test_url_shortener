package me.sadensmol.urlshortener;

import me.sadensmol.urlshortener.controller.UrlController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUrlController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UrlController urlController;

    @Test
    public void diTest()    {
        assertThat(urlController).isNotNull();
        assertThat(restTemplate).isNotNull();
    }

    @Test
    public void test() throws MalformedURLException {
        String url = "http://www.ya.ru";
        String shortUrl = this.restTemplate.getForObject("http://localhost:"+port+"/shorten?url={url}", String.class, url);
        assertThat(shortUrl).isNotEmpty();

        URL testUrl = new URL(shortUrl);

        String fullUrl = this.restTemplate.getForObject("http://localhost:"+port+"/url?shortUrl={shortUrl}", String.class, shortUrl);
        assertThat(fullUrl).isNotEmpty();
        assertThat(fullUrl).isEqualTo("redirect:redirect?url="+url);

    }

    //todo add unique test/ wrong url test etc...

}
