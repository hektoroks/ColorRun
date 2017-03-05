package com.hekto.gdx.model;

import com.hekto.gdx.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hekto on 05/03/2017.
 */

/**
 * Create the visual Ground from GroundLine
 */
public class Ground {

    private List<GroundLine> groundLines;
    private GameScreen gameScreen;
    private int distance;

    public Ground( int distance, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.distance = distance;
        groundLines = new ArrayList<GroundLine>();

        createLinearRoad(distance);
    }

    /**
     * Creates a Linear Road
     */
    private void createLinearRoad(int distance) {
        groundLines.add(new GroundLine(-200,-10, distance,-10,gameScreen));
    }


    /**
     * Creates Parabolic Road
     */
    private void createHill(int distance) {

    }

    public void draw() {
        for (GroundLine g :groundLines) {
            g.draw(distance);
        }
    }
}
