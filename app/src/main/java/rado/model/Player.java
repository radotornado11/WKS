package rado.model;

/**
 * Created by Rado on 13.11.2017.
 */

public class Player {
    private String position;
    private String clubName;
    private String pointsCount;
    private String matchesCount;
    private String winsCount;
    private String drawsCount;
    private String losesCount;
    private String goalsBalance;

    public Player(String p, String cl, String point, String match, String win, String draw, String los, String goal)
    {
        this.position = p;
        this.clubName = cl;
        this.pointsCount = point;
        this.matchesCount = match;
        this.winsCount = win;
        this.drawsCount = draw;
        this.losesCount = los;
        this.goalsBalance = goal;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(String pointsCount) {
        this.pointsCount = pointsCount;
    }

    public String getMatchesCount() {
        return matchesCount;
    }

    public void setMatchesCount(String matchesCount) {
        this.matchesCount = matchesCount;
    }

    public String getWinsCount() {
        return winsCount;
    }

    public void setWinsCount(String winsCount) {
        this.winsCount = winsCount;
    }

    public String getDrawsCount() {
        return drawsCount;
    }

    public void setDrawsCount(String drawsCount) {
        this.drawsCount = drawsCount;
    }

    public String getLosesCount() {
        return losesCount;
    }

    public void setLosesCount(String losesCount) {
        this.losesCount = losesCount;
    }

    public String getGoalsBalance() {
        return goalsBalance;
    }

    public void setGoalsBalance(String goalsBalance) {
        this.goalsBalance = goalsBalance;
    }
}
