package com.hekto.gdx.model;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.hekto.gdx.screens.GameScreen;

/**
 * Created by hekto on 26/02/2017.
 */

public class Car {

    final static float RADIUSOFWHEEL = 3;

    private Body chassis, leftWheel, rightWheel;        // a lathatatlan elemek lenyegeben az auto kepe mogott
    SpriteBatch batch;
    public Sprite chassisSprite;
    Sprite leftWheelSprite, rightWheelSprite;
    GameScreen gameScreen;

    public Car(float x, float y, float width, float height, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();

        chassisSprite = new Sprite(new Texture("car.png"));
        leftWheelSprite = new Sprite(new Texture("wheel.png"));
        rightWheelSprite = new Sprite(new Texture("wheel.png"));

        // A fixture a fizikai, lathato tulajdonsagokat irjak le
        FixtureDef chassisFixtureDef = new FixtureDef();
        FixtureDef wheelFixtureDef = new FixtureDef();

         // Set properties
        chassisFixtureDef.density = 45;             // suruseg
		chassisFixtureDef.friction = 1f;            // surlodas 0-1
		chassisFixtureDef.restitution = .2f;        // rugalmassag 0-1

        wheelFixtureDef.density = chassisFixtureDef.density * 2f;
		wheelFixtureDef.friction = 10;
		wheelFixtureDef.restitution = 0.3f;


        // ehhez adjuk hozza majd a Body elemeket. A body dinamikus
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);


        // Body Shape
        PolygonShape chassisShape = new PolygonShape();
        chassisShape.set(new Vector2[]{
                new Vector2(-8f, -2f),
                new Vector2(8f, -2f),
                new Vector2(8f, 1f),
                new Vector2(-7f, 1f)
        });

        chassisFixtureDef.shape = chassisShape;
        chassis = gameScreen.world.createBody(bodyDef);     // body - mapping to body
        chassis.createFixture(chassisFixtureDef);           // mapping to Fixture

        MassData mass = chassis.getMassData();              // set how heavy the chassis is
        mass.mass=1000;
        chassis.setMassData(mass);


        // Left wheel - Shapek vannak a hatterben
        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(RADIUSOFWHEEL);

        wheelFixtureDef.shape = wheelShape;
        leftWheel = gameScreen.world.createBody(bodyDef);     // in the same way
        leftWheel.createFixture(wheelFixtureDef);

        // Right wheel
        rightWheel = gameScreen.world.createBody(bodyDef);
        rightWheel.createFixture(wheelFixtureDef);
    }


    /**
     * Game flow, redraw
     */
    public void update() {
        batch.begin();
        chassisSprite.draw(batch);
        leftWheelSprite.draw(batch);
        rightWheelSprite.draw(batch);
        batch.end();
    }

    
}
