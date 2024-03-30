package view;

import view.font.SoDoSansFont;

import javax.swing.*;
import java.awt.*;

public class ShutdownTimerInterface extends JFrame {
    public JLabel remainingTimeLabel;
    public JTextField hoursField;
    public JTextField minutesField;
    public JTextField secondsField;
    public JButton addTimeButton = new JButton("Add Time");
    public JButton stopButton = new JButton("Stop Timer");
    SoDoSansFont soDoSansRegularFont = new SoDoSansFont();

    public ShutdownTimerInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.remainingTimeLabel = new JLabel("Welcome to the Shutdown Timer");
        this.remainingTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.remainingTimeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        this.hoursField = new RoundedCornerTextField(5, this);
        this.minutesField = new RoundedCornerTextField(5, this);
        this.secondsField = new RoundedCornerTextField(5, this);

        JPanel labelPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelPanel.add(this.createStyledLabel("Hours:"));
        labelPanel.add(this.createStyledLabel("Minutes:"));
        labelPanel.add(this.createStyledLabel("Seconds:"));

        JPanel textFieldPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        textFieldPanel.add(this.hoursField);
        textFieldPanel.add(this.minutesField);
        textFieldPanel.add(this.secondsField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(this.addTimeButton);
        buttonPanel.add(this.stopButton);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(labelPanel, gbc);
        gbc.gridy = 1;
        contentPanel.add(textFieldPanel, gbc);

        this.setLayout(new BorderLayout());
        this.add(this.remainingTimeLabel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.styleComponents();

        this.setMinimumSize(new Dimension(400, 200));
        this.pack();
        this.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(this.soDoSansRegularFont.soDoSansRegularFont());
        return label;
    }

    private void styleComponents() {
        Font font = this.soDoSansRegularFont.soDoSansRegularFont();

        this.remainingTimeLabel.setFont(font);
        this.addTimeButton.setFont(font);
        this.stopButton.setFont(font);
        this.hoursField.setFont(font);
        this.minutesField.setFont(font);
        this.secondsField.setFont(font);
    }

    private void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);
    }
}
