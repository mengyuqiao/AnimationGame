package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;

public class AnimationGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture human, human_attack1, human_attack2, pig_alive, pig_dead, arrow, background;
	Arrow arr;
	Pig pig;
	BitmapFont font;
	int y, dy;
	int human_status;
	int attacking;
	int bgx = 0;
	int retry, point;
	boolean game_over;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		human = new Texture("Man.png");
		human_attack1 = new Texture("Attack1.png");
		human_attack2 = new Texture("Attack2.png");
		background = new Texture("Background.png");
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		pig = new Pig();
		pig_alive = new Texture(pig.getImg());
		pig_dead = new Texture(pig.getDeath_img());
		arr = new Arrow();
		arrow = new Texture(arr.getImg());
		y = 0;
		dy = 0;
		human_status = 0;
		attacking = 0;
		retry = 5;
		point = 0;
		game_over = false;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		// move all items with time
		moveByTime();

		// check human attack
		checkAttack();

		// check the monster is killed, render a new one
		checkKill();

		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		// if the game is still going on
		if (!game_over){
			// draw background image
			bgx = (bgx + 1)%(background.getWidth());
			batch.draw(background, 0, 0, bgx, 0,1518, 500);
			// draw human
			switch (human_status){
				case 1:
					batch.draw(human_attack1, 50, y, 100, 120);
					break;
				case 2:
					batch.draw(human_attack2, 50, y, 100, 120);
					break;
				default:
					batch.draw(human, 50, y, 100, 120);
			}

			// draw arrow
			if (arr.isShow()){
				batch.draw(arrow, arr.getX(),arr.getY(),75,25);
			}

			// draw monsters
			if (pig.checkDeath()){
				batch.draw(pig_dead, pig.getX(), 0, 120, 100);
			}else{
				batch.draw(pig_alive, pig.getX(), 0, 120, 100);
			}

			// draw retry time
			font.draw(batch, "Retry: "+ retry, 5, Gdx.graphics.getHeight());
		}
		// if the game is over
		else{
			font.draw(batch, "Game Over", (int)(Gdx.graphics.getWidth()/2)-30, (int)(Gdx.graphics.getHeight()/2));
		}
		// draw point forever
		font.draw(batch, "Point: "+ point, Gdx.graphics.getWidth()-60, Gdx.graphics.getHeight());

		batch.end();
	}

	private void checkAttack() {
		// attack posture one
		if (attacking > 15 && attacking <= 30){
			human_status = 1;
			attacking--;
		}
		// attack posture two
		else if (attacking > 0 && attacking <= 15){
			arr.setShow();
			human_status = 2;
			attacking--;
		}else { // end attack
			human_status = 0;
		}
	}

	// check if the monster or the human is killed
	private void checkKill() {
		// check pig's kill
		if (pig.getX() <= arr.getX()+50){
			if (arr.isShow()){
				point++;
				arr.setShow();
				pig.setDead(true);
			}
		}

		// check human's kill
		if (pig.getX() <= 150 && pig.getX() >= 50){
			pig.setX(Gdx.graphics.getWidth());
			retry--;
			if (retry == 0){
				game_over = true;
			}
		}
	}

	// move all the elements by every frame
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
		pig.move();

		// arrow move
		if (arr.isShow()){
			arr.move();
			if (arr.getX()>=Gdx.graphics.getWidth()){
				arr.setShow();
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		human.dispose();
		human_attack1.dispose();
		human_attack2.dispose();
		pig_alive.dispose();
		pig_dead.dispose();
		arrow.dispose();
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
		// if game is over, restart the game
		if (game_over){
			game_over = false;
			retry = 5;
			point = 0;
		}else{ // if the game is still going on, attack the monster
			attacking = 30;
		}
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
