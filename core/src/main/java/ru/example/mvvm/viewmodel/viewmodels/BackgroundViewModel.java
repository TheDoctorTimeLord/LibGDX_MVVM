package ru.example.mvvm.viewmodel.viewmodels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.example.mvvm.model.entities.Background;
import ru.example.mvvm.view.TextureStorage;
import ru.example.mvvm.view.TextureView;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

/**
 * Контроллер для отображения сущности {@link Background}
 */
public class BackgroundViewModel extends SpecifiedViewModel<Background>
{
    private final TextureStorage textureStorage;
    private String textureName;
    private TextureView textureView;

    public BackgroundViewModel(TextureStorage textureStorage)
    {
        this.textureStorage = textureStorage;
    }

    @Override
    public Class<Background> getTargetEntityClass() {
        return Background.class;
    }

    @Override
    protected void updateCasted(Background entity) {
        String currentTextureName = entity.getTextureName();
        // Обновляем текстуру, если она изменилась
        if (textureName == null || !textureName.equals(currentTextureName)) {
            textureName = currentTextureName;
            textureView = new TextureView(textureStorage.getTexture(textureName));
        }
    }

    @Override
    public void drawSprite(SpriteBatch batch)
    {
        // Передаём управление в отрисовщик текстур
        textureView.draw(batch);
    }
}
