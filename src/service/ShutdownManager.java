package service;

import controller.dialog.CountdownDialogUIController;

import javax.swing.*;

public class ShutdownManager {

    public void shutdownComputer() {
        final String shutdownCommand = this.getShutdownCommand();
        if (shutdownCommand != null) {
            new CountdownDialogUIController(20, shutdownCommand).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Shutdown command not supported for this operating system.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getShutdownCommand() {
        final String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "shutdown -s -t 1";
        } else if (osName.contains("mac")) {
            return "shutdown -h now";
        } else if (osName.contains("nix") || osName.contains("nux")) {
            return "shutdown now";
        } else {
            return null;
        }
    }
}
