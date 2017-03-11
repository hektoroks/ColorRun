package com.hekto.gdx.model;

import com.hekto.gdx.screens.GameScreen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private static final String INPUT = "Ground.txt";

    public Ground( int distance, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.distance = distance;
        groundLines = new ArrayList<GroundLine>();

        //createLinearRoad(distance);
        createSinusRoad();
    }

    /**
     * Creates a Linear Road
     */
    private void createLinearRoad(int distance) {
        groundLines.add(new GroundLine(-200,-10, distance,-10,gameScreen));
    }


    /**
     * Creates Parabolic Road, Reads from file, x,y coordinates
     */
    private void createSinusRoad() {


        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(INPUT));
            //distance = Integer.parseInt(in.readLine());
            while((line = in.readLine()) != null)
            {
                String[] t = line.split(" ");
                float x1 = Float.parseFloat((t[0].trim()).toString());
                float y1 = Float.valueOf((t[1].trim()).toString());
                float x2 = Float.parseFloat((t[2].trim()).toString());
                float y2 = Float.valueOf((t[3].trim()).toString());
                groundLines.add(new GroundLine(x1, y1, x2, y2, gameScreen));
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        for (GroundLine g :groundLines) {
            g.draw(distance);
        }
    }
}
