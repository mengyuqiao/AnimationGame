package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;

public class AnimationGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img, monster;
	int mx, mdx;
	int y, dy;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Man.png");
		monster = new Texture("Monster.png");
		y = 0;
		dy = 0;
		mx = Gdx.graphics.getWidth();
		mdx = -3;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		// move all items with time
		moveByTime();

		// check the monster is killed, render a new one
		if (checkKill()){
			mx = Gdx.graphics.getWidth();
		}

		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		batch.draw(img, 50, y, 100, 120);
		batch.draw(monster, mx, 0, 120, 100);
		batch.end();
	}

	private boolean checkKill() {
		if (y <= 100 && (150 >= mx && 50 < mx + 120)){
			return true;
		}
		return false;
	}

	private void moveByTime() {
		// check if user is jumping or in the air
		y += dy;
		if (y > 0) {
			dy -= 2;
		}
		if (y <= 0){
			y = 0;
			dy = 0;
		}

		// monster moves
		mx += mdx;
		if (mx < 0 || mx > Gdx.graphics.getWidth()){
			mdx = -mdx;
		}
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
