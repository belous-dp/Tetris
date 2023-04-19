package belous.tetris.ui;
import belous.tetris.game.api.Move;
import belous.tetris.game.api.QueuePlayer;
import belous.tetris.game.api.TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final ContentPane pane = new ContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setBackground(Color.BLACK);

        final BlockingQueue<Move> queue = new ArrayBlockingQueue<>(20);

        final GamePanel gamePanel = new GamePanel(queue);
        pane.add(gamePanel);
//        pane.add(Box.createRigidArea(new Dimension(10, 0)));
//        pane.add(new GamePanel()); // todo info panel

        Thread game = new Thread(() -> {
            QueuePlayer player = new UIPlayer(queue, gamePanel);
            TetrisGame tg = new TetrisGame(player);
            try {
                tg.play();
            } catch (InterruptedException ignore) {}
        });


        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);

        game.start();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}
