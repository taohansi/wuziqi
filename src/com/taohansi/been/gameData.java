package com.taohansi.been;

import java.io.Serializable;

public class gameData implements Serializable{
    private user gamer;
    private point p;
    public gameData(){}
    public gameData(user gamer, point p) {
        this.gamer = gamer;
        this.p = p;
    }

    public user getGamer() {
        return gamer;
    }

    public void setGamer(user gamer) {
        this.gamer = gamer;
    }

    public point getP() {
        return p;
    }

    public void setP(point p) {
        this.p = p;
    }

}
