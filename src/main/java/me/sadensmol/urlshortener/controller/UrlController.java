package me.sadensmol.urlshortener.controller;

import io.seruco.encoding.base62.Base62;
import me.sadensmol.urlshortener.model.URL;
import me.sadensmol.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlRepository urlRepository;
    private final Base62 base62 = Base62.createInstance();

    @Autowired
    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/shorten",produces = "text/plain")
    public String shortenUrl(@RequestParam("url") String url){
         URL intUrl = new URL();
         intUrl.setFullUrl(url);

        URL savedUrl = urlRepository.save(intUrl);

        String encodedString = new String(base62.encode(String.valueOf( savedUrl.getId()).getBytes()));

        return "http://url_shortener/"+ encodedString;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/url")
    public RedirectView processUrl(RedirectAttributes redirectAttributes, @RequestParam("shortUrl") String shortUrl, HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.net.URL intUrl;
        intUrl = new java.net.URL(shortUrl);

        String shortUrlPath = intUrl.getPath().replace("/", "");
        String id = new String(base62.decode(shortUrlPath.getBytes()));;

        Optional<URL> fullUrl;

        try {
            fullUrl = urlRepository.findById(Long.parseLong(id));
        }catch (Exception e) {
            throw new Exception("Cannot find short url :" + shortUrl);
        }

        if (!fullUrl.isPresent()) throw new Exception("Url is missed :" + shortUrl);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(fullUrl.get().getFullUrl());
        return redirectView;
    }

    @ExceptionHandler(Exception.class)
    public String onException(Exception e, HttpServletRequest request) {
        return "Error occurred during the executing request : " + e.getMessage();
    }

}
