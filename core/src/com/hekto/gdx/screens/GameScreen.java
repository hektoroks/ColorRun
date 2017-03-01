package com.hekto.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hekto.gdx.MyGdxGame;
import com.hekto.gdx.model.Car;

/**
 * Created by hekto on 26/02/2017.
 */

public class GameScreen implements Screen {

    MyGdxGame myGdxGame;
    private float width, height;
    public World world;
    SpriteBatch batch;
    Car car;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -10f), true);      // creating the box2D  world

        car = new Car(10, 10, 3, 1.5f, this);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.getGL20().glClearColor(0, 0.4f, 0.7f, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        car.update();
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
        world.dispose();

    }
}
