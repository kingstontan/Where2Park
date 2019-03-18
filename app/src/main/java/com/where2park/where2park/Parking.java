package com.where2park.where2park;

public class Parking implements Comparable<Parking>{

    private String name;
    private int lotsavailable;
    private int etadrive;
    private int etawalk;
    private int score;

    public Parking(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLotsavailable() {
        return lotsavailable;
    }

    public void setLotsavailable(int lotsavailable) {
        this.lotsavailable = lotsavailable;
    }

    public int getEtadrive() {
        return etadrive;
    }

    public void setEtadrive(int etadrive) {
        this.etadrive = etadrive;
    }

    public int getEtawalk() {
        return etawalk;
    }

    public void setEtawalk(int etawalk) {
        this.etawalk = etawalk;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //implementation to be updated
    public void updateRealTimeInfo(){
        setEtadrive(3);
        setEtawalk(3);
        setLotsavailable(500);
        setScore(lotsavailable - etadrive - etawalk);
    }

    @Override
    public int compareTo(Parking o) {
        return o.getScore() - this.getScore() ;
    }
}
