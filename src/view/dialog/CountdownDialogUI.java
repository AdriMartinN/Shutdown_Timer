package view.dialog;

import view.font.SoDoSansFont;

import javax.swing.*;
import java.awt.*;

public class CountdownDialogUI extends JDialog {

    public final SoDoSansFont soDoSansRegularFont = new SoDoSansFont();
    public JLabel mainLabel;
    public JLabel timeLabel;
    public JButton cancelButton;
    public JPanel panel;

    public CountdownDialogUI() {
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);

        this.mainLabel = this.createLabel("Do you want to cancel the shutdown?");
        this.timeLabel = this.createLabel("Time remaining: 0 seconds");
        this.cancelButton = this.createButton();

        this.panel = this.createPanel(this.mainLabel, this.timeLabel, this.cancelButton);

        this.setContentPane(this.panel);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    protected JLabel createLabel(final String text) {
        final JLabel label = new JLabel(text);
        label.setFont(this.soDoSansRegularFont.soDoSansRegularFont());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    protected JButton createButton() {
        final JButton button = new JButton("Cancel");
        button.setFont(this.soDoSansRegularFont.soDoSansRegularFont());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    protected JPanel createPanel(final JLabel mainLabel, final JLabel timeLabel, final JButton cancelButton) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(mainLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(timeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(cancelButton);
        return panel;
    }
}
