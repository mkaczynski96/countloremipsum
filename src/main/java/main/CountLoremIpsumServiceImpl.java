package main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountLoremIpsumServiceImpl implements CountLoremIpsum {

    private static final String CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
    private final WebClient webClient;
    private final String BASE_URL_2;

    public CountLoremIpsumServiceImpl(@Value("${externalapi.BASE_URL_1}") String BASE_URL_1, @Value("${externalapi.BASE_URL_2}") String BASE_URL_2) {
        webClient = WebClient
                .builder()
                .baseUrl(BASE_URL_1)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
        this.BASE_URL_2 = BASE_URL_2;
    }

    @Override
    public int countLetters(int numberOfParagraphs, String characterToCount) {
        WebClient.RequestBodySpec uri = webClient
                .method(HttpMethod.GET)
                .uri("/" + numberOfParagraphs + BASE_URL_2);

        String response = uri
                .retrieve()
                .bodyToMono(String.class)
                .block();

        assert response != null;

        return filterLetters(response, characterToCount);
    }

    private static int filterLetters(String text, String characterToCount) {
        return (int) text.chars()
                .filter(e -> e == characterToCount.charAt(0))
                .count();
    }
}
