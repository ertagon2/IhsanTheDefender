import javax.swing.*;

public class Application {

    private Application(){
        JFrame frame = new JFrame("Ihsan The Defender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel= new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        new Thread(gamePanel).start();
    }

    public static void main (String args[]){
        new Application();
    }
}
