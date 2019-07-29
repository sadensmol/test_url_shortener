** Url Shortener **

Shortens source url and returns the shorten one

When you call it with shortener url it redirects you to the original (full) url

to run use  'mvn spring-boot:run' command

you can call : localhost:8080/shorten?url=<your url> with GET request and it will answer you with a shorten url

to redirect from short url to the full url you can call: 
localhost:8080/url?shortUrl=<your short url> with POST request and it will redirect you to a full url

The project doesn't have any additional web functionality like authentification, SSL, etc.
The project doesn't have multithreading support.



Unit tests cover only the main business logic (min amount of tests).


*** Url shortener algorithm ***

From wiki [https://en.wikipedia.org/wiki/URL_shortening]

There are several techniques to implement a URL shortening. 
Keys can be generated in base 36, assuming 26 letters and 10 numbers. 
In this case, each character in the sequence will be 0, 1, 2, ..., 9, a, b, c, ..., y, z. 
Alternatively, if uppercase and lowercase letters are differentiated, then each character can represent a single digit within a number of base 62 (26 + 26 + 10). 
In order to form the key, a hash function can be made, or a random number generated so that key sequence is not predictable.

In our solution we will use full list of numbers/chars: 0..9, A-Z, a-z

Every URL in the database will be added with primary id and we will use base10 to base64 converter for shortening the url.
depends on url's size in database we could enlarge number of chars in shortened url.

I took standard base10-base62 converter library instead of writing my own.

*** Original test requirements ***
URL shortener service.
Url shortener services are used to create short links from the source URLs that will still redirect to the same page. (eg. goo.gl, tinyurl.com, and bit.ly)

Your task is to implement a service with a single endpoint that takes a big URL and response with a shortened version.

Requirements:

Programming language: Kotlin/Java
Clean code covered with unit tests
Please don't bother yourself with database selection and use some embedded DB like h2
Should use Spring Boot as well.

Please include a small description of the algorithm and your considerations about the approach you chose.

Code can be deployed to GitHub or sent as a zip archive with all necessary scripts, configs etc. We also expect that source code contains a script or instruction on how to run the service.

As a final result we expect a small microservice that we can run locally. When invoking a registered endpoint to create short link we should receive the shortened URL. When sending the shortened URL to another endpoint we expect that the service redirects to the desired destination URL.

 