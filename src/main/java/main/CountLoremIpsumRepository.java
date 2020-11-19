package main;

import org.springframework.stereotype.Repository;

@Repository
public class CountLoremIpsumRepository {
    private final CountLoremIpsumService countLoremIpsumService;

    public CountLoremIpsumRepository(CountLoremIpsumService countLoremIpsumService) {
        this.countLoremIpsumService = countLoremIpsumService;
    }

    public int getCount(int numberOfParagraphs, String characterToCount) {
        return countLoremIpsumService.countLetters(numberOfParagraphs, characterToCount);
    }
}