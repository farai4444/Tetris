package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameForm extends JFrame {
    private JButton mainMenu;
    private JPanel mainMenuPanel;
    private GameThread gt;
    private JPanel board;
    private  JLabel scoreDisp;
    private JLabel levelDisp;

    private GameArea ga;

    public GameForm() {
        this.ga = new GameArea(10);
        initFrame();
        initControls();
    }

    private void initFrame(){
        mainMenuPanel = new JPanel();
        mainMenu = new JButton("Main menu");
        board = new JPanel();
        scoreDisp = new JLabel("Score: 0");
        levelDisp = new JLabel("Level: 1");
        Font font = new Font(Font.SERIF,Font.BOLD,18);
        scoreDisp.setFont(font);
        levelDisp.setFont(font);
        mainMenu.setBackground(Color.white);
        mainMenuPanel.setBounds(30, 100, 100, 50);
        board.setBounds(360, 95, 100, 50);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuPanel.setLayout(new FlowLayout());
        board.setLayout(new GridLayout(2,0));
        mainMenuPanel.add(mainMenu);
        board.add(scoreDisp);
        board.add(levelDisp);
        this.add(board,BorderLayout.EAST);
        this.add(mainMenuPanel,BorderLayout.WEST);
        this.add(ga);
        this.setLayout(new BorderLayout());
        this.setBounds(100, 100, 500, 500);
        this.setResizable(true);
        mainMenu.setFocusable(false);
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.showStartUp();
                setVisible(false);
                gt.interrupt();
            }
        });



    }
    public void startGame(){
        ga.initBackgroundArray();
        gt = new GameThread(ga,this);
        gt.start();
    }
    private void initControls(){
       InputMap in = this.getRootPane().getInputMap();
       ActionMap ac = this.getRootPane().getActionMap();
       in.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        in.put(KeyStroke.getKeyStroke("LEFT"), "left");
        in.put(KeyStroke.getKeyStroke("UP"), "up");
        in.put(KeyStroke.getKeyStroke("DOWN"), "down");
        ac.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });
        ac.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();

            }
        } );
        ac.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ga.rotateBlock();
            }
        } );
        ac.put("down",  new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();

            }
        });

    }
    public void updateScore(int score){scoreDisp.setText("Score: "+score);}
    public void updateLevel(int level){levelDisp.setText("Level: "+level);}


}