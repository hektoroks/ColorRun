package com.hekto.gdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.hekto.gdx.screens.GameScreen;

/**
 * Created by hekto on 11/03/2017.
 */

public class ColorSwitch {

    private static final int RADIUSOFSWITCH = 14;
    private GameScreen gameScreen;
    private float width, height;
    private float hx, hy;
    private Body switchBody;
    private SpriteBatch batch;
    public Sprite switchSprite;
    private float bodyAngle;
    public Color color;

    public ColorSwitch(float x, float y, float hx, float hy, GameScreen gameScreen) {
        this.hx = hx;
        this.hy = hy;
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();
        switchSprite = new Sprite(new Texture("colorswitch.png"));

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //  body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        FixtureDef switchFixtureDef =  new FixtureDef();
        // + this line - > add here properties,

        Shape switchShape = new CircleShape();
        switchShape.setRadius(0.00001f);
        switchFixtureDef.shape = switchShape;

        switchBody = gameScreen.world.createBody(bodyDef);
        switchBody.createFixture(switchFixtureDef);

        // Rotation
        switchBody.setAngularVelocity(-0.4f);
        switchBody.setFixedRotation(false);
    }


    /**
     * ColorSwitch draw method
     */
    public void draw() {

        switchSprite.setSize(RADIUSOFSWITCH*2*width/gameScreen.camera.viewportWidth/gameScreen.camera.zoom, RADIUSOFSWITCH*2*width/gameScreen.camera.viewportWidth/gameScreen.camera.zoom);
        switchSprite.setOrigin(switchSprite.getWidth()/2, switchSprite.getHeight()/2);
        switchSprite.setPosition(width/2-switchSprite.getWidth()/2+(gameScreen.camera.position.x-switchBody.getPosition().x)*-width/gameScreen.camera.viewportWidth/gameScreen.camera.zoom, height/2-switchSprite.getHeight()/2+((gameScreen.camera.position.y-switchBody.getPosition().y)*-height/gameScreen.camera.viewportHeight/gameScreen.camera.zoom));
        switchSprite.setRotation(switchBody.getAngle()* MathUtils.radiansToDegrees);

        batch.begin();
        switchSprite.draw(batch);
        batch.end();
    }


    /**
     * @returns the actual color of the switch
     */
    public Color getColor() {
        float angle = switchBody.getAngle();
        color = color.BLUE;
        if(angle >= 0 && angle < 90)
            color = color.YELLOW;
        else
            if(angle >= 90 && angle < 180)
                color = color.VIOLET;
        else
            if (angle >= 180 && angle < 270)
                color = color.PINK;

        return color;
    }

    /**
     * Set the velocity of rotation
     * @param d
     */
    public void setRotationVelocity(float d) {
        switchBody.setAngularVelocity(d);
    }
}
