package com.drools.droolstry.service;

import com.drools.droolstry.config.DroolsBeanFactory;
import com.drools.droolstry.model.Letter;
import org.kie.api.runtime.KieSession;

public class LetterService {

    KieSession kieSession = new DroolsBeanFactory().getKieSession();

    public Letter signLetter(Letter letter){
        kieSession.insert(letter);
        kieSession.fireAllRules();
        System.out.println("This is letter "+letter.getLetter());
        return letter;
    }

    public Letter inputsignLetter(String letter){
        Letter letter1=new Letter(letter);

        kieSession.insert(letter1);
        kieSession.fireAllRules();
        System.out.println("This is letter "+letter1);
        return letter1;
    }
}
