package ru.example.mvvm.viewmodel.viewmodels;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import ru.example.mvvm.model.entities.MovingCircle;
import ru.example.mvvm.view.CircleView;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

/**
 * Контроллер для отображения сущности {@link MovingCircle}
 */
public class CircleViewModel extends SpecifiedViewModel<MovingCircle>
{
    private final CircleView circleView = new CircleView();

    @Override
    public Class<MovingCircle> getTargetEntityClass() {
        return MovingCircle.class;
    }

    @Override
    protected void updateCasted(MovingCircle entity) {
        Vector2 position = entity.getPosition();
        circleView.setPosition(position.x, position.y, entity.getRadius());
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
        circleView.draw(shapeRenderer);
    }
}
