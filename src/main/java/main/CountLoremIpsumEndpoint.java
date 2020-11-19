package main;

import gen.GetCountRequest;
import gen.GetCountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountLoremIpsumEndpoint {
    private static final String NAMESPACE_URI = "gen";
    private static final Logger LOGGER = LoggerFactory.getLogger(CountLoremIpsumEndpoint.class);
    private final CountLoremIpsumRepository countLoremIpsumRepository;

    public CountLoremIpsumEndpoint(CountLoremIpsumRepository countLoremIpsumRepository) {
        this.countLoremIpsumRepository = countLoremIpsumRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRequest")
    @ResponsePayload
    public GetCountResponse getCount(@RequestPayload GetCountRequest request) {
        GetCountResponse response = new GetCountResponse();
        try {
            response.setCount(countLoremIpsumRepository.getCount(request.getNumberOfParagraphs(), request.getCharacterToCount()));
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            response.setCount(-1);
        }

        return response;
    }
}