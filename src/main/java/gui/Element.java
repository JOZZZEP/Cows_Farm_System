package gui;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.io.File;
import java.util.Enumeration;

public class Element {

    public static FontUIResource getUIFont(int size){
        Font font = null;
        try {
            font = Font.createFont(Font.PLAIN,new File("src/main/java/font/Kanit-Regular.ttf"));
            return new FontUIResource(font.getFontName(),Font.PLAIN,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert font != null;
        return new FontUIResource(font.getFontName(),Font.PLAIN,size);
    }

    public static Font getFont(int size){
        Font font = null;
        try {
            font = Font.createFont(Font.PLAIN,new File("src/main/java/font/Kanit-Regular.ttf"));
            return font.deriveFont((float)size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }

    public static void setUIFont (FontUIResource font){
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof FontUIResource)
                UIManager.put (key, font);
        }
    }
}
