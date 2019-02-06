import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    private static final Dimension DESIRED_SIZE = new Dimension(600,700);
    Image gameOverImg = new ImageIcon(getClass().getResource("images/gameover1.png")).getImage();
//    Image gameOverImg = new ImageIcon("C:\\Users\\jaros\\OneDrive\\Private Projects\\Ihsan Invaders\\src\\images\\gameover1.png").getImage();
    private Ihsan ihsan;
    private ArrayList <David> davids = new ArrayList<>();
    private int score=0;
    private double enemies=12;
    private boolean pause=false;
    private boolean gameOver=false;

    GamePanel() {
        ihsan = new Ihsan(this);
        for (int i = 0; i < enemies; i++) {
            davids.add(new David(this));
        }
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

//        Shooting
        new Thread(()->{
            while (!pause && !gameOver) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) { }
                if (ihsan.isShoot()) {
                    Bullet.bullets.add(new Bullet(this, ihsan.getX() + ihsan.getWidth() / 2, ihsan.getY() + ihsan.getHeight() / 2));
                    if(score<100) {
                        try {
                            Thread.sleep(1300);
                        } catch (InterruptedException e) { }
                    }
                    if(score>=100 && score<250){
                        try {
                            Thread.sleep(450);
                        } catch (InterruptedException e) { }
                    }
                    if(score>=250 && score<1000) {
                        try {
                            Thread.sleep(170);
                        } catch (InterruptedException e) {}
                    }
                    if(score>=1000){
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) { }
                    }
                }
            }
        }).start();

//        KeyEvent handling
        new Thread(() -> {
            while (!pause && !gameOver) {
                try{Thread.sleep(6);}
                catch (InterruptedException e){}
                if(ihsan.isGoRight()){
                    if(score<100){ihsan.move(1,0);}
                    if(score>=100 && score<250){ihsan.move(2,0);}
                    if(score>=250 && score<1000){ihsan.move(3,0);}
                    if(score>=1000){ihsan.move(4,0);}
                }

                if(ihsan.isGoLeft()){
                    if(score<100){ihsan.move(-1,0);}
                    if(score>=100 && score<250){ihsan.move(-2,0);}
                    if(score>=250 && score<1000){ihsan.move(-3,0);}
                    if(score>=1000){ihsan.move(-4,0);}
                }
            }
        }).start();
    }

    @Override
    public void run() {
        while (!pause && !gameOver){

            if(davids.size()<Math.sqrt(score)){
                enemies+=0.4;
                for(int i=0; i<enemies; i++){
                    davids.add(new David(this));
                }
            }

            Iterator<David> it2 = davids.iterator();
            while (it2.hasNext()) {
                David david = it2.next();
                if (david.move()) { // if david should be removed
                    it2.remove(); // remove it from the list
                    score++;
                }
            }

            Iterator<Bullet> it = Bullet.bullets.iterator();
            while (it.hasNext()) {
                Bullet bullet = it.next();
                if (bullet.move() || bullet.isRemove()){ // if bullet should be removed
                    it.remove(); // remove it from the list
                }
            }
            repaint();
            try{Thread.sleep(30);}
            catch (InterruptedException e){}
        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,0 ,getWidth(), getHeight());

        for(David david : davids){
            g2d.drawImage(david.getImg(), david.getX(), david.getY(), null);
        }
        g2d.drawImage(ihsan.getImg(), ihsan.getX(), ihsan.getY(), null);

        for (Bullet bullet : Bullet.bullets) {
            g2d.drawImage(bullet.getImg(), bullet.getX(), bullet.getY(), null);
        }

        g2d.setFont(new Font( "Times", Font.PLAIN, 20 ));
        g2d.setColor(Color.BLACK);
        String scoreStr = "Score: "+String.valueOf(score);
        g2d.drawString(scoreStr,0,20);

        if(gameOver){
            g2d.drawImage(gameOverImg,0,getHeight()/4,null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key== KeyEvent.VK_RIGHT || key== KeyEvent.VK_D){
            ihsan.setGoRight(true);
        }

        if(key== KeyEvent.VK_LEFT || key== KeyEvent.VK_A){
            ihsan.setGoLeft(true);
        }

        if(key== KeyEvent.VK_SPACE){
            ihsan.setShoot(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();

        if(key== KeyEvent.VK_RIGHT || key== KeyEvent.VK_D){
            ihsan.setGoRight(false);
        }

        if(key== KeyEvent.VK_LEFT || key== KeyEvent.VK_A){
            ihsan.setGoLeft(false);
        }

        if(key== KeyEvent.VK_SPACE){
            ihsan.setShoot(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    public boolean getGameOver(){ return gameOver; }

    @Override
    public Dimension getPreferredSize(){ return DESIRED_SIZE;}

    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }

    public int getScore(){return score;}
}
