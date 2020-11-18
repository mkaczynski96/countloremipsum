package main;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountLoremIpsumServiceImpl implements CountLoremIpsum {

    private final WebClient webClient;
    private static final String BASE_URL_1 = "https://loripsum.net/api/";
    private static final String BASE_URL_2 = "long/plaintext";
    private static final String CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    public CountLoremIpsumServiceImpl() {
        webClient = WebClient
                .builder()
                .baseUrl(BASE_URL_1)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public int countLetters(int numberOfParagraphs, String characterToCount) {
        StringBuilder ab = new StringBuilder("/" + numberOfParagraphs + "/" + BASE_URL_2);
        WebClient.RequestBodySpec uri1 = webClient
                .method(HttpMethod.GET)
                .uri(ab.toString());

        String response2 = uri1
                .retrieve()
                .bodyToMono(String.class)
                .block();

        assert response2 != null;
        return filterLetters(response2, characterToCount);
    }

    @Override
    public int coundWords() {
        return 0;
    }

    private static int filterLetters(String text, String characterToCount) {
        return (int) text.chars()
                .filter(e -> e == characterToCount.charAt(0))
                .count();
    }
}
