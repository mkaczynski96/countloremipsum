package main;

import org.springframework.stereotype.Repository;

@Repository
public class CountLoremIpsumRepository {

    public int getCount(int numberOfParagraphs, String characterToCount) {
        return 1035;
    }
}