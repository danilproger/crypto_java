package ru.dnvaulin.random;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomComposer {
    private final Map<String, RandomService> randomServices;

    public RandomComposer(List<RandomService> randomServices) {
        this.randomServices = randomServices.stream().collect(Collectors.toMap(RandomService::randomMethod, Function.identity()));
    }

    public int getRandomInt(String randomMethod, int minInclusive, int maxExclusive) {
        return Optional.ofNullable(randomServices.get(randomMethod))
                .orElseThrow(() -> new IllegalArgumentException("Неизвестный тип рандома: " + randomMethod))
                .getRandom().nextInt(maxExclusive - minInclusive) + minInclusive;
    }
}
