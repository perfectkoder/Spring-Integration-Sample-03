package com.designthink.integration.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class SpringIntegrationDemoMain implements ApplicationRunner {

    @Autowired
    @Qualifier("inputChannel")
    private final DirectChannel inputChannel;

    public SpringIntegrationDemoMain(DirectChannel inputChannel) {
        this.inputChannel = inputChannel;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationDemoMain.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Message<String> message = MessageBuilder.withPayload("Hello-World from the first integration : ")
                .setHeader("headerName", "headerValue").build();
        MessagingTemplate messagingTemplate = new MessagingTemplate();
        System.out.println(messagingTemplate.sendAndReceive(inputChannel, message));
    }
}
