package edu.angelo.finalprojectli;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.ARGB8888);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB8888);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB8888);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB8888);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB8888);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB8888);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB8888);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB8888);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB8888);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB8888);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB8888);
        Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB8888);
        Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB8888);
        Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB8888);
        Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB8888);
        Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB8888);
        Assets.bulletGoDown = g.newPixmap("bulletGoDown.png", PixmapFormat.ARGB8888);
        Assets.bulletGoUp = g.newPixmap("bulletGoUp.png", PixmapFormat.ARGB8888);
        Assets.Enemy = g.newPixmap("Enemy.png", PixmapFormat.ARGB8888);

        //new target pixamp object
        Assets.player = g.newPixmap("player.png", PixmapFormat.ARGB8888);
        Assets.ShootButton = g.newPixmap("ShootButton.png", PixmapFormat.ARGB8888);
        //new target gameBackground object
        Assets.gameBackground = g.newPixmap("gameBackground.png", PixmapFormat.ARGB8888);
        //add this line for target
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        //changed the sound instead bitten.ogg to gamover.ogg
        Assets.bitten = game.getAudio().newSound("gameover.ogg");
        //a new sound is add name paused.ogg
        Assets.paused = game.getAudio().newSound("paused.ogg");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}
