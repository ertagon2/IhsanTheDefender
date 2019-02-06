import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Random;

public class David {
//    environment
    private GamePanel gp;
    Random rand = new Random();
    private int jpWidth, jpHeight;
    private int score;
//    properties
    private Image image;
    private int x,y;
    private int yVector=1, xVector;
    private int width, height;

    David(GamePanel gp){
        this.gp=gp;
//        load the image
        image = new ImageIcon(getClass().getResource("images/david.png")).getImage();
//        image = new ImageIcon("C:\\Users\\jaros\\OneDrive\\Private Projects\\Ihsan Invaders\\src\\images\\david.png").getImage();

//        get david size
        width=image.getWidth(null);
        height=image.getHeight(null);
//        get GamePanel's dimensions
        jpWidth=gp.getPreferredSize().width;
        jpHeight=gp.getPreferredSize().height;
//        random position
//        Math.random for better random-start-position:
        x= (int) (Math.random()*(jpWidth-width));
//        x=rand.nextInt(jpWidth-width);
        y=-rand.nextInt(height*3)-height;
//        scaling yVector
        score=gp.getScore();
        if(score>100 && score<250) {
            yVector = 2;
        }
        if(score>=250 && score<1000){
            yVector=3;
        }
        if(score>=1000){
            yVector=5;
        }
    }

    private int i=0;
    public boolean move(){
        y+=yVector;
        if(i>20){
            xVector = rand.nextInt(3) - 1;
            i = 0;
        }else {i++;}
//        overwrite xVector if about to hit the border
        if(x<1){xVector=1;}
        if(x+width>jpWidth-1){xVector=-1;}

        x+=xVector;
        if(y+height>jpHeight){gp.setGameOver(true);}

//        Collision
        Iterator<Bullet> it = Bullet.bullets.iterator();
        while (it.hasNext()){
            Bullet bullet = it.next();
            if(isHit(bullet)){
                bullet.setRemove(true);
                return true;
            }
        }
        return false;
    }

    public boolean isHit(Bullet bullet){
        if(bullet.getY()<=y+height && bullet.getY()>=y){
            if(bullet.getX()+bullet.getWidth()>=x && bullet.getX()<=x+width){
                return true;
            }
        }
        return false;
    }

    public Image getImg(){
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}
}
