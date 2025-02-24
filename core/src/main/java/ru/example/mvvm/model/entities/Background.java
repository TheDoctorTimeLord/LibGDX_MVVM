package ru.example.mvvm.model.entities;

import ru.example.mvvm.model.ModelContext;

/**
 * Сущность для отображения заднего фона окна
 */
public class Background extends BaseEntity {
    private final String textureName;

    public Background(int id, String textureName) {
        super(id);
        this.textureName = textureName;
    }

    @Override
    public void update(ModelContext context) { }

    public String getTextureName() {
        return textureName;
    }
}
