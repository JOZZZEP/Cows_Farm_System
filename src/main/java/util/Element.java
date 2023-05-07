package util;

import java.awt.Font;
import java.io.InputStream;

public class Element {

    public static Font getFont(int size){
        Font font = null;
        try {
            InputStream inputStream = Element.class.getResourceAsStream("/font/Kanit-Regular.ttf");
            assert inputStream != null;
            font = Font.createFont(Font.PLAIN, inputStream);
            return font.deriveFont((float)size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }

}
