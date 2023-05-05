import gui.Display;
import gui.Element;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;

public class Main {

    public static void main(String[] args){

        try {
            Element.setUIFont(Element.getUIFont(15));
        } catch (Exception ignored) { }

        JFrame frame = new JFrame();
        frame.setTitle("Cow Simulator");

        frame.add(new Display());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1366, 768));
        frame.setMinimumSize(frame.getPreferredSize());
        frame.pack();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
