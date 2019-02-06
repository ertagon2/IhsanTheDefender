import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bullet {
    //Environment
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    private GamePanel gp;
    private int score;
    //properties
    private int x,y;
    private int width,height;
    private int yVector;
    private Image image;
    //Management
    private boolean remove=false;


    Bullet(GamePanel gp, int x, int y){
        image = new ImageIcon(getClass().getResource("images/javaicon.png")).getImage();
//        image = new ImageIcon("C:\\Users\\jaros\\OneDrive\\Private Projects\\Ihsan Invaders\\src\\images\\javaicon.png").getImage();

        width=image.getWidth(null);
        height=image.getHeight(null);
        this.gp=gp;
        this.x=x;
        this.y=y;
        score=gp.getScore();
        if(score<100){
            yVector=5;
        }
        if(score>=100){
            yVector=7;
        }
        if(score>=250){
            yVector=12;
        }
        if(score>=1000){
            yVector=23;
        }
    }

    public boolean move(){
        if (y < -height) {
            return true;
        }
        y-=yVector;
        return false;
    }

    public Image getImg(){
        return image;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setRemove(boolean remove){
        this.remove=remove;
    }

    public boolean isRemove() {
        return remove;
    }
}
