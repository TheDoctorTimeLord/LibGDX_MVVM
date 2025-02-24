package ru.example.mvvm.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Реализация объекта для отрисовки кругов
 */
public class CircleView implements DrawableView {
    private float centerX;
    private float centerY;
    private float radius;

    public void setPosition(float x, float y, float radius) {
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
    }

    @Override
    public void draw(SpriteBatch batch) { }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(centerX, centerY, radius);
    }
}
