package belous.tetris.ui;

import belous.tetris.game.api.Move;
import belous.tetris.game.api.QueuePlayer;
import belous.tetris.game.api.TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class UILayout {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setBackground(Color.BLACK);

        final BlockingQueue<Move> queue = new ArrayBlockingQueue<>(20);

        final InfoPanel infoPanel = new InfoPanel();
        pane.add(infoPanel);
        pane.add(Box.createRigidArea(new Dimension(5, 0)));
        final GamePanel gamePanel = new GamePanel(queue);
        pane.add(gamePanel);

        Thread game = new Thread(() -> {
            QueuePlayer player = new UIPlayer(queue, gamePanel, infoPanel);
            TetrisGame tg = new TetrisGame(player);
            try {
                long score = tg.play();
                System.out.println();
                System.out.println("Your score: " + score);
                System.out.println();
            } catch (InterruptedException ignore) {}
        });


        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);

        game.start();
    }
}
