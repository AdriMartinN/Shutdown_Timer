package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedCornerTextField extends JTextField {

    Frame frame;
    private Shape shape;

    public RoundedCornerTextField(int size, Frame frame) {
        super(size);
        this.frame = frame;
        this.setOpaque(false);

        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!this.isOpaque()) {
            g2.setColor(this.frame.getBackground());
            g2.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10, 10);
        }
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(122, 121, 124));
        g2.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10, 10);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        if (this.shape == null || !this.shape.getBounds().equals(this.getBounds())) {
            this.shape = new RoundRectangle2D.Float(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10, 10); //
        }
        return this.shape.contains(x, y);
    }
}