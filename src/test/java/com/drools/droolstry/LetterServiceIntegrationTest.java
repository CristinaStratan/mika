package com.drools.droolstry;

import com.drools.droolstry.model.Letter;
import com.drools.droolstry.service.LetterService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LetterServiceIntegrationTest {
    private LetterService letterService;

    @Before
    public void setup(){letterService= new LetterService();}

    @Test
    public void whenLetterMatching(){
        Letter letter = new Letter("A");
        letterService.signLetter(letter);
        assertEquals("A",letter.getLetter());
    }
}
