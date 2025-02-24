package ru.example.mvvm.model;

/**
 * Класс, через который отдельно взятая {@link Entity} может узнать о всём игровом состоянии и как-то на него
 * повлиять. Любые способы изменения модели должны представляться отдельными методами данного класса
 */
public class ModelContext {
    private final float gameZoneWidth;
    private final float gameZoneHeight;

    public ModelContext(float gameZoneWidth, float gameZoneHeight) {
        this.gameZoneWidth = gameZoneWidth;
        this.gameZoneHeight = gameZoneHeight;
    }

    /**
     * Получить ширину зоны, в которой могут перемещаться сущности. На данный момент ширина зоны совпадает с
     * количеством пикселей в ширине окна. В идеале нужно вводить отдельное координатное пространство модели и
     * отображать его в пространство окна
     */
    public float getGameZoneWidth() {
        return gameZoneWidth;
    }

    /**
     * Получить высоту зоны, в которой могут перемещаться сущности. На данный момент высота зоны совпадает с
     * количеством пикселей в высоте окне. В идеале нужно вводить отдельное координатное пространство модели и
     * отображать его в пространство окна
     */
    public float getGameZoneHeight() {
        return gameZoneHeight;
    }
}
