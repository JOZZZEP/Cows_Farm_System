package util;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class TreeCustom extends DefaultTreeCellRenderer {

    public TreeCustom() {}

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
//        label.setForeground(Color.decode(node.getUserObject().toString()));
//        label.setText(node.getUserObject().toString());

        return label;
    }
}
