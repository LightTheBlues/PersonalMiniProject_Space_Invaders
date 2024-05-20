package edu.angelo.finalprojectli;


//320 x (higher number, more to the right)
//480 y (higher number, more to the botton)


public class player {

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

    public int playernumberBullet = 3;

    /**
     * PlayerBullets give player total of playernumberBullet amount of bullets
     */
    bulletSet PlayerBullets = new bulletSet(1, playernumberBullet);
    public int velocityX;
    public int locationX;
    public int locationY;

    /**
     * default for player, so it can draw according in gameScreen
     */
    public player() {
        this.locationX = 160;
        this.locationY = 400;
        this.velocityX = 0;
    }

    /**
     * UserShoot are called when user click on the shoot button
     */
    public void UserShoot() {
        //for (bullet targetBullet : EachBullet) {
        //find the first free to move bullet
        for(int i = 0; i < PlayerBullets.EachBullet.size(); i++ ) {
            if (PlayerBullets.getNumOfBulletInfo(i).velocityY == 0) {
                PlayerBullets.getNumOfBulletInfo(i).locationX = this.locationX;
                PlayerBullets.getNumOfBulletInfo(i).locationY = this.locationY;
                PlayerBullets.getNumOfBulletInfo(i).velocityY = -7; //dont know which way this go
                PlayerBullets.getNumOfBulletInfo(i).which_Side_bullet = 1;
                //after first find bullet, break it
                break;
            }
        }
    }

    /**
     * advancePlayer move player when it was called and if its not touching either bound
     */
    public void advancePlayer() {
        if ((locationX + velocityX < minX)) {
            velocityX = 0;
        } else if (((locationX + velocityX > maxX))) {
            //if it touch the upper bounce in the x direction
            velocityX = 0;
        } else {
            locationX = locationX + velocityX;
        }
    }



}
