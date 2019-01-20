# ShortURL
My UrlShortener answer

1) Get short URL when passes a URL as parameter
http://localhost:8080/?url=http://www.vanhack.com/testing/a/long/url/address

JSON response: 
{
"longURL": "http://www.vanhack.com/testing/a/long/url/address",
"shortURL": "http://van.hack/CYJeDw"
}

2) Get short URL when passes a URL and a host as parameters
http://localhost:8080/?url=http://www.vanhack.com/testing/a/long/different/address&host=foo.com

JSON response:
{
"longURL": "http://www.vanhack.com/testing/a/long/different/address",
"shortURL": "http://foo.com/5fPnyF"
}

3) Redirect to long URL when receives short URL as argument
http://localhost:8080/forward?url=http://van.hack/CYJeDw

Redirects to: http://www.vanhack.com/testing/a/long/url/address

## Compilation:
mvn install dockerfile:build
