package com.hekto.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hekto.gdx.MyGdxGame;
import com.hekto.gdx.controller.GameController;

/**
 * Created by hekto on 31/03/2017.
 */

public class MenuScreen extends InputAdapter implements Screen {

    private MyGdxGame myGdxGame;
    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Color Run";
    private int currentItem;
    private String[] menuItems;


    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        sb = new SpriteBatch();
        titleFont = new BitmapFont();//Gdx.files.internal("angry.ttf"), true);
        titleFont.getData().setScale(2f);
        font = new BitmapFont();

        menuItems = new String[]{"Play", "Highscores", "Quit"};
        Gdx.input.setInputProcessor(new InputMultiplexer(this));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        sb.begin();

        titleFont.draw(sb, title, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 100);

        for (int i = 0; i < menuItems.length; i++) {
            if (currentItem == i) {
                font.setColor(Color.RED);
            } else font.setColor(Color.WHITE);
            font.draw(sb, menuItems[i], Gdx.graphics.getWidth() / 2, 300 - 20 * i);
        }
        sb.end();
    }


    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.UP) {
            if (currentItem > 0) currentItem--;
        }

        if (keycode == Input.Keys.DOWN) {
            if (currentItem < menuItems.length - 1) currentItem++;
        }

        if (keycode == Input.Keys.ENTER) {
            select();
        }

        return false;
    }

    /* Selected Item */
    private void select() {
        //play
        if (currentItem == 0) {
            myGdxGame.setScreen(new GameScreen(myGdxGame));
        } else if (currentItem == 1) {
            //myGdxGame.setScreen(new HighScoreScreen(myGdxGame));
        } else if (currentItem == 2) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        sb.dispose();
        font.dispose();
        titleFont.dispose();
    }
}
