package controller;

import service.ShutdownTimerService;
import view.ShutdownTimerInterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShutdownTimerInterfaceController extends ShutdownTimerInterface {

    ShutdownTimerService shutdownTimerService = new ShutdownTimerService();

    public ShutdownTimerInterfaceController() {
        super();

        shutdownTimerService.updateRemainingTime(remainingTimeLabel);

        shutdownTimerService.startTimer(remainingTimeLabel);

        addTimeButton.addActionListener(e -> shutdownTimerService.addTime(remainingTimeLabel, hoursField,
                minutesField, secondsField));

        stopButton.addActionListener(e -> shutdownTimerService.stopTimer(remainingTimeLabel));

        minutesField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    shutdownTimerService.addTime(remainingTimeLabel, hoursField, minutesField, secondsField);
                }
            }
        });
    }
}
