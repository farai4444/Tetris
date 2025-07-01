package TetrisBlocks;

import Tetris.TetrisBlock;

public class IShape extends TetrisBlock {
    public IShape(int[][] shape) {
        super(shape);

    }

    @Override
    public void rotate() {
        super.rotate();
        if (this.getWidth() == 1){
            this.setX(this.getX()+1);
            this.setY(this.getY()-1);
        }
        else{
            this.setY(this.getY()+1);
            this.setX(this.getX()-1);
        }
    }
}
