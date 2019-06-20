package com.drools.droolstry.model;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;


public class MessageLayout extends HorizontalLayout {
    Image image = new Image();
    Label textLabel = new Label();
    String from;

    public Image getImage() {
        return image;
    }

    public void setImage(String from, Image image) {
        this.image = image;
        image.setWidth(60, Sizeable.Unit.PIXELS);
        image.setHeight(60, Sizeable.Unit.PIXELS);
        addComponent(new Label(from));
        addComponent(image);
    }

    public void setText(String from, Label text) {
        this.textLabel = text;
        text.setValue(from + " " + text.getValue());
        textLabel.setWidth(50, Unit.PERCENTAGE);
        addComponent(textLabel);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
