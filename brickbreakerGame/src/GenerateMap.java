import java.awt.*;
import java.util.Arrays;
public class GenerateMap {
    int[][] bricksArray; // map for all bricks
    int brickWidth;
    int brickHeight;

    // constructor for generate map class
    public GenerateMap(int rows, int cols){
        bricksArray = new int[rows][cols];
        for(int i = 0; i < bricksArray.length; i++){
            for(int j = 0; j < bricksArray[0].length; j++){
                bricksArray[i][j] = 1;//fill the array with 1 to show that the bricks are not broken
            }
        }
        brickWidth = 420/cols; // 8`
        brickHeight = 90/rows; //50
    }
    // generate the bricks
    public void makeBricks(Graphics2D g){
        for(int i = 0; i < bricksArray.length; i++) {
            for (int j = 0; j < bricksArray[0].length; j++) {
                if (bricksArray[i][j] != 0) {
                    g.setColor(Color.blue);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3.0f));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void setValue(int i, int j, int val){
        bricksArray[i][j] = val;
    }
}
