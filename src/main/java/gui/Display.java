package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Display extends JPanel {

    public Display(){
        this.setPreferredSize(new Dimension(1366, 768));
        this.setLayout(new BorderLayout());
    }
}
