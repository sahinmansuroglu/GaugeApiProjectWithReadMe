#Specification Heading

Created by testinium on 29.09.2021

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

##Add a new pet
* Add payload as file from resource "payloads/petPost.json"
* Add base url "https://petstore.swagger.io"
* Add relaxed HTTPS validation
* Add endpoint "/v2/pet"
* Add Headers
    |key            |value              |
    |---------------|-------------------|
    |accept         |application/json   |
    |Content-Type   |application/json   |
    |Cache-Control  |max-age=0          |
* Add log filter with errorStatus
    |Status |
    |500    |
    |400    |
    |405    |
* Post request
* Check if status code is "200"
//* Get "tags" from the body then convert it as list and store it with "tags_list" during the scenario



##Add a new pet and verify id and the tags.
* Add base url "https://petstore.swagger.io"
* Add relaxed HTTPS validation
* Add payload as file from resource "payloads/petPost.json"
* Add endpoint "/v2/pet"
* Add Headers
    |key            |value              |
    |---------------|-------------------|
    |accept         |application/json   |
    |Content-Type   |application/json   |
    |Cache-Control  |max-age=0          |
* Add log filter with errorStatus
    |Status |
    |500    |
    |400    |
    |405    |
* Post request
* Get "tags" from response and store it with "tags" during scenario
* Get "123" and "123" from scenario store and then compare, Are they equals?
* Get "id" from "tags" json list which one equals "name"="kuçukuçu", and store it during Scenario with "tagId"
* Get response time as milliseconds and compare it, is it less then "50000"?
* Sleep for "120" milliSecond
* Check if status code is "200"







