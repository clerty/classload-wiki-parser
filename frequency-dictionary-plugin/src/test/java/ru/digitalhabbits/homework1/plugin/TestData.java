package ru.digitalhabbits.homework1.plugin;

import java.util.Map;

final class TestData {
    public static final String TEXT1 = "Two little birds\n" +
            "Sitting on a wall,\n" +
            "One called Peter,\n" +
            "One called Paul.\n" +
            "Fly away Peter,\n" +
            "Fly away Paul,\n" +
            "Come back Peter,\n" +
            "Come back Paul.";

    public static final String TEXT2 = "Fall is fun.\n" +
            "I like to run.\n" +
            "I see the sun.\n" +
            "Fall is fun!";

    static class TestDataHolder {
        private final String text;
        private final Map<String, Integer> frequency;

        public TestDataHolder(String text, Map<String, Integer> frequency) {
            this.text = text;
            this.frequency = frequency;
        }

        public String getText() {
            return text;
        }

        public Map<String, Integer> getFrequency() {
            return frequency;
        }
    }
}
