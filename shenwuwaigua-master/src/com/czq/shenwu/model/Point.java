package com.czq.shenwu.model;

public class Point {

    public int x = -1;
    public int y = -1;

    public Point() {

    }
    public Point (int x,int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point,int dx,int dy) {
        this.x = point.x + dx;
        this.y = point.y + dy;
    }
    public boolean isVaild() {
        if (x < 0 || y < 0)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
