package edu.angelo.finalprojectli;

/**
 * bullet class create and define basic information and logic for the bullet
 */
public class bullet {

    public static final int minY = 64 + 16;
    public static final int maxY = World.WORLD_HEIGHT - 16;
    public int velocityY;
    public int locationX;
    public int locationY;
    // 0 = opp, 1 == player, it will effect collision
    public int which_Side_bullet = 2;

    /**
     * bullet function create a default functionally for each bullet bulletSet created
     * @param locationX locationX is the x location of the bullet it shoot
     * @param locationY locationY is the y location of the bullet it shoot
     * @param which_Side_bullet which_Side_bullet effect which collision it should call
     */
    public bullet(int locationX, int locationY, int which_Side_bullet) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.velocityY = 0;
        //base on which side bullet it define for,
        // it going down when opponent shoot it,
        // it going up when player shoot it.
        if (this.which_Side_bullet == 0) {
            this.velocityY = 5;
        } else if(this.which_Side_bullet == 1){
            this.velocityY = -5;
        }
    }

    /**
     * bullet_advance define how the bullet move regrades its side
     */
    public void Bullet_advance() {
            if ((this.locationY + this.velocityY > maxY)) {
                //if it touch the lower bounce in the x direction
                this.velocityY = 0;
                this.locationX = -5;
                this.locationY = -5;
            } else {
                this.locationY = this.locationY + this.velocityY;
            }
            if ((this.locationY + this.velocityY < minY)) {
                //if it touch the lower bounce in the x direction
                this.velocityY = 0;
                this.locationX = -5;
                this.locationY = -5;
            } else {
                this.locationY = this.locationY + this.velocityY;
            }
        }
    }

