package algo;

import models.Breed;
import models.Cow;
import util.ReadCSVFile;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Font;
import java.util.ArrayList;

public class FindParent {

    private Cow sourceCow;
    ArrayList<String[]> allCow;
    ArrayList<String[]> allMomBreed;
    ArrayList<String[]> allDadBreed;

    ArrayList<Cow> allCowParent;

    private JFrame frame;

    public FindParent(String cowCode, JFrame frame){
        this.frame = frame;
        allCow = new ReadCSVFile("4cow").getData();
        allMomBreed = new ReadCSVFile("5breed").getData();
        allDadBreed = new ReadCSVFile("7tpfdata").getData();
        allCowParent = new ArrayList<>();
        finding(cowCode);
    }

    public void finding(String cowCode){
        for(String[] cow : allCow){
            if(cow[2].equals(cowCode)){
                sourceCow = new Cow(cowCode, cow[10], cow[11]);
            }
        }

        if (sourceCow != null){
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(sourceCow.getCowCode());
            findingParent(sourceCow);
//            printParent(sourceCow);

            DefaultMutableTreeNode prevNode = null;
            for (Cow cow : allCowParent){
                if (cow.getMom() != null && cow.getDad() != null){
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(cow.getCowCode());
                    DefaultMutableTreeNode momNode = new DefaultMutableTreeNode(cow.getMomCode()+" (MOM)");
                    DefaultMutableTreeNode dadNode = new DefaultMutableTreeNode(cow.getDadCode()+" (DAD)");

                    if (prevNode != null){
                        if (cow.getMomCode().equals(prevNode.getUserObject().toString())){
                            prevNode.setUserObject(cow.getMomCode()+" (MOM)");
                            node.add(prevNode);
                            node.add(dadNode);
                        }
                        else{
                            prevNode.setUserObject(cow.getDadCode()+" (DAD)");
                            node.add(prevNode);
                            node.add(momNode);
                        }
                    }
                    else{
                        node.add(momNode);
                        node.add(dadNode);
                    }
                    prevNode = node;
                }
            }
            if (prevNode != null){
                JTree tree = new JTree(prevNode);
                tree.setFont(new Font("",Font.PLAIN,25));
                frame.add(new JScrollPane(tree));
            }
        }

    }

    private void findingParent(Cow cow){

        if (cow.getMomCode().isEmpty() || cow.getCowCode().equals(cow.getMomCode())){
            findingMomBreed(cow);
            allCowParent.add(cow);
//            System.out.println(cow);
            return;
        }
        if (cow.getDadCode().isEmpty() || cow.getCowCode().equals(cow.getDadCode())){
            findingDadBreed(cow);
            allCowParent.add(cow);
//            System.out.println(cow);
            return;
        }
        for (String[] cowParent : allCow){
            if (cowParent[2].equals(cow.getMomCode()) || cowParent[2].equals(cow.getDadCode())){

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
                    allCowParent.add(cow);
                    break;
                }
            }
        }
    }

    private void findingMomBreed(Cow cowMom){
        ArrayList<Breed> momBreed = new ArrayList<>();
        for (String[] breed : allMomBreed){
            if (breed[0].equals(cowMom.getCowCode())){
                momBreed.add(new Breed(breed[1],Double.parseDouble(breed[2])));
            }
        }
        cowMom.setBreeds(momBreed);
    }

    private void findingDadBreed(Cow cowDad){
        ArrayList<Breed> dadBreed = new ArrayList<>();
        for (String[] breed : allDadBreed){
            if (breed[0].equals(cowDad.getCowCode())){
                dadBreed.add(new Breed(breed[1],Double.parseDouble(breed[2])));
            }
        }
        cowDad.setBreeds(dadBreed);
    }

    private void printParent(Cow cow){
        if (cow.getMom() == null || cow.getDad() == null){
            return;
        }
        printParent(cow.getMom());
        System.out.println(cow);
        printParent(cow.getDad());
    }
}
