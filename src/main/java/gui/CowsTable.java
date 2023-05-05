package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CowsTable {

    private JPanel allCowsPanel;
    ArrayList<String[]> allData;
    public CowsTable(ArrayList<String[]> allData, String[] columnNames){
        this.allData = allData;
        allCowsPanel = new JPanel();
        allCowsPanel.setLayout(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel(allData.toArray(new Object[0][0]), columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(20);
        table.setRowSelectionAllowed(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    String name = (String) table.getValueAt(row, 0);
                    JOptionPane.showMessageDialog(null, "Name: " + name);
                }
            }
        });

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(true);
        header.setResizingAllowed(true);
        header.setBackground(Color.lightGray);
        header.setForeground(Color.black);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1366, 768));
        scrollPane.getVerticalScrollBar().setUnitIncrement(100);

        allCowsPanel.add(scrollPane);
    }

    public JPanel getAllCowsPanel() {
        return allCowsPanel;
    }

}
