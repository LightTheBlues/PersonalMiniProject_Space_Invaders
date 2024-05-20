package edu.angelo.finalprojectli;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class Space_Invaders_Game extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
