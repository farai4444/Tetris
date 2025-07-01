package Tetris;

import javax.swing.*;

public class Tetris {
    private static GameForm gf;
    private static StartUpForm suf;
    private static LeaderBoardForm lbf;
    private static AudioPlayer audio = new AudioPlayer();

    public static void start(){
        gf.startGame();
        gf.setVisible(true);
    }
    public static void showLeaderBoard(){
        lbf.setVisible(true);
    }
    public static void showStartUp(){
        suf.setVisible(true);
    }
    public static void gameOver(int score){
       playGameOver();
       String name = JOptionPane.showInputDialog("GAME OVER!!!\nPlease Enter your name");
       gf.setVisible(false);
       lbf.addPlayer(name,score);

    }
    public static void playClear(){audio.playClearLine();}
    public static void playGameOver(){audio.playGameOver();}
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gf = new GameForm();
                suf = new StartUpForm();
                lbf = new LeaderBoardForm();
                suf.setVisible(true);
            }
        });


    }
}
