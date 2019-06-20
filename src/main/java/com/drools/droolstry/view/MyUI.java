package com.drools.droolstry.view;

import com.drools.droolstry.handler.ErrorHandler;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.NavigationStateManager;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Theme("colored")
@SpringUI
@Push
@SpringViewDisplay
@SpringBootApplication
public class MyUI extends UI implements ViewDisplay {
    protected static final String MAINVIEW = "";
    Navigator navigator;
    @Autowired
    MainView mainView;
    private CssLayout cssLayout = new CssLayout();
    private VerticalLayout verticalLayout = new VerticalLayout();
    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("MIKA Chatbot");
        cssLayout.setSizeFull();
        setContent(cssLayout);

        NavigationStateManager stateManager = new Navigator.UriFragmentManager(Page.getCurrent());
        stateManager.setState(MainView.VIEW_NAME);

        setSizeFull();
        final Label h1 = new Label("MIKA ChatBot");
        h1.addStyleName(ValoTheme.LABEL_H1);
        h1.addStyleName("textFormat");
        h1.setHeight("50");
//        h1.setIcon(new ThemeResource("https://core3.imgix.net/59afd70fd1abalogo1250x250.png"));

        navigator = new Navigator(this, this::showView);
        navigator.addView(MAINVIEW, mainView);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(h1);
        horizontalLayout.setStyleName("backgroundColor");
        horizontalLayout.setSizeFull();
        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);
        horizontalLayout.setHeight("20%");

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();

        verticalLayout.addComponents(horizontalLayout, springViewDisplay);
        verticalLayout.setSpacing(false);
        verticalLayout.setMargin(false);

        Label lFooter = new Label("@Copyright MIKA Chatbot created by Cristina Stratan");
        lFooter.addStyleName("mylabelstyle");
        lFooter.setSizeFull();

        cssLayout.addComponents(verticalLayout, lFooter);

        UI.getCurrent().setErrorHandler(new ErrorHandler());
    }


    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}
