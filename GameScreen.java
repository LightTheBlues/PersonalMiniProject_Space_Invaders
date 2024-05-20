package edu.angelo.finalprojectli;

import android.graphics.Color;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver,
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        //play the game paused sound if the sound is enabled
                        Assets.paused.play(1);
                    }
                    state = GameState.Paused;
                    return;
                }
            }
            if (event.type == TouchEvent.TOUCH_DOWN) {
                //if it touch at least 64 above the ground
                if (event.y > 64) {
                }
            }

            if (event.type == TouchEvent.TOUCH_DOWN) {
                //move left if the player touch around the
                if (event.x < 64 && event.y > 416) {
                    world.player.velocityX = -4;
                }
                //move right if the player touch around the
                if (event.x > 100 && event.x < 164  && event.y > 416) {
                    world.player.velocityX = 4;
                }
                //shoot a bullet if the player touch around the
                if (event.x < 294 && event.x > 230 && event.y > 416) {
                    world.player.UserShoot();
                }
            }
            //when user no longer holding down move button, stop moving
            if (event.type == TouchEvent.TOUCH_UP) {
                world.player.velocityX = 0;
            }
        }


        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.bitten.play(1);
            }
            state = GameState.GameOver;
        }
        else if(world.endGame == true) {
            world.Restart();
        }
        if (oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if (Settings.soundEnabled) {
                Assets.eat.play(1);
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    }
                    if (event.y > 148 && event.y < 196) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        //save the score
                        Settings.addScore(world.score);
                        Settings.save(game.getFileIO());
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }

    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    Settings.addScore(world.score);
                    Settings.save(game.getFileIO());
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }



    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }
        //draw text draw the score
        drawText(g, score, g.getWidth() - score.length() * 20, 0);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();

        //draw the background
        g.drawPixmap(Assets.gameBackground, 0, 64);
        //if the enemy is alive, draw it, else don't draw it
        for (Enemy enemy : world.enemySet.EachEnemy) {
            //draw each target and its location decrease by 16
            if(enemy.isKilled == false) {
                g.drawPixmap(Assets.Enemy, (int)enemy.locationX - 12, (int)enemy.locationY - 12);
            }
        }
        //draw bullet for enemy
        for (bullet bullets : world.enemySet.EnmemisBullets.EachBullet) {
                g.drawPixmap(Assets.bulletGoDown, (int)bullets.locationX - 9, (int)bullets.locationY - 9);
        }
        //draw the player
        g.drawPixmap(Assets.player, (int)world.player.locationX -16,(int)world.player.locationY-16);
        //draw each bullet player have
        for (bullet bullets : world.player.PlayerBullets.EachBullet) {
            g.drawPixmap(Assets.bulletGoUp, (int)bullets.locationX-9, (int)bullets.locationY-9 );
        }
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 47, 100);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        //all the button for the user to click
        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 100, 416, 0, 64, 64, 64);
        g.drawPixmap(Assets.ShootButton, 230, 416);
        //g.drawRect(230, 416, 64, 64, Color.rgb(255,0,0));
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 80, 100);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
    }




    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i += 1) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
        if (world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
