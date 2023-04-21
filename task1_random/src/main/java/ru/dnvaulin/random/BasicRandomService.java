package ru.dnvaulin.random;

import java.util.Random;

public class BasicRandomService implements RandomService {
    private final Random random;

    public BasicRandomService() {
        long seed = System.nanoTime();
        seed = (System.nanoTime() * seed) ^ seed;
        this.random = new Random(seed);
    }

    @Override
    public Random getRandom() {
        return this.random;
    }

    @Override
    public String randomMethod() {
        return "Basic";
    }
}
