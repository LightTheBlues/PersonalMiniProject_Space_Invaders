package edu.angelo.finalprojectli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * EnemySet keep all Enemy within a set for track
 */
public class EnemySet {

    public List<Enemy> EachEnemy;
    //rnd are used for shooting, base on the rnd number, Enemy will shoot when rnd got 1
    Random rnd = new Random();
    public int bulletsVelocityY = 5;
    //give EnmemisBullets 10 bullet
    bulletSet EnmemisBullets = new bulletSet( 0, 10);


    /**
     * default for EnemySet, so it can draw according in gameScreen
     */
    public EnemySet() {
        EachEnemy = new ArrayList<Enemy>();


        //draw each Enemy in the EnemySet base on the row and col, start from 0,0, go to 5,9
        // total 45 Enemy
        for (int col = 1; col <= 5; col++) {
            for (int row = 1; row <= 9; row++) {
                EachEnemy.add(new Enemy(row * 28, 28 * col +50));
            }
        }
    }

    /**
     * when first Enemy reach the bounce, move every Enemy down by a row
     * @param movedown to double check it should move down or not, to prevent accident mvoedown
     */
    public void changeDiction(int movedown) {
        // 0 meant move down
        if (movedown == 0) {
            for (Enemy target : EachEnemy) {
                target.velocityX = target.velocityX * -1;
                target.movedown = 1;
                target.locationY += 12;
            }
        }

    }

    /**
     * how every Enemy should move
     */
    public void advance() {
        for (Enemy target : EachEnemy) {
            //call the Enemy advance function (this is how each Enemy move)
            target.advance();
            //if the movedown set to 0 and the Enemy on that location is alive
            if (target.movedown == 0 && target.isKilled == false) {
                //call move down on EnemySet to move all Enemy down
                changeDiction(target.movedown);
            }
                                            //lower to make game easier
            if(rnd.nextInt(250) == 1 && target.isKilled == false) {
                //shoot a bullet if it rolled 1
                EnemyShoot(target.locationX-9, target.locationY-9);
            }
        }
    }

    /**
     * one Enemy shoot a bullet base on its location
     * @param locationX locationX is the x location where bullet should shoot
     * @param locationY locationY is the y location where bullet should shoot
     */
    public void EnemyShoot(int locationX, int locationY) {
            //for (bullet targetBullet : EachBullet) {
            //find the first free to shoot bullet
            for (int i = 0; i < EnmemisBullets.EachBullet.size(); i++) {
                //0 velocity meant it was ready to re-shoot
                if (EnmemisBullets.getNumOfBulletInfo(i).velocityY == 0) {
                    EnmemisBullets.getNumOfBulletInfo(i).locationX = locationX;
                    EnmemisBullets.getNumOfBulletInfo(i).locationY = locationY;
                    EnmemisBullets.getNumOfBulletInfo(i).velocityY = bulletsVelocityY; //down
                    EnmemisBullets.getNumOfBulletInfo(i).which_Side_bullet = 0;
                    //when first free bullet got shoot, break it
                    break;
                }
            }
    }
}
