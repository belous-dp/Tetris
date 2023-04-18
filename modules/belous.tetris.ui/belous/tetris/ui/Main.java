package belous.tetris.ui;
import belous.tetris.game.api.Move;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setBackground(Color.BLACK);

        BlockingQueue<Move> queue = new ArrayBlockingQueue<>(20);

        pane.add(new GamePanel(queue));
//        pane.add(Box.createRigidArea(new Dimension(10, 0)));
//        pane.add(new GamePanel()); // todo info panel

        // создать отдельный тред для ConcurrentQueuePlayer и TetrisGame.
        // ConcurrentQueuePlayer должен на makeMove брать последний ход из очереди, либо PASS.
        // когда ConcurrentQueuePlayer получает апдейт состояния, нужно с помощью какого-нибудь
        // примитива синхронизации передать его GamePanel.
        // GamePanel имеет таймер и раз в какое-то время просыпается, смотрит, не обновилось ли состояние,
        // и, если да, перерисовывает обновившуюся часть


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}
