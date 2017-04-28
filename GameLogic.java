package game.alg.com.game;

import java.util.Random;
public class GameLogic {

    private static final char DIVIDER = '_';

    private String[] questions = {"Elephant", "Hippo", "Tiger", "Lion"};
    private String lastQuestion;

    public String getNextQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(questions.length);
        lastQuestion = questions[randomIndex];

        randomIndex = random.nextInt(lastQuestion.length());

        char[] wordArray = lastQuestion.toCharArray();
        wordArray[randomIndex] = DIVIDER;

        return String.valueOf(wordArray);
    }

    public boolean checkAnswer(Player player) {
        String playerAnswer = player.getQuestion().replace(DIVIDER, player.getAnswer());
        return playerAnswer.equalsIgnoreCase(lastQuestion);
    }
}
