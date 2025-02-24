package ru.example.mvvm.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Интерфейс для всех объектов, занимающихся отображением данных. Производит отрисовку в два этапа:
 * <ol>
 *     <li>Рисуются все спрайты</li>
 *     <li>Рисуются все геометрические фигуры</li>
 * </ol>
 * Логику отрисовки можно реализовать иначе, например в один цикл, но тогда нужно правильно открывать и закрывать
 * пакеты отправляемых в OpenGL фигур
 */
public interface DrawableView {
    void draw(SpriteBatch batch);
    void draw(ShapeRenderer shapeRenderer);
}
