import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {


    Timer timer;
    List<Sprite> actors = new ArrayList<Sprite>();

    final int friendlyNum = 10;
    final int enemyNum = 5;

    public Board(){
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void setup(){

        actors.add(0, new Friendly(Color.yellow, getWidth()/2, getHeight()/2, 15));

        for(int i = 0; i < friendlyNum; i++){
            actors.add(new Friendly(Color.red, getWidth()/2, getHeight()/2, 20));
        }

        for(int i = 0; i < enemyNum; i++){
            actors.add(new Enemies(Color.CYAN, getWidth()/2, getHeight()/4, 10, 20));
        }

    }

    public void checkCollisions(){

        for(int i = 0; i < actors.size(); i++){
            for(int j = 0; j < actors.size(); j++){
                if(i != j){
                    if(actors.get(i).collidesWith(actors.get(j))){
                        if(actors.get(i) instanceof Friendly && actors.get(j) instanceof Friendly){

                            actors.get(i).bounce();
                            actors.get(j).bounce();

                        }

                        if(actors.get(i) instanceof Enemies && actors.get(j) instanceof Enemies){
                            actors.get(i).bounce();
                            actors.get(j).bounce();
                        }
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < actors.size(); i++){
            actors.get(i).paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        checkCollisions();

        for(int i = 1; i < actors.size(); i++){
            actors.get(i).move(getHeight(), getWidth());
        }

        repaint();
    }
}
