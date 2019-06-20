//package com.drools.droolstry;
//
//import com.drools.droolstry.config.DroolsBeanFactory;
//import com.drools.droolstry.model.Letter;
//import org.kie.api.runtime.KieSession;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class RuleFiring {
//
//    private void main (String[] args){
//
//        List<String> alphabet = new ArrayList<>();
//        alphabet.add("A");
//        alphabet.add("B");
//        alphabet.add("C");
//        alphabet.add("D");
//        alphabet.add("E");
//        alphabet.add("F");
//        alphabet.add("G");
//
//        try {
//            KieSession kSession = new DroolsBeanFactory().getKieSession();
//
//            Scanner scanner = new Scanner(System.in);
//            String input = scanner.nextLine();
//
//            String[] strings = input.split(" ");
//            String letter = "";
//            for (String string : strings) {
//                if (string.length() == 1) {
//                    letter = string;
//                    for (String s : alphabet) {
//                        if (s.equals(letter.toUpperCase())) {
//                            String l = s.toUpperCase();
//                            Letter let = new Letter(l);
//                            kSession.insert(let);
////                            System.out.println("***the letter should be:" + l);
//                            System.out.println("************* Fire Rules **************");
//                            kSession.fireAllRules();
//                            System.out.println("************************************");
//                        }
//                        else System.out.println("nopoooo");
//                    }
//                }
//            }
//
//
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
//}
