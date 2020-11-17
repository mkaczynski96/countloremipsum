package main;

import gen.GetCountRequest;
import gen.GetCountResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountLoremIpsumEndpoint {
    private static final String NAMESPACE_URI = "gen";

    private final CountLoremIpsumRepository getCountRepository;

    public CountLoremIpsumEndpoint(CountLoremIpsumRepository getCountRepository) {
        this.getCountRepository = getCountRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRequest")
    @ResponsePayload
    public GetCountResponse getCount(@RequestPayload GetCountRequest request) {
        GetCountResponse response = new GetCountResponse();
        response.setCount(5);

        return response;
    }
}