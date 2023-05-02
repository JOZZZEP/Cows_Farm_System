package algo;

import models.Breed;
import models.Cow;
import util.ReadCSVFile;

import java.util.ArrayList;

public class FindParent {

    private Cow sourceCow;
    ArrayList<String[]> allCow;
    ArrayList<String[]> allMomBreed;
    ArrayList<String[]> allDadBreed;

    ArrayList<Cow> allCowParent;

    public FindParent(String cowCode){
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
            findingParent(sourceCow);
            printParent(sourceCow);
        }

//        for (Cow cow : allCowParent){
//            System.out.println(cow);
//        }
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
        System.out.println(cow);
        if (cow.getMom() == null || cow.getDad() == null){
            return;
        }
        printParent(cow.getMom());
        printParent(cow.getDad());
    }
}
