package edu.angelo.finalprojectli;

import java.util.ArrayList;
import java.util.List;

/**
 * bulletSet keep all bullet within a set for track
 */
public class bulletSet {
    public List<bullet> EachBullet;


    /**
     * bulletSet create bullets basic of numOfBullet it received and give a sie for collision
     * @param which_Side_bullet
     * @param numOfBullet
     */
    public bulletSet(int which_Side_bullet, int numOfBullet) {
        EachBullet = new ArrayList<bullet>();
        // put in actual bullet object
        while(EachBullet.size() <  numOfBullet) {
            EachBullet.add(new bullet(-50,-50, which_Side_bullet));
        }
    /*    for (bullet targetBullet : EachBullet) {
            if (targetBullet.velocityY == 0) {
                targetBullet.locationX = locationX;
                targetBullet.locationY = locationY;
                targetBullet.which_Side_bullet = which_Side_bullet;
                if (targetBullet.which_Side_bullet == 0) {
                    targetBullet.velocityY = 2;
                } else if (targetBullet.velocityY == 1) {
                    targetBullet.velocityY = -2;
                }
                break;
            }
        }
        */
    }

    /**
     * Bulletadvance advance every bullet in the same time
     */
    public void Bulletadvance() {
        for (bullet targetBullet : EachBullet) {
            targetBullet.Bullet_advance();
        }
    }

    /**
     * use this function to get detail inforamtion for the bullet, got help from JP
     * @param x which bullet it looking for
     * @return return the information
     */
    public bullet getNumOfBulletInfo(int x) {
        return EachBullet.get(x);
    }
}
/*

            if(targetBullet.velocityY == 0) {
                if (targetBullet.which_Side_bullet == 0) {
                    targetBullet.velocityY = 2;
                } else if (targetBullet.velocityY == 0) {
                    targetBullet.velocityY = -2;
                }
 */