package gui;

import algo.FindParent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;

public class Display extends JPanel {

    private FindParent findParent;
    public Display(){
        findParent = new FindParent("143014ND00487");
        this.setPreferredSize(new Dimension(1366, 768));
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        String[] columnAlLCows = {"หมายเลขฟาร์ม", "หมายเลขโค", "สถานะโค","วันกรอกข้อมูล", "ชื่อโค", "c_oth", "วันเกิด"
                , "หมายเลขแม่พันธุ์", "หมายเลขพ่อพันธุ์", "เพศ", "outfg", "milk", "eurbrd", "eurper"};
        tabbedPane.add("All Cows วัวทั้งหมด ("+decimalFormat(findParent.getAllCow().size())+" รายการ)",
                new CowsTable(findParent.getAllCow(), columnAlLCows).getAllCowsPanel());

        String[] columnAlLBreed = {"หมายเลขโค", "สายพันธุ์", "เปอร์เซ็นต์รวม"};
        tabbedPane.add("Cows Correct ข้อมูลวัวถูกต้อง ("+decimalFormat(findParent.getAllCorrectBreeds().size())+" รายการ)",
                new CowsTable(findParent.getAllCorrectBreeds(), columnAlLBreed).getAllCowsPanel());
        tabbedPane.add("Cows Error ข้อมูลวัวไม่ถูกต้อง ("+decimalFormat(findParent.getAllErrorBreeds().size())+" รายการ)",
                new CowsTable(findParent.getAllErrorBreeds(), columnAlLBreed).getAllCowsPanel());
        this.add(tabbedPane);
    }

    public String decimalFormat (int number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(number);
    }
}
