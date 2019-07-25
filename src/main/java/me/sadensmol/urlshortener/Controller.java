package me.sadensmol.urlshortener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/")
public class Controller {


    @RequestMapping(method = RequestMethod.GET, value = "/shorten",produces = "text/plain")
    public String shortenUrl(@RequestParam("url") String url){
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost()
                    .getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return url + "Hello Spring Boot from " + hostname;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/url")
    public RedirectView processUrl(@RequestParam("shortUrl") String shortUrl, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
            String redirectUrlString = ""+shortUrl;
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://" + redirectUrlString);
            return redirectView;
    }


}
