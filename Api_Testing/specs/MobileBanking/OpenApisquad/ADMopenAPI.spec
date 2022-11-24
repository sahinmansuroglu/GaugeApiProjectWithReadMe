Specification Heading
=====================
Created by 004577 on 18-Mar-22

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     

## Get Accounts ADM

* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/accounts"
* Add body as file from resource "payloads/AdmAccounts.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## Get Accounts ADM if tin is empty 
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/accounts"
* Add body as file from resource "payloads/AdmAccountsEmptyTin.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## Get Accounts ADM if cif is empty 
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/accounts"
* Add body as file from resource "payloads/AdmAccountsEmptyCif.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## Get Accounts ADM if tin is incorrect
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/accounts"
* Add body as file from resource "payloads/AdmAccountsTinIncorrect.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## Get Accounts ADM if cif is incorrect 
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/accounts"
* Add body as file from resource "payloads/AdmAccountsCifIncorrect.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## Get Accounts ADM if cif and tin are incorrect
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/accounts"
* Add body as file from resource "payloads/AdmAccountsDataIncorrect.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## ADM Auth happy path (terminalId ABB0001)
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthHappyPath.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## ADM Auth if accountNumber is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyAccount.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"


## ADM Auth if agentCode is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyAgentcode.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if birthdate is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyBirth.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if cif is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyCif.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if customerType is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyCustomer.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if fullName is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyFullname.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if phoneNumber is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyPhone.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if pin is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyPin.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if purpose is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyPurpose.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if terminalId is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyTerminal.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## ADM Auth if terminalId is ABB0002
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthTerminalABB002.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## ADM Auth if terminalId is not ABB0001 and ABB0002
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthTerminalABB003.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "200"

## ADM Auth if tin is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyTin.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Auth if transactionType is empty
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/auth"
* Add body as file from resource "payloads/AdmAuthEmptyTransaction.json"
* Add as a header "Content-Type" = "application/json"
* Post request
* Check if status code is "400"

## ADM Payment
* Define new request
* Add base url "http://services-test.apps.test.ocp.ibar.az"
* Add endpoint "/adm-ms/payment"
* Add body as file from resource "payloads/AdmPayment.json"
* Add as a header "Content-Type" = "application/json"
* Put request
* Check if status code is "200"