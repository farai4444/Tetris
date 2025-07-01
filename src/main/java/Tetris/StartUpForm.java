package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpForm extends JFrame {
    private JPanel panel;
    private JPanel areaPanel;
    private JLabel textArea;
    private Button startGame;
    private Button leaderBoard;
    private Button quit;
    public StartUpForm(){
        initFrame();
    }
    public void initFrame(){
        Font font = new Font(Font.SERIF,Font.BOLD,28);
        areaPanel = new JPanel();
        startGame = new Button("Start Game");
        leaderBoard = new Button("LeaderBoard");
        textArea = new JLabel("TETRIS GAME");
        quit = new Button("Quit");
        panel = new JPanel();
        startGame.setBackground(Color.WHITE);
        leaderBoard.setBackground(Color.WHITE);
        quit.setBackground(Color.WHITE);
        textArea.setFont(font);
        textArea.setForeground(Color.GREEN);
        areaPanel.setBackground(Color.black);
        areaPanel.setLayout(new FlowLayout());
        areaPanel.setBounds(120, 100, 200, 50);
        panel.setLayout(new GridLayout(3, 0, 0,8 ));
        panel.setBounds(70, 250, 300, 100);
        areaPanel.add(textArea);
        panel.add(startGame);
        panel.add(leaderBoard);
        panel.add(quit);
        this.add(areaPanel,BorderLayout.SOUTH);
        this.add(panel,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBounds(100, 100, 500, 500);
        this.setResizable(true);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.start();

            }
        });
        leaderBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.showLeaderBoard();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }




}
