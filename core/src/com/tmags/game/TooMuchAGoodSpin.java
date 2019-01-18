package com.tmags.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tmags.game.Screens.PlayScreen;

public class TooMuchAGoodSpin extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
