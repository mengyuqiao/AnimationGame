package com.mygdx.game;

public class FlyingItem {
    private int x, y, dx, dy;
    private String img;
    private boolean show;

    public FlyingItem(int dx, int dy, String img, boolean show) {
        this.x = 100;
        this.y = 40;
        this.dx = dx;
        this.dy = dy;
        this.img = img;
        this.show = show;
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

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow() {
        if (this.show){
            x = 100;
            y = 40;
        }
        this.show = !this.show;
    }

    public void move(){
        x += dx;
        y += dy;
    }
}
