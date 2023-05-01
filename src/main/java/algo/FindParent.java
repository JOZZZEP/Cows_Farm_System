package algo;

import models.Cow;
import util.ReadCSVFile;

import java.util.ArrayList;

public class FindParent {

    private Cow sourceCow;
    ArrayList<String[]> allCow;
    int i = 0;

    public FindParent(String cowCode){
        allCow = new ReadCSVFile("4cow").getData();
        finding(cowCode);
    }

    public void finding(String cowCode){
        for(String[] cow : allCow){
            if(cow[2].equals(cowCode)){
                sourceCow = new Cow(cowCode, cow[10], cow[11]);
            }
        }

        if (sourceCow != null){
            findingParent(sourceCow);
            System.out.println(i);
            printParent(sourceCow);
        }
    }

    private void findingParent(Cow cow){
        if (cow.getMomCode().isEmpty() || cow.getDadCode().isEmpty()
            || cow.getCowCode().equals(cow.getMomCode())
            || cow.getCowCode().equals(cow.getDadCode())){
            return;
        }
        for (String[] cowParent : allCow){
            if (cowParent[2].equals(cow.getMomCode()) || cowParent[2].equals(cow.getDadCode())){
                if (cowParent[10].isEmpty() || cowParent[11].isEmpty()){
                    return;
                }

                if (cowParent[2].equals(cow.getMomCode())){
                    Cow mom = new Cow(cowParent[2], cowParent[10], cowParent[11]);
                    cow.setMom(mom);
                    findingParent(mom);
                }
                if (cowParent[2].equals(cow.getDadCode())){
                    Cow dad = new Cow(cowParent[2], cowParent[10], cowParent[11]);
                    cow.setDad(dad);
                    findingParent(dad);
                }

                if (cow.getMom() != null && cow.getDad() != null){
                    break;
                }
            }
            i++;
        }
    }

    private void printParent(Cow cow){
        System.out.println(cow);
        if (cow.getMom() == null || cow.getDad() == null){
            return;
        }
        printParent(cow.getMom());
        printParent(cow.getDad());
    }
}
