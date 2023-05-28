package ru.dnvaulin.project.encrypt;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

public class RandomService {

    public Random getRandom(RandomType randomType) {
        long seed = System.nanoTime();
        seed = (System.nanoTime() * seed) ^ seed;
        return randomType == RandomType.BASIC ?
                new Random(seed) :
                new SecureRandom(ByteBuffer.allocate(Long.BYTES).putLong(seed).array());
    }

    public enum RandomType {
        BASIC,
        SECURE,
    }
}
