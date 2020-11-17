package main;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Scanner;

@Service
public class CountLoremIpsumServiceImpl implements CountLoremIpsum{
    public CountLoremIpsumServiceImpl() {
        run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello in Count-LoremIpsum");
        System.out.println("\nEnter the number of paragraphs to generate: ");
        int k = scanner.nextInt();


        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter char to count: ");
        char g = sc.next().charAt(0);


        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://loripsum.net/api/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        WebClient.UriSpec<WebClient.RequestBodySpec> request = webClient.post();

        final String baseUrl2 = "long/plaintext";
        StringBuilder ab = new StringBuilder("/" + k + "/" + baseUrl2);
        WebClient.RequestBodySpec uri1 = webClient
                .method(HttpMethod.GET)
                .uri(ab.toString());

        String response2 = uri1
                .retrieve()
                .bodyToMono(String.class)
                .block();

        long m = response2.chars()
                .filter(e -> e == g)
                .count();
        System.out.println(m);
    }

    @Override
    public int countLetters() {
        return 0;
    }

    @Override
    public int coundWords() {
        return 0;
    }
}
