import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {


    Timer timer;
    List<Sprite> actors = new ArrayList<Sprite>();

    final int friendlyNum = 50;

    public Board(){
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.BLACK);

        for(int i = 0; i < friendlyNum; i++){
            actors.add(new Friendly(Color.red, getWidth()/2, getHeight()/2, 5));
        }

        timer = new Timer(1000/60, this);
        timer.start();



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < actors.size(); i++){
            actors.get(i).paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < actors.size(); i++){
            actors.get(i).move(getHeight(), getWidth());
        }
        repaint();
    }
}
