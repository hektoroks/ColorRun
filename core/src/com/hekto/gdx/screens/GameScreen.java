package com.hekto.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hekto.gdx.MyGdxGame;
import com.hekto.gdx.model.Car;

/**
 * Created by hekto on 26/02/2017.
 */

public class GameScreen implements Screen {

    public MyGdxGame            myGdxGame;
    private float               width, height;
    public World                world;
    private SpriteBatch         batch;
    private Car                 car;
    public OrthographicCamera   camera;
    private float 				timeStep = 1/30f;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -10f), true);      // creating the box2D  world
        camera = new OrthographicCamera();

        car = new Car(0f, 0f, 3, 1.5f, this);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        float framesPerSecond = Gdx.graphics.getFramesPerSecond();
        System.out.println(framesPerSecond + " fps/ X: "+car.getX() + "Y: "+ car.getY());
        Gdx.graphics.getGL20().glClearColor(0, 0.4f, 0.7f, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.x = car.getX();
        camera.position.y = car.getY();
        if(camera.zoom<Math.abs(car.getSpeed())/15+0.5)camera.zoom+=0.01;
        if(camera.zoom>Math.abs(car.getSpeed())/15+0.5)camera.zoom-=0.003;
        camera.update();

        world.step(timeStep, 8, 3);

        car.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 60/height*width;
        camera.viewportHeight = 60;
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
        batch.dispose();
    }
}
