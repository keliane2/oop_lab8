package it.unibo.deathnote.api;

import java.util.ArrayList;
import java.util.Iterator;


public class MyDeathNote implements DeathNote{
    private int nameCount=0;
    private long initialTime;
    private long finalTime;
    private ArrayList<ArrayList<String>> nameList=new ArrayList<ArrayList<String>>();
    
    public MyDeathNote(){

    }

    public ArrayList<ArrayList<String>> getNameList(){
        return this.nameList;
    }
    public int getNameCount(){
        return this.nameCount;
    }

    @Override
    public String getRule(int ruleNumber){
        if (ruleNumber<1 || ruleNumber>DeathNote.RULES.size()) {
            throw new IllegalArgumentException("Enter a valid number");
        }else{
            return DeathNote.RULES.get(ruleNumber);
        }
    }

    @Override
    public void writeName(String name) {
        if (name.equals(null)||name.equals("")) {
            throw new NullPointerException("Please write a valid name");
        }else{
            ArrayList<String> newInfo=new ArrayList<String>(3);
            newInfo.add(0, name);
            newInfo.add(1, "heart attack");
            newInfo.add(2, "");
            this.nameList.add(newInfo);
            this.nameCount++;
        }
        this.initialTime=System.currentTimeMillis();
    }
    @Override
    public boolean writeDeathCause(String cause) {
        if (this.nameCount==0 || cause.equals("")) {
            throw new IllegalStateException("No noun registered or the cause specified is null");
        }
        this.finalTime=System.currentTimeMillis();
        if ((this.finalTime-this.initialTime)<40) {
            ArrayList<String> deathInfo=new ArrayList<String>(3);
            Iterator<ArrayList<String>> myDeathIterator=this.getNameList().iterator();
            while (myDeathIterator.hasNext()) {
                deathInfo=myDeathIterator.next();
            }
            deathInfo.add(1, cause);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean writeDetails(String details) {
        if (this.nameCount==0 || details.equals("")) {
            throw new IllegalStateException("No noun registered or the detail specified is null");
        }
        this.finalTime=System.currentTimeMillis();
        if ((this.finalTime-this.initialTime)<6040) {
            ArrayList<String> deathInfo=new ArrayList<String>(3);
            Iterator<ArrayList<String>> myDeathIterator=this.getNameList().iterator();
            while (myDeathIterator.hasNext()) {
                deathInfo=myDeathIterator.next();
            }
            deathInfo.add(2, details);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        if (isNameWritten(name)){
            for (int i = 0; i < nameList.size(); i++) {
                if (this.nameList.get(i).get(0).equals(name)) {
                    if(this.nameList.get(i).get(1).equals(null) || this.nameList.get(i).get(1).equals("")){
                        return "heart attack";
                    }else{
                        return nameList.get(i).get(1);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Death cause can't be geted");
    }

    @Override
    public String getDeathDetails(String name) {
        if (isNameWritten(name)){
            for (int i = 0; i < nameList.size(); i++) {
                if (nameList.get(i).get(0).equals(name)) {
                    if(nameList.get(i).get(2).equals(null)){
                        return "";
                    }else{
                        return nameList.get(i).get(2);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Death details can't be geted");
    }

    @Override
    public boolean isNameWritten(String name) {
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).get(0).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
}
