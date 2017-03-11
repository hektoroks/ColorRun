package com.hekto.gdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.hekto.gdx.screens.GameScreen;

/**
 * Created by hekto on 03/03/2017.
 */

public class GroundLine {

    private Body body;          // the body of line
    GameScreen gameScreen;
    float x1,y1, x2,y2;         // line coordinates
    float w, h;                 // width and height of screen

    Texture snowTexture;
    Texture groundTexture;
    PolygonSprite polygonSprite;
    PolygonSpriteBatch batch;
    float[] snowVertices;
    short[] triangles;

    public GroundLine(float x1, float y1, float x2, float y2, GameScreen gameScreen) {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.gameScreen = gameScreen;

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;


        EdgeShape shape = new EdgeShape();
        shape.set(x1, y1, x2, y2);
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;               // egy vonal
        fixDef.density = 10;                // suruseg
        fixDef.restitution = 0.1f;          // rugalmassag
        fixDef.friction = 10f;              // surlodas
        body = gameScreen.world.createBody(bodyDef);
        body.createFixture(fixDef);


        /// Tovabb;
        snowTexture = new Texture("snow.png");
        groundTexture = new Texture("snow.png");

        snowVertices = new float[]{100,0,100,100,0,100,0,0};
        triangles = new short[]
                {
                        0,1,3,0,1,2
                };
        polygonSprite = new PolygonSprite(new PolygonRegion(new TextureRegion(snowTexture,128,128), snowVertices, triangles));
        batch = new PolygonSpriteBatch();
    }



    /**
     * @param distance  - length of Ground
     */
    public void draw(float distance) {
        boolean inPole = true;

        if((x2-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2<0
                || (x1-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2 > w)
            inPole = false;


        else
        {

            float[] snowVertices = new float[]
                    {
                            (x1-0.01f-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, (y1-gameScreen.camera.position.y)*h/gameScreen.camera.viewportHeight/gameScreen.camera.zoom-3*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+h/2,
                            (x2-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, (y2-gameScreen.camera.position.y)*h/gameScreen.camera.viewportHeight/gameScreen.camera.zoom+h/2,
                            (x2-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, (y2-gameScreen.camera.position.y)*h/gameScreen.camera.viewportHeight/gameScreen.camera.zoom-3*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+h/2,
                            (x1-0.01f-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, (y1-gameScreen.camera.position.y)*h/gameScreen.camera.viewportHeight/gameScreen.camera.zoom+h/2
                    };
            triangles = new short[]
                    {
                            0,1,3,0,1,2
                    };
            PolygonRegion region = new PolygonRegion
                    (
                            new TextureRegion(snowTexture),
                            snowVertices,
                            triangles
                    );
            polygonSprite.setRegion(region);

        }

        if(inPole)
        {
            batch.begin();
            polygonSprite.draw(batch);
            batch.end();
            float[] groundVertices = new float[]
                    {

                            (x1-0.01f-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, 0,
                            (x2-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, -Math.abs(y1-y2)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom,
                            (x2-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, (y2-gameScreen.camera.position.y)*h/gameScreen.camera.viewportHeight/gameScreen.camera.zoom-3*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+h/2,
                            (x1-0.01f-gameScreen.camera.position.x)*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+w/2, (y1-gameScreen.camera.position.y)*h/gameScreen.camera.viewportHeight/gameScreen.camera.zoom-3*w/gameScreen.camera.viewportWidth/gameScreen.camera.zoom+h/2
                    };
            triangles = new short[]
                    {
                            0,1,3,3,1,2
                    };
            PolygonRegion region = new PolygonRegion
                    (
                            new TextureRegion(groundTexture,128,128),
                            groundVertices,
                            triangles
                    );
            polygonSprite.setRegion(region);
            batch.begin();
            polygonSprite.draw(batch);
            batch.end();
        }
    }
}
