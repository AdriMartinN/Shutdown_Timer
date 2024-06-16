package controller.timer;

import service.timer.ShutdownTimerService;
import view.timer.ShutdownTimerUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShutdownTimerUIController extends ShutdownTimerUI {

    private final ShutdownTimerService shutdownTimerService;

    public ShutdownTimerUIController() {
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
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ShutdownTimerUIController.this.shutdownTimerService.addTime(
                            ShutdownTimerUIController.this.remainingTimeLabel,
                            ShutdownTimerUIController.this.hoursField,
                            ShutdownTimerUIController.this.minutesField,
                            ShutdownTimerUIController.this.secondsField);
                }
            }
        });
    }
}
