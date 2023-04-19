package belous.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final static int BORDERS_THICKNESS = 5;
    private final static int WIDTH = 250;
    private final static int HEIGHT = 600;
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

    private static class HelpLabel extends JLabel {
        private final static String MESSAGE = """
                <html><style>
                p {text-align: center;}
                </style>
                <br><p>
                Controls:<br>
                arrows -- move<br>
                X -- rotate<br>
                     counterclockwise<br>
                Z -- rotate<br>
                     clockwise""";

        public HelpLabel(int horizontalAlignment) {
            super(MESSAGE, horizontalAlignment);
            this.setForeground(Color.white);
            this.setFont(new Font("OCR A Extended", Font.BOLD, 15));
            this.setBackground(Color.BLACK);
            this.setOpaque(true);
        }
    }

    InfoPanel() {
        setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDERS_THICKNESS));
        setBackground(Color.BLACK);
        scoreLabel = new ScoreLabel(0, JLabel.CENTER);
        add(scoreLabel);
        JLabel emptyLabel = new JLabel("empty");
        emptyLabel.setPreferredSize(new Dimension(0, 400));
        emptyLabel.setBackground(Color.BLACK);
        emptyLabel.setOpaque(true);
        add(emptyLabel);
        HelpLabel helpLabel = new HelpLabel(JLabel.CENTER);
        add(helpLabel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

}
