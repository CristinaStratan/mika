package com.drools.droolstry.model;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class MessageList extends VerticalLayout {
    List<ChatMessage> messages = new ArrayList<>();
    Grid<ChatMessage> grid = new Grid<>();

    public MessageList() {
        grid.addComponentColumn(probe -> probe.getComponent()).setId("main-column");
        grid.addStyleName("v-grid");
        grid.addStyleName("no-stripes");
        grid.getColumn("main-column").setSortable(false);
        grid.setRowHeight(60.0);
        grid.setHeight("350");
        grid.setWidth(70, Unit.PERCENTAGE);
        grid.setHeaderVisible(false);

        addComponent(grid);
        setComponentAlignment(grid, Alignment.TOP_CENTER);
    }

    public void add(String from, Label component) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setComponent(from, component);
        messages.add(chatMessage);
        grid.setItems(messages);
        grid.scrollToEnd();
    }

    public void add(String from, Image image) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setComponent(from, image);
        messages.add(chatMessage);
        grid.setItems(messages);
        grid.scrollToEnd();
    }

    public void deleteAll() {
        super.removeAllComponents();
    }

    public void delete(Component component) {
        super.removeComponent(component);
    }


}
