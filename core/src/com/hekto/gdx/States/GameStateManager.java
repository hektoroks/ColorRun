package com.hekto.gdx.States;

/**
 * Created by hekto on 01/04/2017.
 */

public class GameStateManager {

    private GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 434232;

    public GameStateManager() {
        setState(MENU);
    }

    public void setState(int state) {
        if(gameState != null)
            gameState.dispose();

        if(state == MENU) {
            gameState = new MenuState(this);
        }

        if(state == PLAY) {
            gameState = new PlayState(this);
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }


}
