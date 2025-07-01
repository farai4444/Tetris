package Tetris;

import javax.naming.ldap.SortKey;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LeaderBoardForm extends JFrame {
    private JButton mainMenu;
    private JTable scoreTable;
    private JScrollPane scrollPane;
    private DefaultTableModel dtm;
    private JPanel mainMenuPanel;
    private  String leaderBoardFile = "leaderboard";
    private TableRowSorter<TableModel> sorter;
        public LeaderBoardForm(){
            initFrame();
            initTableData();
            initTableRowSorter();
        }
        public void initFrame(){
            scoreTable = new JTable();
            scrollPane = new JScrollPane(scoreTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            mainMenuPanel = new JPanel();
            mainMenu = new JButton("Main menu");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
            mainMenuPanel.setLayout(new FlowLayout());
            mainMenu.setBackground(Color.white);
            mainMenuPanel.setBounds(50, 100, 100, 50);
            this.setBounds(100, 100, 500, 500);
            mainMenuPanel.setLayout(new FlowLayout());
            mainMenuPanel.add(mainMenu);
            this.setResizable(true);
            this.add(scrollPane,BorderLayout.CENTER);
            this.add(mainMenuPanel,BorderLayout.WEST);
            mainMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Tetris.showStartUp();
                    setVisible(false);

                }
            });

        }
        private void initTableData(){
            Vector columnHeaders = new Vector();
            columnHeaders.add("Player");
            columnHeaders.add("Score");
            dtm = (DefaultTableModel) scoreTable.getModel();
            dtm.setColumnCount(2);
            dtm.setColumnIdentifiers(columnHeaders);
            try {
                FileInputStream fis = new FileInputStream(leaderBoardFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                dtm.setDataVector((Vector<Vector>)ois.readObject(), columnHeaders);
                fis.close();
                ois.close();
            }
            catch (Exception e) {}
        }
        private void initTableRowSorter(){
           sorter = new TableRowSorter<>(dtm);
           scoreTable.setRowSorter(sorter);

        }
        private void saveLeaderBoard(){
            try (FileOutputStream fs = new FileOutputStream(leaderBoardFile)) {
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(dtm.getDataVector());
                fs.close();
                os.close();
            } catch (Exception e) {
            }

        }
        public void addPlayer(String playerName,int score){
            dtm.addRow(new Object[]{playerName,score});

            saveLeaderBoard();
            setVisible(true);

        }

}
