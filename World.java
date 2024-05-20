package edu.angelo.finalprojectli;



/* The Lens Of Fun
        1.its a Classic arcade game that people enjoy before
           2. difficult and mores levels

    1. Avoid creating unnecessary objects; recycle the same object
    2. I used some enhance for loop syntax for example EnemySet, World, or in GameScreen

 */




public class World {
    //change the width and the height of the game
    //make the world bigger
    //do the correstion here

    static final int WORLD_WIDTH = 320;
    static final int WORLD_HEIGHT = 480;
    static final int SCORE_INCREMENT = 10;

    static final int SCORE_DECREMENT = -1;
    //TICK_INITIAL is how fast when you start
    static final float TICK_INITIAL = 0.1f;
    //every 100 points this is how much faster it go
    boolean endGame = false;
    public boolean gameOver = false;
    public int score = 0;
    float tickTime = 0;
    float tick = TICK_INITIAL;

    //public field
    public TargetSet targetSet;
    public EnemySet enemySet;
    public player player = new player();

    public World() {
        enemySet = new EnemySet();
    }

    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
            //change from snake.advance, now calling targetSet.advance
            //call the all advance that is require
            enemySet.advance();
            player.advancePlayer();
            enemySet.EnmemisBullets.Bulletadvance();
            player.PlayerBullets.Bulletadvance();

            //look for corr
            //for each bullet from player, and every enemy in enemySet
            for (int i = 0; i < player.PlayerBullets.EachBullet.size(); i++) {
                for (int x = 0; x < enemySet.EachEnemy.size(); x++) {
                    //if it was near the enemy and the EachEnemy's kill status is false
                    if (Math.abs(player.PlayerBullets.getNumOfBulletInfo(i).locationX - enemySet.EachEnemy.get(x).locationX) < 15 &&
                            Math.abs(player.PlayerBullets.getNumOfBulletInfo(i).locationY - enemySet.EachEnemy.get(x).locationY) < 15 &&
                            enemySet.EachEnemy.get(x).isKilled == false) {
                        //now its died
                        enemySet.EachEnemy.get(x).isKilled = true;
                        //reset the bullet
                        player.PlayerBullets.getNumOfBulletInfo(i).velocityY = 0;
                        player.PlayerBullets.getNumOfBulletInfo(i).locationX = -50;
                        player.PlayerBullets.getNumOfBulletInfo(i).locationY = -50;
                        //give player score
                        score += SCORE_INCREMENT;
                        //break after the first enemy died
                        break;
                    }
                }
            }

            //for each bullet shoot from enemy, check if player got hit
            for (int i = 0; i < enemySet.EnmemisBullets.EachBullet.size(); i++) {
                //if the bullet got close enough
                if (Math.abs(enemySet.EnmemisBullets.getNumOfBulletInfo(i).locationX - player.locationX) < 15 &&
                        Math.abs(enemySet.EnmemisBullets.getNumOfBulletInfo(i).locationY - player.locationY) < 15) {
                    //I don't think its necessary to do this but I did it so it look same with above
                    enemySet.EnmemisBullets.getNumOfBulletInfo(i).velocityY = 0;
                    enemySet.EnmemisBullets.getNumOfBulletInfo(i).locationX = -50;
                    enemySet.EnmemisBullets.getNumOfBulletInfo(i).locationY = -50;
                    //the game is over when player got shoot
                    gameOver = true;
                }
            }
            //if enemy is alive and reach to the player, we lost
            for(Enemy enemy : enemySet.EachEnemy) {
               if(enemy.locationY >= player.locationY && enemy.isKilled == false) {
                   gameOver = true;
                   break;
               }
            }

            int i = 0;
            //if all enemy is died, set the end game to true, so the enemy can restart from beginning
            while ( i < enemySet.EachEnemy.size()) {
                if (enemySet.EachEnemy.get(i).isKilled == false)
                {
                    //find the first alive, it false
                    endGame = false;
                    break;
                } else {
                    endGame = true;
                }
                i++;
            }
            //after this loop, it should check every enemy that is define in the enemySet
        }
    }

    /**
     * restart the game if all enemy is died
     */
    public void Restart() {
        //for all enemy in enemySet
        for(Enemy enemy : enemySet.EachEnemy) {
            //special case if we end the game in right spot, because it have negative velocity
            //the enemy will stop moving because it got decreased
            if(enemy.velocityX < 0) {
                enemy.velocityX *= -1;
            }
            //increase velocity for enemy
            enemy.velocityX += 0.5;
            //enemy back alive
            enemy.isKilled = false;
            //enemy back to the original location
            enemy.locationX = enemy.startX;
            enemy.locationY = enemy.startY;
        }
        //enemy shoot faster
        enemySet.bulletsVelocityY += 1;
    }
}
