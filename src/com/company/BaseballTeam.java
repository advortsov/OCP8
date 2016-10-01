package com.company;

public class BaseballTeam {
    private String city, mascot;
    private int numberOfPlayers;

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseballTeam))
            return false;
        BaseballTeam other = (BaseballTeam) obj;
        return (city.equals(other.city) && mascot.equals(other.mascot));
    }

    public int hashCode() {
        return numberOfPlayers;
    }

//    public String getCity() {
//        return city;
//    }

    public void setCity(String city) {
        this.city = city;
    }

//    public String getMascot() {
//        return mascot;
//    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

//    public int getNumberOfPlayers() {
//        return numberOfPlayers;
//    }
//
//    public void setNumberOfPlayers(int numberOfPlayers) {
//        this.numberOfPlayers = numberOfPlayers;
//    }

    public static void main(String[] args) {
        BaseballTeam baseballTeam1 = new BaseballTeam();
        baseballTeam1.setCity("VLG");
        baseballTeam1.setMascot("MASCOT1");

        BaseballTeam baseballTeam2 = new BaseballTeam();
        baseballTeam2.setCity("VLG");
        baseballTeam2.setMascot("MASCOT1");

        System.out.println(baseballTeam1.equals(baseballTeam2));
    }
}