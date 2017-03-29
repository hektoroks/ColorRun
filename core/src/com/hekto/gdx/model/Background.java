package com.hekto.gdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hekto on 11/03/2017.
 */

public class Background {

    private static final String PATH = "background2.png";
    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private PolygonSpriteBatch batch;
    private int speed = 2;
    private int width, height;      // screen width and height
    private int screenIteration = 0;// hanyszor haladjuk meg az widthet lenyegeben


    public Background() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        textureRegion = new TextureRegion(new Texture(PATH)); /*(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH))*/
        textureRegionBounds1 = new Rectangle(0 - width / 2 + 1100, 0, width, height);        // ground starts at 1100 x coordinate
        textureRegionBounds2 = new Rectangle(width / 2 + 1100, 0, width, height);

        batch = new PolygonSpriteBatch();
    }

    /**
     * @param delta =  car.getX
     */
    public void act(float delta) {
        if (((delta * speed) / width) > (screenIteration + 1)) {
            resetBounds(delta);
        } else {
            updateXBounds(-delta);
        }
    }

    /**
     * Drawing background with 2 regions
     */
    public void draw() {
        batch.begin();
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, width, height);
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, width, height);
        batch.end();
    }

    /**
     * Folytonosan leptetni az autohoz viszonyitva a poziciot
     * @param delta
     */
    private void updateXBounds(float delta) {
        textureRegionBounds1.x = (delta * speed) + (screenIteration * width);
        textureRegionBounds2.x = (delta * speed) + (screenIteration * width) + width;
 //       System.out.println(textureRegionBounds1.x);
    }

    /**
     * Kierunk a kepernyobol, akkor be kell rakni az uj regiot
     * @param delta
     */
    private void resetBounds(float delta) {
 //       System.out.println("reset");
        screenIteration += 1;
 //       System.out.println(textureRegionBounds2);
 //       System.out.println(screenIteration);
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(width + 1100, 0, width, height);
    }
}