package ru.digitalhabbits.homework1.plugin;

final class TestData {
    public static final String TEXT1 = "Pack my box\nwith five dozen\nliquor jugs";

    public static final String TEXT2 = "Jived fox nymph grabs quick waltz";

    public static final String TEXT3 = "A jovial swain should not complain\n" +
            "Of any buxom fair\n" +
            "Who mocks his pain and thinks it gain\n" +
            "To quiz his awkward air";

    public static final String TEXT4 = "The Hypertext Transfer Protocol (HTTP) is an application layer protocol for distributed,\n" +
            "collaborative, hypermedia information systems. HTTP is the foundation of data communication for the World Wide Web,\n" +
            "where hypertext documents include hyperlinks to other resources that the user can easily access, for example by a mouse click or by tapping the screen in a web browser.";

    static class TestDataHolder {
        private final String text;
        private final int lines;
        private final int words;
        private final int letters;

        public TestDataHolder(String text, int lines, int words, int letters) {
            this.text = text;
            this.lines = lines;
            this.words = words;
            this.letters = letters;
        }

        public String getText() {
            return text;
        }

        public int getLines() {
            return lines;
        }

        public int getWords() {
            return words;
        }

        public int getLetters() {
            return letters;
        }
    }
}
