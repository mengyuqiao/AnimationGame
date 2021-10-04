package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class AnimationGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	int x, y;
	int dx, dy;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Man.png");
		x = 0;
		y = 0;
		dx = 0;
		dy = 0;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		x += dx;
		y += dy;
		if (y > 0) {
			dy -= 3;
		}
		if (y <= 0){
			y = 0;
			dy = 0;
		}
		if (dx > 0){
			dx -= 1;
		}
		if (dx < 0 ){
			dx += 1;
		}

		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		batch.draw(img, x, y, 100, 120);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screenX > x){
			dx += 20;
		} else {
			dx -= 20;
		}
		dy += 30;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
