Specification Heading
======================
Created by testinium on 29.09.2021

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
//git bu siteye ve try out diyip endpointini al!!
//get value stebi icinde jsonpath yaziyoruz!
//kendimiz filter ekleyerek console a log bastiriyoruz ismine log diyebiliriz error kodlari vererek
//posttan onceye koyduk cunku postuda almasini istedik
* Add base url "https://petstore.swagger.io"
Scenario Heading
---------------
* Add endpoint "/v2/pet"
* Add as a header "content-type" = "application/json"
* Add payload as file from resource "payloads/addPost.json"
* Add log filter with errorStatus
    |status codes|
    |200|
    |405|
    |415|
* Post request
* Check if status code is "200"
* Get value with "tags[0].name" from response and verify it is equal with "mert"