package gui;

import util.RunDB;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Display extends JPanel {

    private static CardLayout cardLayout;

    public Display(){
        ArrayList<String[]> allCow = RunDB.getAllCows();
        ArrayList<String[]> allCorrectBreeds = RunDB.getAllCorrectBreed();
        ArrayList<String[]> allErrorBreeds = RunDB.getAllErrorBreed();
        cardLayout = new CardLayout();
        this.setPreferredSize(new Dimension(1366, 768));
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(cardLayout);

        JTabbedPane tabbedPane = new JTabbedPane();
        String[] columnAlLCows = {"เลขเกษตรกร", "หมายเลขโค", "สถานะโค","วันที่", "ชื่อโค", "c_oth", "วันเกิด"
                , "หมายเลขแม่", "หมายเลขพ่อ", "เพศ", "outfg", "milk", "eurbrd", "eurper"};
        tabbedPane.add("All Cows วัวทั้งหมด ("+decimalFormat(allCow.size())+" รายการ)",
                new CowsTable(allCow, columnAlLCows, this).getAllCowsPanel());

        String[] columnAlLBreed = {"หมายเลขโค", "สายพันธุ์", "เปอร์เซ็นต์รวม"};
        tabbedPane.add("Cows Correct ข้อมูลวัวถูกต้อง ("+decimalFormat(allCorrectBreeds.size())+" รายการ)",
                new CowsTable(allCorrectBreeds, columnAlLBreed, this).getAllCowsPanel());
        tabbedPane.add("Cows Error ข้อมูลวัวไม่ถูกต้อง ("+decimalFormat(allErrorBreeds.size())+" รายการ)",
                new CowsTable(allErrorBreeds, columnAlLBreed, this).getAllCowsPanel());
        this.add(tabbedPane, "COWS_TABLE");
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }

    public String decimalFormat (int number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(number);
    }
}
