package ru.dnvaulin.random;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new UnsupportedOperationException("Для использования введите два аргумента: Имя и тип рандома [Basic, Secure]");
        }

        String userName = args[0];
        String randomMethod = args[1];

        RandomComposer composer = new RandomComposer(List.of(new SecureRandomService(), new BasicRandomService()));
        String[] messages = {"У вас сегодня будет удача в дела!", "Сегодня хороший день для саморазвития!"};

        RandomMessageService service = new RandomMessageService(messages, composer);
        System.out.println(service.getMessage(randomMethod, userName));
    }
}
