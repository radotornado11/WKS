package rado.model;

/**
 * Created by Rado on 21.12.2017.
 */

public class ClubNameParser {

    public String parseClubNameToCity(String clubName){
        switch (clubName){
            case "Legia Warszawa":
                return "Warszawa";
            case "Śląsk Wrocław":
                return "Wrocław";
            case "Lech Poznań":
                return "Poznań";
            case "Górnik Zabrze":
                return "Zabrze";
            case "Jagiellonia Białystok":
                return "Białystok";
            case "Arka Gdynia":
                return "Gdynia";
            default:
                return "Bradi";
        }
    }
}
