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

    private final CountLoremIpsumServiceImpl countLoremIpsumService;

    public CountLoremIpsumEndpoint(CountLoremIpsumServiceImpl countLoremIpsumService) {
        this.countLoremIpsumService = countLoremIpsumService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRequest")
    @ResponsePayload
    public GetCountResponse getCount(@RequestPayload GetCountRequest request) {
        GetCountResponse response = new GetCountResponse();

        response.setCount(countLoremIpsumService.countLetters(request.getNumberOfParagraphs(), request.getCharacterToCount()));

        return response;
    }
}