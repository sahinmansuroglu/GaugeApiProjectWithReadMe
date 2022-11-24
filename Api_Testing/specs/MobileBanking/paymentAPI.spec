#Payment API test caseler

Created by testinium on 29.09.2021

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

## Get all categories from mobile app

* Loged in to mobil with API and stored AccessToken and RefreshToken
* Define new request
* Add base url "https://mbanking-test.ibar.az"
* Add endpoint "/api/v3/payment-svc/categories"
* Add Bearer token "AccessToken"
* Get request
* Check if status code is "200"
