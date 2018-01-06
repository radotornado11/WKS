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
            case "Korona Kielce":
                return "Kielce";
            case "Wisła Płock":
                return "Płock";
            case "Zagłębie Lubin":
                return "Lubin";
            case "Lechia Gdańsk":
                return "Gdańsk";
            case "Sandecja N. Sącz":
                return "Nowy Sącz";
            case "Piast Gliwice":
                return "Gliwice";
            case "Cracovia":
                return "Kraków";
            case "Wisła Kraków":
                return "Kraków";
            case "Bruk-Bet Termalica":
                return "Nieciecza";
            case "Pogoń Szczecin":
                return "Szczecin";
            default:
                return "Brak danych";
        }
    }

    public String parseClubNameToCityWithoutPolishSigns(String clubName){
        switch (clubName){
            case "Legia Warszawa":
                return "Warszawa";
            case "Śląsk Wrocław":
                return "Wroclaw";
            case "Lech Poznań":
                return "Poznan";
            case "Górnik Zabrze":
                return "Zabrze";
            case "Jagiellonia Białystok":
                return "Bialystok";
            case "Arka Gdynia":
                return "Gdynia";
            case "Korona Kielce":
                return "Kielce";
            case "Wisła Płock":
                return "Plock";
            case "Zagłębie Lubin":
                return "Lubin";
            case "Lechia Gdańsk":
                return "Gdansk";
            case "Sandecja N. Sącz":
                return "NowySacz";
            case "Piast Gliwice":
                return "Gliwice";
            case "Cracovia":
                return "Krakow";
            case "Wisła Krakow":
                return "Krakow";
            case "Bruk-Bet Termalica":
                return "Nieciecza";
            case "Pogoń Szczecin":
                return "Szczecin";
            default:
                return "Brak danych";
        }
    }
}
