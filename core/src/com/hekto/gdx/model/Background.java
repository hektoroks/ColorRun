package com.hekto.gdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hekto.gdx.utils.Constants;

/**
 * Created by hekto on 11/03/2017.
 */

public class Background extends Actor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 100;
    private int w, h;

    public Background() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        textureRegion = new TextureRegion(new Texture("background2.png")); /*(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH))*/
        textureRegionBounds1 = new Rectangle(0 - w / 2, 0, w, h);
        textureRegionBounds2 = new Rectangle(w / 2, 0, w, h);
    }

    @Override
    public void act(float delta) {
        System.out.println("act");
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.begin();
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, w, h);
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, w, h);
        //batch.end();
    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - (delta * speed)) <= 0;
    }

    private void updateXBounds(float delta) {
        textureRegionBounds1.x += delta * speed;
        textureRegionBounds2.x += delta * speed;
    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(w, 0, w, h);
    }

}