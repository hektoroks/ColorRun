package com.hekto.gdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.hekto.gdx.screens.GameScreen;

/**
 * Created by hekto on 26/02/2017.
 * InputAdapter for keyDown method(override what you are interested)
 */

public class Car extends InputAdapter {

    private final static float RADIUSOFWHEEL = 2;
    private final float motorSpeed = 12;//6;
    private boolean touch = false;
    float w,h;
    private Body chassis, leftWheel, rightWheel;        // a lathatatlan elemek lenyegeben az auto kepe mogott
    private WheelJoint leftAxis, rightAxis;             // joint the 3 bodies together
    GameScreen gameScreen;
    SpriteBatch batch;
    public Sprite chassisSprite;
    public Sprite leftWheelSprite;
    public Sprite rightWheelSprite;
    Texture yellow, violet, pink, blue;
    int colorCounter = 0; //which color comes

    public Car(float x, float y, float width, float height, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        // The different textures for car chassis
        yellow = new Texture("truck_yellow.png");
        violet = new Texture("truck_violet.png");
        pink = new Texture("truck_pink.png");
        blue = new Texture("truck_blue.png");

        chassisSprite = new Sprite(new Texture("truck.png"));
        leftWheelSprite = new Sprite(new Texture("wheel.png"));
        rightWheelSprite = new Sprite(new Texture("wheel.png"));

        // A fixture a fizikai, lathato tulajdonsagokat irjak le
        FixtureDef chassisFixtureDef = new FixtureDef();
        FixtureDef wheelFixtureDef = new FixtureDef();

        // Set properties
        chassisFixtureDef.density = 400;             // suruseg
        chassisFixtureDef.friction = 1f;            // surlodas 0-1
        chassisFixtureDef.restitution = .8f;        // rugalmassag 0-1

        wheelFixtureDef.density = chassisFixtureDef.density * 2f;
        wheelFixtureDef.friction = 10;
        wheelFixtureDef.restitution = 0.8f;


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
        mass.mass = 1000;
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


        // Joint - Connect bodies together / Left Axis Connecttion to chassis
        WheelJointDef axisDef = new WheelJointDef();
        axisDef.bodyA = chassis;
        axisDef.bodyB = leftWheel;
        axisDef.frequencyHz = 1.6f;
        axisDef.localAnchorA.set(-8 * .6f, -3f);      // body/chassis connection point
        axisDef.localAxisA.set(0, 1f);
        axisDef.maxMotorTorque = 50 / 9 * wheelShape.getRadius() * wheelShape.getRadius() * 5500f;

        leftAxis = (WheelJoint) gameScreen.world.createJoint(axisDef);


        // Right Axix Connection to Chassis
        axisDef.bodyB = rightWheel;
        axisDef.localAnchorA.x *= -1;
        axisDef.maxMotorTorque = 50 / 9 * wheelShape.getRadius() * wheelShape.getRadius() * 5500f;

        rightAxis = (WheelJoint) gameScreen.world.createJoint(axisDef);

        leftAxis.setSpringDampingRatio(0.7f);
        rightAxis.setSpringDampingRatio(0.7f);

        leftWheel.setBullet(true);
        rightWheel.setBullet(true);


        // Folytonos menes
        leftAxis.enableMotor(true);
        rightAxis.enableMotor(true);
        leftAxis.setMotorSpeed(-motorSpeed);
        rightAxis.setMotorSpeed(-motorSpeed);
    }


    /**
     * Game flow, Continous going, redraw
     */
    public void update() {

        if (touch) {
            if (colorCounter % 4 == 1) {
                chassisSprite.setRegion(blue);
            } else if (colorCounter % 4 == 2) {
                chassisSprite.setRegion(pink);
            } else if (colorCounter % 4 == 3) {
                chassisSprite.setRegion(violet);
            } else {
                chassisSprite.setRegion(yellow);
            }
        }


        chassisSprite.setSize(16 * w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom, 4 * h / gameScreen.camera.viewportHeight / gameScreen.camera.zoom);
        chassisSprite.setOrigin(chassisSprite.getWidth() / 2, chassisSprite.getHeight() / 2);
        /// chassisSprite.setPosition(w/2-chassisSprite.getWidth()/2, h/2-chassisSprite.getHeight()/2);
        chassisSprite.setPosition(w / 2 - chassisSprite.getWidth() / 2 + (gameScreen.camera.position.x - chassis.getPosition().x) * -w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom, h / 2 - chassisSprite.getHeight() / 2 + (gameScreen.camera.position.y - chassis.getPosition().y) * -h / gameScreen.camera.viewportHeight / gameScreen.camera.zoom);

        chassisSprite.setRotation(chassis.getAngle() * MathUtils.radiansToDegrees);

        leftWheelSprite.setSize(RADIUSOFWHEEL * 2 * w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom, RADIUSOFWHEEL * 2 * w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom);
        leftWheelSprite.setOrigin(leftWheelSprite.getWidth() / 2, leftWheelSprite.getHeight() / 2);
        leftWheelSprite.setPosition(w / 2 - leftWheelSprite.getWidth() / 2 + (gameScreen.camera.position.x - leftWheel.getPosition().x) * -w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom, h / 2 - leftWheelSprite.getHeight() / 2 + (gameScreen.camera.position.y - leftWheel.getPosition().y) * -h / gameScreen.camera.viewportHeight / gameScreen.camera.zoom);
        leftWheelSprite.setRotation(leftWheel.getAngle() * MathUtils.radiansToDegrees);

        rightWheelSprite.setSize(RADIUSOFWHEEL * 2 * w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom, RADIUSOFWHEEL * 2 * w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom);
        rightWheelSprite.setOrigin(rightWheelSprite.getWidth() / 2, rightWheelSprite.getHeight() / 2);
        rightWheelSprite.setPosition(w / 2 - rightWheelSprite.getWidth() / 2 + (gameScreen.camera.position.x - rightWheel.getPosition().x) * -w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom, h / 2 - rightWheelSprite.getHeight() / 2 + ((gameScreen.camera.position.y - rightWheel.getPosition().y) * -h / gameScreen.camera.viewportHeight / gameScreen.camera.zoom));
        rightWheelSprite.setRotation(rightWheel.getAngle() * MathUtils.radiansToDegrees);


        // Batch drawing
        batch.begin();
        chassisSprite.draw(batch);
        leftWheelSprite.draw(batch);
        rightWheelSprite.draw(batch);
        batch.end();

        System.out.println(getX());
    }

    /**
     * @param x
     * @param y
     * @param arg2
     * @param button
     * @return a boolean, if it was clicked or not
     */
    @Override
    public boolean touchDown(int x, int y, int arg2, int button) {
        if (button == Input.Buttons.LEFT) {
            touch = true;
            colorCounter++;
        }

        return false;
    }

    @Override
    public boolean touchUp(int arg0, int arg1, int arg2, int button) {
        touch = false;
        return false;
    }

    /**
     * Getters & Setters
     */

    public Body getChassis() {
        return chassis;
    }

    public Body getLeftWheel() {
        return leftWheel;
    }

    public Body getRightWheel() {
        return rightWheel;
    }

    public float getX() {
        return chassis.getPosition().x;
    }

    public float getY() {
        return chassis.getPosition().y;
    }

    public float getSpeed() {
        return chassis.getLinearVelocity().x;
    }

    public Sprite getChassisSprite() {
        return chassisSprite;
    }


    /* Meg */
    public float getChassisWidth() {
        return 2 * w / gameScreen.camera.viewportWidth / gameScreen.camera.zoom;
    }
}
