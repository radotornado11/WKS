package rado.model;

/**
 * Created by Rado on 14.11.2017.
 */

public class Player {
    String name;
    String surname;
    String number;
    String date;
    String height;
    String weight;
    String position;
    String marketValue;
    String endOfContractDate;
    String nationality;
    String previousClub;
    String img;

    public Player(String name, String surname, String num, String date, String height, String weight, String position, String marketValue, String endOfContractDate, String nationality, String previousClub, String img) {
        this.name = name;
        this.surname = surname;
        this.number = num;
        this.date = date;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.marketValue = marketValue;
        this.endOfContractDate = endOfContractDate;
        this.nationality = nationality;
        this.previousClub = previousClub;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getEndOfContractDate() {
        return endOfContractDate;
    }

    public void setEndOfContractDate(String endOfContractDate) {
        this.endOfContractDate = endOfContractDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPreviousClub() {
        return previousClub;
    }

    public void setPreviousClub(String previousClub) {
        this.previousClub = previousClub;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setImg(String img) { this.img = img;}

    public String getImg() {return img;}
}
