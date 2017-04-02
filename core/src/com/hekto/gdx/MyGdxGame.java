package com.hekto.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.hekto.gdx.States.GameStateManager;
import com.hekto.gdx.screens.MenuScreen;
import com.hekto.gdx.screens.WelcomeScreen;

/**
 * Created by hekto on 26/02/2017.
 */

public class MyGdxGame extends Game {

    private GameStateManager gsm;

    @Override
    public void create() {

        gsm = new GameStateManager();
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();

        //setScreen(new WelcomeScreen(this));
       // setScreen(new MenuScreen(this));
    }
}
