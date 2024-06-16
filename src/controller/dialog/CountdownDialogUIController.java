package controller.dialog;

import service.dialog.CountdownDialogService;
import view.dialog.CountdownDialogUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CountdownDialogUIController extends CountdownDialogUI {

    private final CountdownDialogService service;
    private final String command;

    public CountdownDialogUIController(final int countdownSeconds, final String command) {
        super();
        this.command = command;

        this.service = new CountdownDialogService(countdownSeconds,
                this::shutdown,
                this::updateCountdownLabel);

        this.timeLabel.setText("Time remaining: " + countdownSeconds + " seconds");

        this.cancelButton.addActionListener(this::cancelShutdown);

        this.service.startCountdown();
    }

    private void updateCountdownLabel() {
        SwingUtilities.invokeLater(() -> {
            final int remainingSeconds = this.service.getCountdownSeconds();
            this.timeLabel.setText("Time remaining: " + remainingSeconds + " seconds");
        });
    }

    private void cancelShutdown(final ActionEvent e) {
        this.service.cancelCountdown();
        this.dispose();
    }

    private void shutdown() {
        SwingUtilities.invokeLater(() -> {
            this.dispose();
            try {
                Runtime.getRuntime().exec(this.command);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        });
    }
}
