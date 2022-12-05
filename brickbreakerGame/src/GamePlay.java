import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false; // game is stop
    private int score = 0; // initial score;
    private Timer timer;
    private int totalBricks = 21;
    private int delay = 8; // time after which the game starts again
    private int platformPos = 310; // platform position in x-axis
    private int ballposX = 120; // ball position in x-axis
    private int ballposY = 350; // ball position in y-axis
    private int ballXdir = -1;
    private int ballYdir = -2;
    private GenerateMap map;

    // designing the background, ball, bricks, platform.
    public void paint(Graphics g){
        // BackGround
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        // Score
        g.setColor(Color.white);
        g.setFont(new Font("Arial Black",Font.BOLD,30));
        g.drawString(""+score,590, 30);
        // PlatForm
        g.setColor(Color.yellow);
        g.fillRect(platformPos, 550, 100, 8); // platform position in x-axis = 310 ,y-axis = 550
        // Ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY,20, 20);
        // Bricks
        map.makeBricks((Graphics2D)g);
        // if ball touches the floor
        if(ballposY > 560){
            play = false; // game over
            ballXdir = -1;
            ballYdir = -2;
            g.setColor(Color.blue);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("GAME OVER , SCORE"+score, 170,300);
            g.drawString("ENTER to Continue",190,340);
        }
    }
    public GamePlay(){
        map = new GenerateMap(3,7);
        timer = new Timer(delay, this);
        timer.start();
        setFocusTraversalKeysEnabled(false); //tab or shift+tab keys are disabled
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) { // game is ON
            // if the ball hits the platform.
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(platformPos, 550, 100, 8))) {
                //ballXdir = -ballXdir;
                ballYdir = -ballYdir;
            }
            // if the ball hits the brick
            loops:
            for(int i = 0; i < map.bricksArray.length; i++){
                for(int j = 0; j < map.bricksArray[0].length; j++){
                    if(map.bricksArray[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        Rectangle brickRec = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRec = new Rectangle(ballposX, ballposY,20,20);
                        if(ballRec.intersects(brickRec)){
                            map.setValue(i,j,0);
                            totalBricks--;
                            score+=5;
                            // if the ball hits the brick horizontally left or right
                            if(ballposX + 19 <= brickRec.x || ballposX + 1 >= brickRec.x + brickWidth){
                                ballXdir = -ballXdir;//reverse the ball direction.
                            }
                            else // ball hits vertically
                                ballYdir = -ballYdir;
                            break loops;
                        }
                    }
                }
            }
            // if the ball does not hit any brick
            ballposX += ballXdir; // ball continues to move in x-axis.
            ballposY += ballYdir; // ball continues to move in y-axis.
            if(ballposX < 0) // if ball goes out of bound from left
                ballXdir = -ballXdir;
            if(ballposY < 0) //  ball goes out of roof
                ballYdir = -ballYdir;
            if(ballposX > 670) // if ball goes out of bound from right
                ballXdir = -ballXdir;
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(platformPos >= 600)
                platformPos = 600;
            else
                moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(platformPos < 10)
                platformPos = 10;
            else
                moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){ // not playing
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                platformPos = 120;
                totalBricks = 21;
                map = new GenerateMap(3,7);
                repaint();//calls paint function again.
            }
        }
    }
    public void moveRight(){
        play = true;
        platformPos += 20; // increase the position of platform by 20 in x-axis.
    }
    public void moveLeft(){
        play = true;
        platformPos -= 20; // decrease the position of platform by 20 in x-axis.
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
