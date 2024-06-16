import com.formdev.flatlaf.FlatDarkLaf;
import controller.timer.ShutdownTimerUIController;

import javax.swing.*;

public class Main {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (final UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            new ShutdownTimerUIController();
        });
    }
}
