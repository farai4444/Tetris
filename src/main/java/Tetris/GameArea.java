package Tetris;

import TetrisBlocks.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {
    private int gridRows;
    private  int gridColumns;
    private  int gridCellSize;
    private Color[][] background;
    private TetrisBlock block;
    private TetrisBlock [] blocks;

    public GameArea(int columns) {
        this.setBounds(150, 100, 200, 300);
        gridColumns = columns;
        borderLine();
        gridCellSize = this.getBounds().width/gridColumns;
        gridRows = this.getBounds().height/gridCellSize;
        blocks = new TetrisBlock[]{ new JShape(new int[][] {{0,1},{0,1},{1,1}}), new ZShape( new  int[][] {{1,1,0},{0,1,1}}), new LShape(new int[][] {{1,0},{1,0},{1,1}}), new OShape(  new int [][]  {{1,1},{1,1}}),new SShape( new int [][] {{0,1,1},{1,1,0}}), new TShape(new int [][] {{1,1,1},{0,1,0}}), new IShape(new int[][]{{1,1,1,1}})};



    }
    public void initBackgroundArray(){
        background = new Color[gridRows][gridColumns];
    }
    public void spawnBlock(){
        Random r = new Random();
       block = blocks[r.nextInt(blocks.length)];
       block.spawn(gridColumns);

    }

    public boolean isBlockOutOfBounds(){
        if(block.getY()<0){
            block = null;
            return true;
        }
        return false;
    }
    public boolean moveBlockDown(){
        if (!checkBottom()){
            return false;
        }


        block.moveDown();
        repaint();
        return true;
    }
    private boolean checkBottom(){
        if (block.getBottomEdge()==gridRows){
            return false;
        }
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int col = 0;col<w;col++){
            for (int row = h-1;row >=0;row--){
               if (shape[row][col]!=0){
                   int x = col + block.getX();
                   int y = row + block.getY() + 1;
                   if (y<0) break;
                   if (background[y][x] != null) return false;
                   break;
               }
            }
        }
        return true;
    }
    private boolean checkLeft(){
        if (block.getLeftEdge() == 0)return false;

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row = 0;row<h;row++){
            for (int col = 0;col < w;col++){
                if (shape[row][col]!=0){
                    int x = col + block.getX()-1;
                    int y = row + block.getY();
                    if (y<0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;

    }
    private boolean checkRight(){
        if (block.getRightEdge() == gridColumns ) return false;
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row = 0;row<h;row++){
            for (int col = w-1;col >=0 ;col--){
                if (shape[row][col]!=0){
                    int x = col + block.getX()+1;
                    int y = row + block.getY();
                    if (y<0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;

    }

    public int clearLines(){
        boolean lineFilled;
        int lineCleared = 0;
        for (int r = gridRows - 1; r >= 0; r--){
            lineFilled = true;
            for (int c = 0; c<gridColumns; c++){
                if (background[r][c] == null){
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled){
                lineCleared++;
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                r++;


                   repaint();


            }
        }
       if (lineCleared>0){Tetris.playClear();}
        return lineCleared;
    }
    private void clearLine(int r){
        for (int i = 0; i<gridColumns; i++) {
            background[r][i] = null;
        }
    }
    private void shiftDown(int r){
        for (int row = r;row>0;row--){
            for (int col=0;col<gridColumns;col++){
                background[row][col] = background[row-1][col];
            }
        }

    }
    public void movedBlockToBackground(){
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();

        for (int r = 0; r<h; r++){
            for (int c =0;c<w;c++){
                if (shape[r][c] == 1)

                    background[r + yPos][c + xPos] = color;
            }
        }
    }

    public void moveBlockRight(){
        if (block == null){return;}
         if (!checkRight()) return;
        block.moveRight();
        repaint();


    }
    public void moveBlockLeft(){
        if (block == null){return;}
        if (!checkLeft()) return;
        block.moveLeft();
        repaint();

    }
    public void rotateBlock(){
        if (block == null){return;}
        block.rotate();
        if (block.getLeftEdge()<0) block.setX(0);
        if (block.getRightEdge() >= gridColumns) block.setX(gridColumns - block.getWidth());
        if (block.getBottomEdge() >= gridRows) block.setY(gridRows- block.getHeight());
        repaint();

    }
    public void dropBlock(){
        if (block == null){return;}
        while (checkBottom()){
            block.moveDown();
        }
        repaint();

    }
    private void borderLine(){
        Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(borderLine);

    }
    private void drawBlock(Graphics g){
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int [][] shape = block.getShape();
        for (int row= 0; row<h;row++){
            for (int col= 0; col<w;col++){
                if (shape[row][col] == 1) {
                    int x = (block.getX()+col)*gridCellSize ;
                    int y = (block.getY()+row)*gridCellSize;
                   drawGridSquare(g, c, x,y);
                }

            }

        }
    }
    private void drawBackground(Graphics g){
        Color color;
        for (int r= 0; r<gridRows;r++){
            for (int c= 0; c<gridColumns;c++){
                color = background[r][c];
                if (color != null) {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    drawGridSquare(g, color,x,y);

                }
            }


             }

    }
    private void drawGridSquare(Graphics g, Color color,int x, int y){
        g.setColor(color);

        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlock(g);
        drawBackground(g);
    }
}
