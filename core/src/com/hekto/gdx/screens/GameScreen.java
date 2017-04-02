package com.hekto.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.hekto.gdx.MyGdxGame;
import com.hekto.gdx.controller.GameController;
import com.hekto.gdx.model.Background;
import com.hekto.gdx.model.Car;
import com.hekto.gdx.model.Ground;
import com.hekto.gdx.model.TextBox;

/**
 * Created by hekto on 26/02/2017.
 */

public class GameScreen implements Screen {

    public MyGdxGame myGdxGame;
    private SpriteBatch batch;
    private float width, height;
    public World world;
    private Car car;
    private Ground ground;
    public OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    public boolean debugRend = false;
    private float timeStep = 1 / 30f;
    private Background background;

    private TextBox scoreBox;
    private int score;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -10f), true);      // creating the box2D  world
        debugRenderer = new Box2DDebugRenderer();           // adding renderer to the world
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        background = new Background();
        camera.zoom += 0.7;
        ground = new Ground(2000, this);
        car = new Car(110f, -50, 3, 1.5f, this);


        Gdx.input.setInputProcessor(new InputMultiplexer(new GameController(this), car)); // setting control

        // setting up score
        score = 0;
        scoreBox = new TextBox(Gdx.graphics.getWidth() - 30 , Gdx.graphics.getHeight() - 30);
        scoreBox.setColor(1, 1, 1, 0.3f);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        float framesPerSecond = Gdx.graphics.getFramesPerSecond();
        //System.out.println(framesPerSecond + " fps/ X: "+car.getX() + "Y: "+ car.getY());
        Gdx.graphics.getGL20().glClearColor(0, 0.4f, 0.7f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.position.x = car.getX() + 50;
        camera.position.y = car.getY() + 25;
        //if(camera.zoom<Math.abs(car.getSpeed())/15+0.5) camera.zoom+=0.01;
        //if(camera.zoom>Math.abs(car.getSpeed())/15+0.5) camera.zoom-=0.003;
        camera.update();

        world.step(timeStep, 8, 3);


        /* Background call */
        background.act(car.getX());
        background.draw();

        /* Setting score up */
        scoreBox.setStr(score + "");
        scoreBox.draw(delta);

        /* Render  and draw car and ground (press ctrl to see) */
        if (debugRend) {
            debugRenderer.render(world, camera.combined);
        } else {
            ground.draw(car);
            car.update();
            ground.checkCollision(car);
        }



    }

    @Override
    public void resize(int arg1, int arg2) {
        camera.viewportWidth = 60 / height * width;
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
