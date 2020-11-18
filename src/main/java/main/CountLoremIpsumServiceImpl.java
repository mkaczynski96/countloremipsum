package main;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountLoremIpsumServiceImpl implements CountLoremIpsum {

    private final WebClient webClient;

    public CountLoremIpsumServiceImpl() {
        webClient = WebClient
                .builder()
                .baseUrl("https://loripsum.net/api/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public int countLetters(int numberOfParagraphs, String characterToCount) {
        //    WebClient.UriSpec<WebClient.RequestBodySpec> request = webClient.post();

        final String baseUrl2 = "long/plaintext";
        StringBuilder ab = new StringBuilder("/" + numberOfParagraphs + "/" + baseUrl2);
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
