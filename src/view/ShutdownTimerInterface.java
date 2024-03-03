package view;

import javax.swing.*;
import java.awt.*;

public class ShutdownTimerInterface extends JFrame {
    public JLabel remainingTimeLabel;
    public JTextField hoursField;
    public JTextField minutesField;
    public JTextField secondsField;
    public JButton addTimeButton = new JButton("Add Time");
    public JButton stopButton = new JButton("Stop Timer");


    public ShutdownTimerInterface() {
        super("Shutdown Timer");
        setVisible(true);

        remainingTimeLabel = new JLabel("Welcome to the shutdown timer");
        hoursField = new JTextField(5);
        minutesField = new JTextField(5);
        secondsField = new JTextField(5);

        // Layout
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Cambiado a la izquierda
        labelPanel.add(new JLabel("Hours:"));
        labelPanel.add(new JLabel("Minutes:"));
        labelPanel.add(new JLabel("Seconds:"));

        JPanel textFieldPanel = new JPanel(new FlowLayout());
        textFieldPanel.add(hoursField);
        textFieldPanel.add(new JLabel(":"));
        textFieldPanel.add(minutesField);
        textFieldPanel.add(new JLabel(":"));
        textFieldPanel.add(secondsField);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(remainingTimeLabel, FlowLayout.LEFT);
        add(new JLabel("Add time:"));
        add(labelPanel);
        add(textFieldPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addTimeButton);
        buttonPanel.add(stopButton);
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
