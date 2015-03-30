package com.data;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.Scanner;

/**
 * Main class.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        JmsTemplate sender = context.getBean("jmsProducerTemplate", JmsTemplate.class);
        if (sender == null) {
            System.out.println("Sender is not loaded");
        }

        sender.send(session -> session.createTextMessage("Hola!!!"));

        System.out.println("Please press ENTER to quit...");
        try(Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
        }

        context.close();
    }
}