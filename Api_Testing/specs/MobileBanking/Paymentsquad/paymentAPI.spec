#Payment API test caseler

Created by testinium on 29.09.2021

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

## Get all categories from mobile app

* Loged in to mobil with API and stored AccessToken and RefreshToken
* Define new request
* Add base url "https://mbanking-test.ibar.az"
* Add endpoint "/api/v3/payment-svc/categories"
* Add as a header "X-App-Version" = "3.2"
* Add Bearer token "AccessToken"
* Get request
* Check if status code is "200"


## Get all merchants from mobile app

* Loged in to mobil with API and stored AccessToken and RefreshToken
* Define new request
* Add base url "https://mbanking-test.ibar.az"
* Add endpoint "/api/v3/payment-svc/categories/13/merchants"
* Add Bearer token "AccessToken"
* Get request
* Check if status code is "200"


## Get Azercell Customer info

* Loged in to mobil with API and stored AccessToken and RefreshToken
* Define new request
* Add base url "https://mbanking-test.ibar.az"
* Add endpoint "/api/v3/payment-svc/customer-info"
* Add body as file from resource "payloads/paymentAPI.json"
* Add as a header "Content-Type" = "application/json"
* Add Bearer token "AccessToken"
* Post request
* Check if status code is "200"




