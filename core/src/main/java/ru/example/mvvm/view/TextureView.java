package ru.example.mvvm.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Реализация объекта для отрисовки произвольных спрайтов
 */
public class TextureView implements DrawableView {
    private final Texture texture;

    public TextureView(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, 140, 210);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) { }
}
