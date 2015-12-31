package com.mygdx.b2d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.b2d.B2d;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = B2d.WIDTH;
        config.height = B2d.HEIGHT;
		new LwjglApplication(new B2d(), config);
	}
}
