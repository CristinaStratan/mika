package com.drools.droolstry;

import com.drools.droolstry.model.ChatMessage;
import com.vaadin.ui.VerticalLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@SpringBootApplication
public class DroolstryApplication extends VerticalLayout {

    public static void main(String[] args) {
        SpringApplication.run(DroolstryApplication.class, args);
    }

    @Bean
    UnicastProcessor<ChatMessage> publisher() {
        return UnicastProcessor.create();
    }

    @Bean
    Flux<ChatMessage> messages(UnicastProcessor<ChatMessage> publisher) {
        Flux<ChatMessage> chatMessageFlux = publisher
                .replay( 30)
                .autoConnect();
        return chatMessageFlux;
    }
}
