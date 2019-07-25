** Url Shortener **

Shortens source url and returns the shorten one

When you call it with shortener url it redirects you to the original (full) url

to run use  'mvn spring-boot:run' command

you can call : localhost:8080/shorten?url=<your url> with GET request and it will answer you with a shorten url

to redirect from short url to the full url you can call: 
localhost:8080/url?shortUrl=<your short url> with POST request and it will redirect you to a full url


The project doesn't have any additional web functionality like authentification, SSL, etc.

Unit tests cover only the main business logic.


*** Url shortener algorithm ***

From wiki [https://en.wikipedia.org/wiki/URL_shortening]

There are several techniques to implement a URL shortening. 
Keys can be generated in base 36, assuming 26 letters and 10 numbers. 
In this case, each character in the sequence will be 0, 1, 2, ..., 9, a, b, c, ..., y, z. 
Alternatively, if uppercase and lowercase letters are differentiated, then each character can represent a single digit within a number of base 62 (26 + 26 + 10). 
In order to form the key, a hash function can be made, or a random number generated so that key sequence is not predictable.

In our solution we will use full list of numbers/chars: 0..9, A-Z, a-z



 