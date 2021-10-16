package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Monster {
    private int x, y, dx, dy, death_count;
    private String img, death_img;
    private boolean show, dead;

    public Monster(int dx, int dy, String img, String death_img, boolean show) {
        this.x = Gdx.graphics.getWidth();
        this.y = 0;
        this.dx = dx;
        this.dy = dy;
        this.death_count = 5;
        this.img = img;
        this.death_img = death_img;
        this.show = show;
        this.dead = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public String getImg() {
        return img;
    }

    public String getDeath_img() {
        return death_img;
    }

    public void setDeath_img(String death_img) {
        this.death_img = death_img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow() {
        if (this.show){
            x = Gdx.graphics.getWidth();
            y = 0;
        }
        this.show = !this.show;
    }

    public int getDeath_count() {
        return death_count;
    }

    public void setDeath_count(int death_count) {
        this.death_count = death_count;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void move(){
        if (x < 0){
            x = Gdx.graphics.getWidth();
        }
        x += dx;
        y += dy;
    }

    public boolean checkDeath(){
        if (dead){
            if (death_count > 0){
                death_count--;
                return true;
            }else{
                death_count = 5;
                x = Gdx.graphics.getWidth();
                dead = false;
                setShow();
                return false;
            }
        }
        return false;
    }
}
