package service.dialog;

import javax.swing.*;

public class CountdownDialogService {

    private int countdownSeconds;
    private final Timer timer;
    private final Runnable onCountdownFinish;
    private final Runnable onCountdownUpdate;

    public CountdownDialogService(final int countdownSeconds, final Runnable onCountdownFinish,
                                  final Runnable onCountdownUpdate) {
        this.countdownSeconds = countdownSeconds;
        this.onCountdownFinish = onCountdownFinish;
        this.onCountdownUpdate = onCountdownUpdate;

        this.timer = new Timer(1000, e -> this.updateCountdown());
    }

    public void startCountdown() {
        this.timer.start();
    }

    public void cancelCountdown() {
        this.timer.stop();
        this.countdownSeconds = 20;
    }

    public int getCountdownSeconds() {
        return this.countdownSeconds;
    }

    private void updateCountdown() {
        this.countdownSeconds--;
        if (this.countdownSeconds <= 0) {
            this.timer.stop();
            this.onCountdownFinish.run();
        } else {
            this.onCountdownUpdate.run();
        }
    }
}
