import java.awt.*;

public class Sprite implements Move, Paint {

    Color color;
    int x, y, width, height;

    double dx, dy;

    final int SPEED = 10;

    /**
     * This constructor builds an object from the Sprite class. These will be the main actors of our, or any, game, blah blah blah
     *
     * @param color  This determines the color of our actor
     * @param x      This specifies the initial x location of our actor
     * @param y      This specifies the initial y location of our actor
     * @param width  This specifies the width to determine the size of our actor
     * @param height This specifies the height to determine the size of our actor
     */


    public Sprite(Color color, int x, int y, int width, int height){

        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        while (dx < 1 || dy < 1){
            double angle = 2 * Math.PI * Math.random();
            double speed = 1 + SPEED * Math.random();
            dx = Math.cos(angle) * speed;
            dy = Math.sin(angle) * speed;
        }
    }

    /**
     *
     * @param other Specifies the Sprite entity that is being collided with
     * @return      Gives a boolean value, true if the entities collided, false if not.
     */

    public boolean collidesWith(Sprite other){

        return this.getBounds().intersects(other.getBounds());

    }


    @Override
    public void move(int boardHeight, int boardWidth) {

        //Predictive Movement
        double nextLeft =  x + dx;
        double nextRight = x + width + dx;
        double nextTop = y + dy;
        double nextBottom = y + height + dy;

        if(nextTop <= 0 || nextBottom > boardHeight){
            dy*=-1;
        }
        if(nextLeft <= 0 || nextRight > boardWidth){
            dx*=-1;
        }
        x += dx;
        y += dy;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x + dx), (int)(y + dy), width, height);
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void setPosition(int x, int y) {

        this.x = x;
        this.y = y;
    }


}
