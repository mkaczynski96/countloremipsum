package com.mkaczynski.countloremipsum;

import com.mkaczynski.countloremipsum.gen.GetCountRequest;
import com.mkaczynski.countloremipsum.gen.GetCountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountLoremIpsumEndpoint {
    private static final String NAMESPACE_URI = "http://www.mkaczynski.com/countloremipsum/gen";

    private final GetCountRepository getCountRepository;

    @Autowired
    public CountLoremIpsumEndpoint(GetCountRepository getCountRepository) {
        this.getCountRepository = getCountRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRequest")
    @ResponsePayload
    public GetCountResponse getCountry(@RequestPayload GetCountRequest request) {
        GetCountResponse response = new GetCountResponse();
        response.setCount(5);

        return response;
    }
}
