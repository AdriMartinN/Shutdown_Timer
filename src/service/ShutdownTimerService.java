package service;

import javax.swing.*;
import java.io.IOException;

public class ShutdownTimerService {

    private int remainingTimeSeconds;
    private boolean timerRunning;

    // Constructor
    public ShutdownTimerService() {
        remainingTimeSeconds = 0;
        timerRunning = false;
    }

    // Start timer
    public void startTimer(JLabel remainingTimeLabel) {
        Timer timer = new Timer(1000, e -> {
            if (remainingTimeSeconds > 0 && timerRunning) {
                remainingTimeSeconds--;
                updateRemainingTime(remainingTimeLabel);
            } else if (remainingTimeSeconds <= 0 && timerRunning) {
                shutdownComputer();
                stopTimer(remainingTimeLabel);
            }
        });
        timer.start();
    }

    // Update remaining time label
    public void updateRemainingTime(JLabel remainingTimeLabel) {
        int hours = remainingTimeSeconds / 3600;
        int minutes = (remainingTimeSeconds % 3600) / 60;
        int seconds = remainingTimeSeconds % 60;
        remainingTimeLabel.setText(String.format("Remaining time: %02d:%02d:%02d", hours, minutes, seconds));
    }

    // Add time to remaining time
    public void addTime(JLabel remainingTimeLabel, JTextField hoursField, JTextField minutesField,
                        JTextField secondsField) {
        try {
            int hours = hoursField.getText().isBlank() ? 0 : Integer.parseInt(hoursField.getText());
            int minutes = minutesField.getText().isBlank() ? 0 : Integer.parseInt(minutesField.getText());
            int seconds = secondsField.getText().isBlank() ? 0 : Integer.parseInt(secondsField.getText());

            if (hours == 0 && minutes == 0 && seconds == 0) {
                throw new IllegalArgumentException("Time values cannot all be zero.");
            }

            int totalSeconds = hours * 3600 + minutes * 60 + seconds;
            remainingTimeSeconds += totalSeconds;
            updateRemainingTime(remainingTimeLabel);
            hoursField.setText("");
            minutesField.setText("");
            secondsField.setText("");
            timerRunning = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for hours, minutes, and seconds.", "Error"
                    , JOptionPane.ERROR_MESSAGE);
        }
    }


    // Stop timer
    public void stopTimer(JLabel remainingTimeLabel) {
        remainingTimeSeconds = 0;
        timerRunning = false;
        updateRemainingTime(remainingTimeLabel);
    }

    // Shutdown the computer
    private void shutdownComputer() {
        try {
            // Execute the system shutdown command
            Process process = Runtime.getRuntime().exec("shutdown -s -t 1");
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while shutting down the computer.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
