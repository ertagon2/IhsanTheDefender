import javax.swing.*;
import java.awt.*;


public class Ihsan {
    //environment
    private int jpWidth, jpHeight;
    GamePanel gp;
    //properties
    private Image image, image2, image3, image4;
    private int x,y;
    private int width, height;
    //management
    private boolean goRight=false;
    private boolean goLeft=false;
    private boolean shoot=false;

    Ihsan(GamePanel gp){
        this.gp=gp;
        //load the image
        image = new ImageIcon(getClass().getResource("images/ihsan.png")).getImage();
        image2 = new ImageIcon(getClass().getResource("images/bluej.png")).getImage();
        image3 = new ImageIcon(getClass().getResource("images/frunk.png")).getImage();
        image4 = new ImageIcon(getClass().getResource("images/david2.png")).getImage();
//        image = new ImageIcon("C:\\Users\\jaros\\OneDrive\\Private Projects\\Ihsan Invaders\\src\\images\\ihsan.png").getImage();

        //get panel size
        //jpWidth=jp.getWidth returns 0 hence :
        jpWidth=gp.getPreferredSize().width;
        jpHeight=gp.getPreferredSize().height;
        //get ihsan size
        width=image.getWidth(null);
        height=image.getHeight(null);
        //set ihsan position
        x=jpWidth/2-width/2;
        y=jpHeight-height;
        Thread t = new Thread();
        t.start();
    }

    public void move(int xVector, int yVector){
        if(gp.getGameOver()){return;}
        if(x<=0-width/2 && xVector<0){return;}
        if(x>=jpWidth-width/2 && xVector>0){return;}
        x+=xVector;
        y+=yVector;
    }


    public Image getImg(){
        if(gp.getScore()>=100 && gp.getScore()<250){return image2;}
        if(gp.getScore()>=250 && gp.getScore()<1000){return image3;}
        if(gp.getScore()>=1000){return image4;}
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isGoLeft() {
        return goLeft;
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public boolean isGoRight() {
        return goRight;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }
}
