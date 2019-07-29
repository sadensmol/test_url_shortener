package me.sadensmol.urlshortener;

import me.sadensmol.urlshortener.controller.UrlController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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


    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Test
    public void diTest()    {
        assertThat(urlController).isNotNull();
        assertThat(restTemplate).isNotNull();
    }

    @Test
    public void test() throws Exception {
        String url = "http://www.ya.ru";
        String shortUrl = this.restTemplate.getForObject("http://localhost:"+port+"/shorten?url={url}", String.class, url);
        assertThat(shortUrl).isNotEmpty();

        URL testUrl = new URL(shortUrl);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("http://localhost:"+port+"/url?shortUrl="+shortUrl);

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl
                        (url));
    }

    //todo add unique test/ wrong url test etc...

}
