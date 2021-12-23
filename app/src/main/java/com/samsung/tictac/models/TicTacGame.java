package com.samsung.tictac.models;

import java.io.Serializable;

public class TicTacGame implements Serializable {

    public String player1;
    public String player2;

    public int amountOfPlayer;

    public boolean player1IsX;
    public int difficulty;

    public TicTacGame(String player1, String player2, int amountOfPlayer, boolean player1IsX, int difficulty) {
        this.player1 = player1;
        this.player2 = player2;
        this.amountOfPlayer = amountOfPlayer;
        this.player1IsX = player1IsX;
        this.difficulty = difficulty;
    }

    public TicTacGame() {
    }

    @Override
    public String toString() {
        return "TicTacGame{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", amountOfPlayer=" + amountOfPlayer +
                ", player1IsX=" + player1IsX +
                ", difficulty=" + difficulty +
                '}';
    }
}
