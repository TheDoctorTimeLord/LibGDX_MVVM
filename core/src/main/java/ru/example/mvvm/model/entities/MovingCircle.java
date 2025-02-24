package ru.example.mvvm.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import ru.example.mvvm.model.ModelContext;

/**
 * Сущность перемещаемого круга. Круги перемещаются в случайном направлении, пока не достигнут границы окна, после
 * чего отскакивают в случайном направлении
 */
public class MovingCircle extends BaseEntity {
    private final Vector2 position;
    private final Vector2 speed;
    private final float radius;

    public MovingCircle(int id, Vector2 position, Vector2 speed, float radius) {
        super(id);
        this.position = position;
        this.speed = speed;
        this.radius = radius;
    }

    @Override
    public void update(ModelContext context) {
        // Посчитаем новую позицию круга с учётом скорости перемещения. Скорость умножается на время, прошедшее с
        // предыдущего обновления сущности, чтобы круги перемещались с одинаковой скоростью на быстрых и на медленных
        // компьютерах
        Vector2 newPosition = position.add(speed.cpy().scl(Gdx.graphics.getDeltaTime()));

        // Ограничиваем координаты сущности пределами окна, чтобы она не выскакивала за экран
        float clampedX = MathUtils.clamp(newPosition.x, radius, context.getGameZoneWidth() - radius);
        float clampedY = MathUtils.clamp(newPosition.y, radius, context.getGameZoneHeight() - radius);

        // Если сущность достигла границы окна, то повернём её в случайном направлении
        if (!MathUtils.isEqual(newPosition.x, clampedX) || !MathUtils.isEqual(newPosition.y, clampedY)) {
            speed.rotateRad(MathUtils.random(0, MathUtils.PI2));
            position.set(clampedX, clampedY);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }
}
