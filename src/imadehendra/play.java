package imadehendra;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class play extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;

    private int tBrick;

    //movespeed ball
    private Timer tmr;
    private int dly = 5;

    private int playerX = 210;

    private int bolposX = 200;
    private int bolposY = 350;
    private int boldirectionX = -1;
    private int boldirectionY = -2;

    private bata bta;

    int level = 1 ;

    public play(){

        bta = new bata(1, 7);
        tBrick = 7;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        tmr = new Timer(dly, this);
        tmr.setDelay(dly);
        tmr.start();
      }


    public void paint(Graphics g){

        //backgroud
        g.setColor(Color.WHITE);
        g.fillRect(1,1, 392, 592);

        //brick
        bta.draw((Graphics2D) g);

        //borders
        g.setColor(Color.BLACK);
        g.fillRect(-2,0,10,599);
        g.fillRect(0,0,492,10);
        g.fillRect(388,0,10,599);

        //paddle
        g.setColor(Color.BLACK);
        g.fillRect(playerX, 550, 60, 8);

        //ball
        g.setColor(Color.BLACK);
        g.fillOval(bolposX, bolposY, 15, 15);

        //score
        g.setColor(Color.red);
        g.setFont(new Font("Consolas", Font.BOLD, 15));
        g.drawString("SCORE "+score, 300, 30);
        g.drawString("LEVEL  "+level, 20, 30);
        //g.drawString("YOU LOSE ! PRESS ENTER TO REPLAY ", 100, 300);


        //gameover
        if (bolposY >550){
            play = false;
            bolposY = 0;
            bolposX = 0;
            //tBrick = 0;
            score = 0;
        }
        //gamefinish
        else if (tBrick == 0 ){
            play = false;
            bolposY = 0;
            bolposX = 0;
            level = 1;
            g.setColor(Color.red);
            g.setFont(new Font("Consolas", Font.BOLD, 15));
            g.drawString("YAY! PRESS ENTER TO PLAY NEXT LEVEL ", 60, 300);
        }
        //level
        if(score == 35){
            level = 2;
        }else if (score == 105){
            level = 3;
        }else if (score == 210){
            level = 4;
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tmr.restart();

        if(play){

            if(new Rectangle(bolposX, bolposY, 15, 15 ).intersects(new Rectangle(playerX, 550, 60, 8))){
                boldirectionY = -boldirectionY;
            }

            //ball touch brick
          A:  for (int i = 0; i < bta.bt.length; i++){
                for (int j = 0; j < bta.bt[0].length; j++){
                    if(bta.bt[i][j] > 0){
                        int bX = j * bta.bwidth + 32;
                        int bY = i * bta.bheight + 50;
                        int bwidth = bta.bwidth;
                        int bheight = bta.bheight;

                        Rectangle rect = new Rectangle(bX, bY, bwidth, bheight);
                        Rectangle bolRect = new Rectangle(bolposX,bolposY,15,15);
                        Rectangle btRect = rect;

                        if (bolRect.intersects(btRect)){
                            bta.setBtValue(0,i,j);
                            tBrick --;
                            score += 5;

                            //left right touch
                            if(bolposX + 10 <= btRect.x || bolposX + 1 >= btRect.x + btRect.width){
                                boldirectionX = -boldirectionX;
                            }else {
                                boldirectionY = -boldirectionY;
                            }
                            break A;
                        }
                    }
                }
            }

            //ball moving
            bolposX += boldirectionX;
            bolposY += boldirectionY;
            if(bolposX < 5){
                boldirectionX = -boldirectionX;
            }if(bolposY < 5){
                boldirectionY = -boldirectionY;
            }if(bolposX >370){
                boldirectionX = -boldirectionX;
            }
        }

       /* else if(play = false){
            Timer.getLogTimers();

        }   */
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 300){
                playerX = 359;
            }else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <=10){
                playerX = 10;
            }else {
                moveLeft();
            }
        }

        //restart game after gameover or game finish
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                bolposY = 250;
                bolposX = 120;
                boldirectionX = -1;
                boldirectionY = -2;
                playerX = 210;

                if (level == 1) {
                    bta = new bata(1, 7);
                    tBrick = 7;
                    score = 0;

                }else if (level == 2) {
                    bta = new bata(2, 7);
                    tBrick = 14;
                    score = 35;
                }else if (level == 3) {
                    bta = new bata(3, 7);
                    tBrick = 21;
                    score = 105;
                } else if (level == 4) {
                    bta = new bata(4, 7);
                    tBrick = 28;
                    score = 210;
            }
                repaint();
                System.out.println("level " + level);
                System.out.println("delay " + dly);
                System.out.println("timer " + tmr.getDelay());
                System.out.println("tbrick " + tBrick);

            }
        }

    }

    public void moveRight(){
        play = true;
        playerX+=50;

    }
    public void moveLeft(){
        play = true;
        playerX-=50;

    }
}



