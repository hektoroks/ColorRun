package com.hekto.gdx.States;

/**
 * Created by hekto on 01/04/2017.
 */

public abstract class GameState {

    protected  GameStateManager gsm;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
