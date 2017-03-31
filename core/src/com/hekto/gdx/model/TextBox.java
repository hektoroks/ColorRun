package com.hekto.gdx.model;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by hekto on 30/03/2017.
 * TextBoxes for the Screens(theoretically Labels) -BitmapFont: Renders bitmap fonts.
 */

public class TextBox extends BitmapFont {

    private SpriteBatch spriteBatch;
    private String 		text;
    private float 		x,y;

    public TextBox(float x,float y)
    {
        text = "";
        setColor(com.badlogic.gdx.graphics.Color.WHITE);
        this.x=x;
        this.y=y;
        spriteBatch = new SpriteBatch();
    }
    public void setPosition(float x, float y)
    {
        this.x=x;
        this.y=y;

    }
    public void draw(float stateTime)
    {
        spriteBatch.begin();
            draw(spriteBatch, text, x, y);
        spriteBatch.end();
    }

    public String getStr()
    {
        return text;
    }
    public void setStr(String text)
    {
        this.text=text;
    }
    public void clear()
    {
        text = "";
    }
    public void add(char ch)
    {
        if(text.length()<4)text += ch;
    }
}
