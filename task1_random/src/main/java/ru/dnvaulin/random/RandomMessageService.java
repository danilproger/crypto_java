package ru.dnvaulin.random;

public class RandomMessageService {
    private final String[] messages;
    private final RandomComposer randomComposer;

    public RandomMessageService(String[] messages, RandomComposer randomComposer) {
        this.messages = messages;
        this.randomComposer = randomComposer;
    }

    public String getMessage(String randomMethod, String userName) {
        int messageIndex = randomComposer.getRandomInt(randomMethod, 0, messages.length);
        String message = messages[messageIndex];
        return userName + ", ваш прогноз: " + message;
    }
}
