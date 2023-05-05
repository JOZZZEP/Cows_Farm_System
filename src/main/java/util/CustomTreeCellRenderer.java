package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    private final DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    Color[] myColors = {
            Color.BLUE,
            Color.GREEN,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.RED
    };


    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Random random = new Random();
        Color color = myColors[random.nextInt(myColors.length-1)];
//        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            if (node.getParent() != null) {
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();

                int childCount = parent.getChildCount();
                int index = parent.getIndex(node);
//
//                // Set the color of the first child to red
//                for (int i=0;i < childCount;i++){
//                }
                if (index == 0) {
                    component.setForeground(Color.RED);
//                    component.setForeground(color);
                }
//                // Set the color of the last child to green
                if (index == childCount - 1) {
                    component.setForeground(Color.BLUE);
//                    component.setForeground(color);
                }
//                // Reset the color of other nodes to black
//                else {
//                    component.setForeground(Color.BLACK);
//                }
//            }
        }

        return component;
    }
}
