package com.drools.droolstry.model;

import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

import java.time.LocalDateTime;

public class ChatMessage {
    MessageLayout messageLayout = new MessageLayout();
    private String from;
    private LocalDateTime time;
    private String message;
    private Image image;

    public ChatMessage(String from, String message) {
        this.from = from;
        this.message = message;
        this.time = LocalDateTime.now();
        messageLayout.setText(from, new Label(message));

    }

    public ChatMessage(String from, Image message) {
        this.from = from;
        this.image = message;
        this.time = LocalDateTime.now();
        messageLayout.setImage(from, message);

    }

    public ChatMessage() {
    }

    public MessageLayout getComponent() {
        return messageLayout;
    }


    public void setComponent(String from, Label component) {
//        this.image.setAlternateText(component.getValue());
        messageLayout.setText(from, component);

    }

    public void setComponent(String from, Image component) {
//        this.image = component;
        messageLayout.setImage(from, component);

    }

    public String getFrom() {
        return from;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Object getMessage() {

        if (image != null) {
            return image.getSource();
        } else if (message != null) {
            return message;

        } else {
            System.out.println("ambele obiecte is null");
            return null;
        }

    }
}