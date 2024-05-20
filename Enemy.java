package edu.angelo.finalprojectli;

import java.util.ArrayList;
import java.util.List;

public class Enemy {

    public static final int minX = 16;

    /**
     * The farthest right the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxX = World.WORLD_WIDTH - 16;

    /**
     * The farthest up the center of a target can go, leaving room at the top of the screen for
     * the pause button and score and for the radius of the target.
     */
    public static final int minY = 64 + 16;

    /**
     * The farthest down the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxY = World.WORLD_HEIGHT - 16;
    public int velocityX;
    public int locationX;
    public int locationY;
    public int startX;
    public int startY;
    // isKilled determine if I am going to draw this Enemy later
    public boolean isKilled;
    public int movedown = 1;
    // 0 move down 1 stay


    //int direction = 0; //0 = right, 1 = left

    /**
     * default Enemy object
     * @param locationX give it location x to start
     * @param locationY give it location y to start
     */
    public Enemy(int locationX, int locationY) {
        startX = locationX;
        startY = locationY;
        this.locationX = startX;
        this.locationY = startY;
        this.isKilled = false;
        this.velocityX = 2;

    }

    /**
     * if it touch the bounce
     * change direction of the enemy moving direction
     * else it just move
     */
    public void advance() {

        if ((locationX + velocityX < minX)) {
            //if it touch the lower bounce in the x direction
            locationX = (minX - locationX) - velocityX + minX;
            movedown = 0;
        } else if (((locationX + velocityX > maxX))) {
            //if it touch the upper bounce in the x direction
            locationX = (locationX - maxX) + velocityX + maxX;
            movedown = 0;
        } else {
            locationX = locationX + velocityX;
        }
    }

}
