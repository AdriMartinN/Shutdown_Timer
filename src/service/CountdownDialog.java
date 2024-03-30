package service;

import view.font.SoDoSansFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CountdownDialog extends JDialog {

    private final SoDoSansFont soDoSansRegularFont = new SoDoSansFont();
    private int countdownSeconds;
    private final Timer timer;

    public CountdownDialog(int countdownSeconds, String command) {
        this.countdownSeconds = countdownSeconds;
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);

        JLabel mainLabel = this.createLabel("Do you want to cancel the shutdown?");
        JLabel timeLabel = this.createLabel("Time remaining: " + this.countdownSeconds + " seconds");
        JButton cancelButton = this.createButton();
        cancelButton.addActionListener(this::cancelShutdown);

        JPanel panel = this.createPanel(mainLabel, timeLabel, cancelButton);

        this.setContentPane(panel);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        this.timer = new Timer(1000, e -> this.updateCountdown(command));
        this.timer.start();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(this.soDoSansRegularFont.soDoSansRegularFont());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createButton() {
        JButton button = new JButton("Cancel");
        button.setFont(this.soDoSansRegularFont.soDoSansRegularFont());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JPanel createPanel(JLabel mainLabel, JLabel timeLabel, JButton cancelButton) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(mainLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(timeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(cancelButton);
        return panel;
    }

    private void updateCountdown(String command) {
        this.countdownSeconds--;
        if (this.countdownSeconds <= 0) {
            this.timer.stop();
            this.dispose();
            System.out.println("Time's up. Shutting down the computer...");
            try {
                Runtime.getRuntime().exec(command);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        } else {
            JLabel timeLabel = (JLabel) this.getContentPane().getComponent(2);
            timeLabel.setText("Time remaining: " + this.countdownSeconds + " seconds");
        }
    }

    private void cancelShutdown(ActionEvent e) {
        this.timer.stop();
        this.dispose();
        this.countdownSeconds = 20;
    }
}
