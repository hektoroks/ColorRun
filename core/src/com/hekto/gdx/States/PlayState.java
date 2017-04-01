package com.hekto.gdx.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hekto.gdx.MyGdxGame;
import com.hekto.gdx.screens.GameScreen;

/**
 * Created by hekto on 01/04/2017.
 */

public class PlayState extends GameState {

    private MyGdxGame myGdxGame;
    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Color Run";
    private int currentItem;
    private String[] menuItems;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        this.myGdxGame = myGdxGame;
        sb = new SpriteBatch();
        titleFont = new BitmapFont();
        font = new BitmapFont();

        menuItems = new String[]{"Play", "Highscores", "Quit"};
    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        titleFont.draw(sb, title, 300,300);

        for(int i = 0; i<menuItems.length; i++) {
            if(currentItem == i) {
                font.setColor(Color.RED);
            }
            font.draw(sb, menuItems[i],300, 300 -20 * i);
        }
        sb.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {
        sb.dispose();
    }
}
