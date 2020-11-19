# countloremipsum


<b>Count Lorem Ipsum API</b>


SOAP API provides ability to operate on lorem ipsum text. 
It is using an external API from <i>loremipsum.net</i>, which provides plain text of lorem ipsum. 
External API is consumed by WebClient.


The first method is counting letters in the text. User sends a request with the number of paragraphs to generate, and the letter to count. API responses with a count number.


For example: 


<b>REQUEST.XML</b>

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="gen">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:getCountRequest>
            <gs:numberOfParagraphs>100</gs:numberOfParagraphs>
            <gs:characterToCount>g</gs:characterToCount>
        </gs:getCountRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

<b>TERMINAL</b> 

```
curl --header "content-type: text/xml" -d @./src/test/request.xml http://localhost:8080/ws  | xmllint --format -

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   594  100   234  100   360    144    221  0:00:01  0:00:01 --:--:--   366
<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:getCountResponse xmlns:ns2="gen">
      <ns2:count>468</ns2:count>
    </ns2:getCountResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


<b> HOW TO RUN </b>
Run project in Intellij IDEA (until I wont create binary) and put this command into terminal (only for UNIX based systems):
>curl --header "content-type: text/xml" -d @./src/test/request.xml http://localhost:8080/ws  | xmllint --format -

Command should be runned into project directory. Request file is in ./src/test/request.xml path.
