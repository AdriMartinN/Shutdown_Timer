package service;

import javax.swing.*;

public class ShutdownTimerService {

    private int remainingTimeSeconds;
    private boolean timerRunning;
    private final ShutdownManager shutdownManager;

    public ShutdownTimerService() {
        this.remainingTimeSeconds = 0;
        this.timerRunning = false;
        this.shutdownManager = new ShutdownManager();
    }

    public void startTimer(JLabel remainingTimeLabel) {
        Timer timer = new Timer(1000, e -> {
            if (this.remainingTimeSeconds > 0 && this.timerRunning) {
                this.remainingTimeSeconds--;
                this.updateRemainingTime(remainingTimeLabel);
            } else if (this.remainingTimeSeconds <= 0 && this.timerRunning) {
                this.shutdownManager.shutdownComputer();
                this.stopTimer(remainingTimeLabel);
            }
        });
        timer.start();
    }

    public void updateRemainingTime(JLabel remainingTimeLabel) {
        int hours = this.remainingTimeSeconds / 3600;
        int minutes = (this.remainingTimeSeconds % 3600) / 60;
        int seconds = this.remainingTimeSeconds % 60;
        remainingTimeLabel.setText(String.format("Remaining time: %02d:%02d:%02d", hours, minutes, seconds));
    }

    public void addTime(JLabel remainingTimeLabel, JTextField hoursField, JTextField minutesField,
                        JTextField secondsField) {
        try {
            long hours = this.parseLong(hoursField.getText());
            long minutes = this.parseLong(minutesField.getText());
            long seconds = this.parseLong(secondsField.getText());

            if (hours < 0 || hours >= Long.MAX_VALUE) {
                JOptionPane.showMessageDialog(null, "Please enter valid hours.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (hours == 0 && minutes == 0 && seconds == 0) {
                JOptionPane.showMessageDialog(null, "Time values cannot all be zero.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            long totalSeconds = hours * 3600 + minutes * 60 + seconds;
            this.remainingTimeSeconds += (int) totalSeconds;
            this.updateRemainingTime(remainingTimeLabel);
            hoursField.setText("");
            minutesField.setText("");
            secondsField.setText("");
            this.timerRunning = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for hours, minutes, and seconds.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void stopTimer(JLabel remainingTimeLabel) {
        this.remainingTimeSeconds = 0;
        this.timerRunning = false;
        this.updateRemainingTime(remainingTimeLabel);
    }

    private long parseLong(String text) throws NumberFormatException {
        return text.isEmpty() ? 0 : Long.parseLong(text);
    }
}