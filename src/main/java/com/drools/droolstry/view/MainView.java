package com.drools.droolstry.view;

import com.drools.droolstry.config.DroolsBeanFactory;
import com.drools.droolstry.model.ChatMessage;
import com.drools.droolstry.model.Letter;
import com.drools.droolstry.model.MessageList;
import com.drools.droolstry.model.Result;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.catalina.security.SecurityUtil.remove;

@org.springframework.stereotype.Component
@Scope("session")
@UIScope
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements View {
    static final String VIEW_NAME = "";
    @Autowired
    UnicastProcessor<ChatMessage> publisher;
    @Autowired
    Flux<ChatMessage> messages;
    HorizontalLayout buttonGroup = new HorizontalLayout();
    private TextField usernameField = new TextField();
    private MessageList messageList = new MessageList();
    private List<String> alphabet = new ArrayList<>();
    private Button startButton = new Button("Start chatting");
    private TextField messageField = new TextField();
    private Button send = new Button("Send");
    private String username;
    private VerticalLayout layout = new VerticalLayout();
    private final Binder<String> binder = new Binder<>();


    public MainView() {
        Logger logger = LogManager.getLogger(MainView.class);
        logger.error("NET :: LEVEL :: NetApp ERROR Message ::");
        logger.warn("lolololo");
    }

    @PostConstruct
    void init() {
        alphabet.addAll(Arrays.asList(inputArray()));
        askUsername();
    }

    private void checkLetter(String input) {
        try {
            KieSession kSession = new DroolsBeanFactory().getKieSession();
            String[] strings = input.split(" ");
            String[] strings2 = input.split(" ");// asta e string pentru teste
            String letter = "";
            for (int i = 0; i < strings.length; i++) {
                strings2[i] = strings[i].concat(" ");
            }

            strings2 = (String[]) permutation(strings, 0).toArray();
            /////aici
            for (String string : strings) {
                letter = string;
                for (String s : alphabet) {
                    if (s.equals(letter.toUpperCase())) {
                        String l = s.toUpperCase();
                        Letter let = new Letter(l);
                        kSession.insert(let);
                        Result result = new Result();
                        kSession.setGlobal("resultForBot", result);
                        System.out.println("************* Fire Rules **************");
                        kSession.fireAllRules();
                        System.out.println("************************************");
                        Image image = new Image();
                        image.setSource(new com.vaadin.server.ExternalResource(result.getValue()));
                        if (string.length() != 1) {
                            publisher.onNext(new ChatMessage("Mika ", result.getValue()));
                        } else {
                            publisher.onNext(new ChatMessage("Mika ", image));
                        }
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void askUsername() {
        usernameField.setCaption("Enter username:");
        usernameField.focus();
        usernameField.setRequiredIndicatorVisible(true);
        new Binder<MainView>().forField(usernameField)
                .withValidator(str -> str.length() == 4, "Must be 4 chars");
        buttonGroup.addComponents(usernameField, startButton);
        buttonGroup.setComponentAlignment(startButton, Alignment.BOTTOM_LEFT);

        startButton.addClickListener(click -> {
            username = usernameField.getValue();
            removeComponent(buttonGroup);
            remove(buttonGroup);
            startChat();
        });

        addComponent(buttonGroup);
        setComponentAlignment(buttonGroup, Alignment.MIDDLE_CENTER);
    }

    public void startChat() {
        addComponents(messageList, createInputLayout());
        File userInput = new File("userInput.txt");

        messages.subscribe(m -> {
            UI.getCurrent().setPollInterval(500);
//            messageList.add(m.getFrom() + ": ", new Label(m.getMessage()));
            if (m.getMessage() instanceof String)
                messageList.add(m.getFrom() + ": ", new Label((String) m.getMessage()));
            else {
                Image image = new Image();
                image.setSource((com.vaadin.server.ExternalResource) m.getMessage());
                messageList.add(m.getFrom() + ": ", (image));
                m.getMessage();
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(userInput,true));
                writer.newLine();
                writer.append(m.getFrom()+":"+m.getMessage());
                writer.close();
            } catch (Exception e){
                Logger logger = LogManager.getRootLogger();
                logger.error("File not found");
            }
        });
    }

    private VerticalLayout createInputLayout() {
        send.addStyleName(ValoTheme.BUTTON_PRIMARY);
        send.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        messageField.setWidth("630");

        send.addClickListener(click -> {
            publisher.onNext(new ChatMessage(username, messageField.getValue()));
            checkLetter(messageField.getValue());
            messageField.clear();
            messageField.focus();
        });

        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.addComponents(messageField, send);
        layout.addComponent(hlayout);
        layout.setComponentAlignment(hlayout, Alignment.BOTTOM_CENTER);
        return layout;
    }

    private String[] inputArray() {
        String[] array = {"A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "P",
                "O", "Q", "R", "S", "T", "U", "V", "W", "X",
                "Y", "Z", "BYE", "HI", "THANKS", "MULȚUMESC",
                "MERSI", "LA REVEDERE", "END", "PA", "SALUT",
                "BUNA", "BUNĂ", "CARE ESTE SCOPUL TĂU",
                "CARE ESTE SCOPUL TĂU?", "CU CE MĂ POȚI AJUTA",
                "CE POȚI TU FACE", "CARE ESTE SCOPUL TAU?",
                "CU CE MA POTI AJUTA", "CE POTI TU FACE", "CUM STAI?",
                "CE FACI?", "CUM STAI", "CE FACI", "CF",
                "ESTI BAIAT SAU FATA?", "EȘTI BĂIAT SAU FATĂ?", "CE SEX AI?",
                "SPUNE-MI O GLUMĂ", "SPUNEMI O GLUMA", "SPUNEMI UN BANC",
                "ZI O GLUMA", "ZI O ANECDOTA"};
        return array;
    }

    String[] swap(String[] array, int pos1, int pos2) {
        String[] tempArray = array;
        String temp = tempArray[pos1];
        tempArray[pos1] = tempArray[pos2];
        tempArray[pos2] = temp;
        return tempArray;
    }

    public List<String> permutation(String startArray[], int start) {
        List<String> finalList = Arrays.asList(startArray);
        if (start != 0) {
            for (int i = 0; i < start; i++)
                System.out.print(startArray[i]);
            System.out.println();
        }

        for (int i = start; i < startArray.length; i++) {
            swap(startArray, start, i);
            permutation((String[]) finalList.toArray(), start + 1);
            swap(startArray, start, i);
        }
        return finalList;
    }
}

