package com.hekto.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.hekto.gdx.screens.GameScreen;

/**
 * Created by hekto on 27/03/2017.
 */

public class GameController implements InputProcessor {

    private GameScreen gameScreen;
    public int                  tappedColor;

    public GameController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * @param keycode
     * @return Game skeleton shower
     */
    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Input.Keys.CONTROL_LEFT:
                gameScreen.debugRend = !gameScreen.debugRend;
                break;
            case Input.Keys.DEL:
            {
                gameScreen.myGdxGame.setScreen(new GameScreen(gameScreen.myGdxGame));
            }
            break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
