package controller;

import service.ShutdownTimerService;
import view.ShutdownTimerInterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShutdownTimerInterfaceController extends ShutdownTimerInterface {

    private final ShutdownTimerService shutdownTimerService;

    public ShutdownTimerInterfaceController() {
        super();
        this.shutdownTimerService = new ShutdownTimerService();
        this.shutdownTimerService.updateRemainingTime(this.remainingTimeLabel);
        this.shutdownTimerService.startTimer(this.remainingTimeLabel);
        this.setupButtonActions();
        this.setupEnterKeyListener();
    }

    private void setupButtonActions() {
        this.addTimeButton.addActionListener(e -> this.shutdownTimerService.addTime(this.remainingTimeLabel,
                this.hoursField,
                this.minutesField, this.secondsField));

        this.stopButton.addActionListener(e -> this.shutdownTimerService.stopTimer(this.remainingTimeLabel));
    }

    private void setupEnterKeyListener() {
        this.minutesField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ShutdownTimerInterfaceController.this.shutdownTimerService.addTime(
                            ShutdownTimerInterfaceController.this.remainingTimeLabel,
                            ShutdownTimerInterfaceController.this.hoursField,
                            ShutdownTimerInterfaceController.this.minutesField,
                            ShutdownTimerInterfaceController.this.secondsField);
                }
            }
        });
    }
}
