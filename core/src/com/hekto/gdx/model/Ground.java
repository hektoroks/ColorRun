package com.hekto.gdx.model;

import com.hekto.gdx.screens.GameScreen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hekto on 05/03/2017.
 */

/**
 * Create the visual Ground from GroundLine
 */
public class Ground {

    private List<GroundLine> groundLines;
    private List<ColorSwitch> colorSwitches;
    private GameScreen gameScreen;
    private int distance;
    private static final String INPUT = "Ground.txt";
    Random rand;

    public Ground(int distance, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.distance = distance;
        rand = new Random();
        groundLines = new ArrayList<GroundLine>();
        colorSwitches = new ArrayList<ColorSwitch>();

        //createLinearRoad(distance);
        createSinusRoad();
    }

    /**
     * Creates a Linear Road
     */
    private void createLinearRoad(int distance) {
        groundLines.add(new GroundLine(-200, -10, distance, -10, gameScreen));
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

            // reading the ground from file
            float x1, x2, y1, y2;
            int isSwitchOnThisPosition;
            while ((line = in.readLine()) != null) {
                String[] t = line.split(" ");
                x1 = Float.parseFloat((t[0].trim()).toString());
                y1 = Float.valueOf((t[1].trim()).toString());
                x2 = Float.parseFloat((t[2].trim()).toString());
                y2 = Float.valueOf((t[3].trim()).toString());
                groundLines.add(new GroundLine(x1, y1, x2, y2, gameScreen));

                isSwitchOnThisPosition = Integer.parseInt(((t[4].trim()).toString()));
                if (isSwitchOnThisPosition == 1) {
                    colorSwitches.add(new ColorSwitch(x1, y1 - 10, 100, 100, gameScreen));
                    //System.out.println(isSwitchOnThisPosition);
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // draw the ground and and draw the colorswitches
    public void draw(float x) {
        for (GroundLine g : groundLines) {
            g.draw(distance);
        }

        for (ColorSwitch c : colorSwitches) {
            if ((c.getXpos() > x - 100) && (c.getXpos() < x + 100))
                c.draw();
        }
    }
}
