package ru.dnvaulin.random;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

public class SecureRandomService implements RandomService {
    private final SecureRandom random;

    public SecureRandomService() {
        long seed = System.nanoTime();
        seed = (System.nanoTime() * seed) ^ seed;
        this.random = new SecureRandom(ByteBuffer.allocate(Long.BYTES).putLong(seed).array());
    }

    @Override
    public Random getRandom() {
        return this.random;
    }

    @Override
    public String randomMethod() {
        return "Secure";
    }
}
