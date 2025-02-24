package ru.example.mvvm.view;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Класс для управления загруженными в память текстурами. В идеале доступ ко всем текстурам должен осуществляться
 * именно через этот класс
 */
public class TextureStorage implements Disposable {
    private final Map<String, Texture> textures = new HashMap<>();

    public void loadInternalTexture(String textureName, String internalTexturePath) {
        //Загружаем текстуру по пути внутри JAR-файла игры и сопоставляем ей имя внутри приложения
        textures.put(textureName, new Texture(internalTexturePath));
    }

    public Texture getTexture(String textureName) {
        return textures.get(textureName);
    }

    @Override
    public void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        textures.clear();
    }
}
