package ru.he;

import ru.he.interfaces.JlmqConnector;
import ru.he.interfaces.JlmqConsumer;
import ru.he.interfaces.JlmqProducer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JlmqConnector connector = Jlmq.connector()
                .usingStomp(false)
                .port("80")
                .connect();

        JlmqProducer producer = connector.producer()
                .toQueue("documents_for_generate")
                .create();

        JlmqConsumer consumer = connector.consumer()
                .subscribe("documents_for_generate")
                .onReceive(message -> {
                    message.accepted();
                    System.out.println("handling message: ");
                    System.out.println(message.getBody());
                    message.completed();
                })
                .create();


        while (scanner.hasNext()) {

            producer.send(scanner.nextLine());

        }
    }

}
