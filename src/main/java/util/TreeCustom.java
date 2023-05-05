package util;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Random;

public class TreeCustom extends DefaultTreeCellRenderer {

    Color[] myColors = {
            Color.BLUE,
            Color.GREEN,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.RED
    };
    public TreeCustom() {}
    public void setColors(JTree tree, DefaultMutableTreeNode node) {
        Enumeration<?> children = node.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
            if (child.getChildCount() > 0) {
                DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
                DefaultMutableTreeNode firstChild = (DefaultMutableTreeNode) child.getFirstChild();
                renderer.setForeground(Color.BLUE);
                System.out.println(firstChild.toString());
                tree.setSelectionPath(new TreePath(firstChild.getPath()));
                DefaultMutableTreeNode lastChild = (DefaultMutableTreeNode) child.getLastChild();
                renderer.setForeground(Color.GREEN);
                tree.setSelectionPath(new TreePath(lastChild.getPath()));
                for (int i = 0; i < child.getChildCount(); i++) {
                    setColors(tree, (DefaultMutableTreeNode) child.getChildAt(i));
                }
            }
        }
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Random random = new Random();
        Color color = myColors[random.nextInt(myColors.length-1)];
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
//        Enumeration<?> children = node.children();
//        while (children.hasMoreElements()) {
//            label.setForeground(color);
//        }
        setForeground(color);
        setBackgroundSelectionColor(color);
//        label.setText(node.getUserObject().toString());

        return label;
    }
}
