package com.mygdx.b2d;

import com.badlogic.gdx.Game;

public class B2d extends Game
{
    public static final float PPM = 100;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    @Override
    public void create() {
        setScreen(new B2dScreen());
    }

    @Override
	public void render () {
        super.render();
	}
}
