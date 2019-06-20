package com.drools.droolstry.handler;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.AbstractComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.vaadin.server.AbstractErrorMessage.getErrorMessageForException;

public class ErrorHandler extends DefaultErrorHandler {

    private static final Logger logger = LogManager.getLogger(ErrorHandler.class);

    public void error(ErrorEvent event) {
        // Finds the original source of the error/exception
        AbstractComponent component = DefaultErrorHandler.findAbstractComponent(event);
        if (component != null) {
            ErrorMessage errorMessage = getErrorMessageForException(event.getThrowable());
            if (errorMessage != null) {
                component.setComponentError(errorMessage);
                logger.error("Error happened " + event.getThrowable().getMessage(), event.getThrowable());
                return;
            }
        }
        DefaultErrorHandler.doDefault(event);
    }

}
