package view.font;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SoDoSansFont {
    public Font soDoSansRegularFont() {
        Font customFont;

        try (InputStream is = this.getClass().getResourceAsStream("/view/font/SoDoSans-Regular.ttf")) {
            assert is != null;
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }

        return customFont;
    }
}
