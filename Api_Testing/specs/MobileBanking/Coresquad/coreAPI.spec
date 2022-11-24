Specification Heading
=====================
Created by 004577 on 18-Mar-22
    //Specification Heading dedigi cucumberdaki feature gibi dusun

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

     //buraya yazdigim herseyi tum senaryolar oncesinde calistirir
     //mesela base path im tum senaryolarda ayniysa ekleme stepini buraya koyabiliriz
     //before after gibi dusunebilirsin
Send notification to user
----------------

* Loged in to mobil with API and stored AccessToken and RefreshToken
* Define new request
* Add base url "http://ibam-backend-test.apps.test.ocp.ibar.az"
* Add endpoint "/api/v1/notification-svc/notifications"
* Add body as file from resource "payloads/SendNotification.json"
* Add as a header "Content-Type" = "application/json"
* Add Bearer token "AccessToken"
* Post request
* Check if status code is "200"
