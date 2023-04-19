package belous.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final static int BORDERS_THICKNESS = 5;
    private final static int WIDTH = 250;
    private final static int HEIGHT = 250;
    private final ScoreLabel scoreLabel;

    public void updateScore(int score) {
        scoreLabel.setScore(score);
    }

    private static class ScoreLabel extends JLabel {
        private final static String MESSAGE = """
                <html><style>
                p {text-align: center;}
                </style>
                <br><p>Your score:<br>""";

        private static String scoreToString(int score) {
            String res = Integer.toString(score);
            return "0".repeat(7 - res.length()) + res;
        }

        public ScoreLabel(int score, int horizontalAlignment) {
            super(MESSAGE + scoreToString(score), horizontalAlignment);
            this.setForeground(Color.white);
            this.setFont(new Font("OCR A Extended", Font.BOLD, 25));
            this.setBackground(Color.BLACK);
            this.setOpaque(true);
        }

        public void setScore(int score) {
            this.setText(MESSAGE + scoreToString(score));
        }
    }

    InfoPanel() {
        setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDERS_THICKNESS));
        setBackground(Color.BLACK);
        scoreLabel = new ScoreLabel(0, JLabel.CENTER);
        add(scoreLabel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

}
