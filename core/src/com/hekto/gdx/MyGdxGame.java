package com.hekto.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.hekto.gdx.screens.WelcomeScreen;

/**
 * Created by hekto on 26/02/2017.
 */

public class MyGdxGame extends Game {
    @Override
    public void create() {
        setScreen(new WelcomeScreen(this));
    }
}
