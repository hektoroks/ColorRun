package com.hekto.gdx.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hekto.gdx.MyGdxGame;

/**
 * Created by hekto on 01/04/2017.
 */

public class MenuState extends GameState {

    private MyGdxGame myGdxGame;
    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Color Run";
    private int currentItem;
    private String[] menuItems;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        this.myGdxGame = myGdxGame;
        sb = new SpriteBatch();
        titleFont = new BitmapFont();//Gdx.files.internal("angry.ttf"), true);
        titleFont.getData().setScale(2f);
        font = new BitmapFont();

        menuItems = new String[]{"Play", "Highscores", "Quit"};
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        //Gdx.gl.glClearColor(0, 0, 0, 0);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();

        titleFont.draw(sb, title, Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() - 100);

        for(int i = 0; i<menuItems.length; i++) {
            if(currentItem == i) {
                font.setColor(Color.RED);
            } else font.setColor(Color.WHITE);
            font.draw(sb, menuItems[i],Gdx.graphics.getWidth()/2, 300 -20 * i);
        }
        sb.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
