import com.formdev.flatlaf.FlatDarkLaf;
import controller.ShutdownTimerInterfaceController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            new ShutdownTimerInterfaceController();
        });
    }
}
