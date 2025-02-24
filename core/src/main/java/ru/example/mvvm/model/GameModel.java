package ru.example.mvvm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Основной класс модели игры в концепции MVVM. Является главным контроллером всей игровой симуляции. Должна
 * проектироваться таким образом, чтобы не зависеть ни от одной другой абстракции
 * ({@link ru.example.mvvm.view.DrawableView}, {@link ru.example.mvvm.viewmodel.ViewModel}). Если есть необходимость
 * передавать внешние сигналы в модель (например, нажатие мышкой) стоит добавлять отдельные публичные методы, которые
 * будут запоминать данные сигнала в состояние данного класса. Все изменения игровой симуляции должны производиться в
 * методе {@link #update()}
 */
public class GameModel {
    /** Игровые сущности, сгруппированные по типу */
    private final Map<Class<? extends Entity>, List<Entity>> entitiesByClass = new HashMap<>();

    /** Контекст через который {@link Entity} может получать информацию о всём состоянии модели */
    private ModelContext modelContext;

    /**
     * Инициализация общих начальных данных модели
     */
    public void start() {
        modelContext = new ModelContext(640, 480);
    }

    /**
     * Метод обновления игрового состояния. Производит обновление всех имеющихся игровых сущностей
     */
    public void update() {
        for (List<Entity> typedEntities : entitiesByClass.values()) {
            for (Entity entity : typedEntities) {
                entity.update(modelContext);
            }
        }
    }

    /**
     * Добавляет новую игровую сущность в текущее состояние модели
     */
    public void addEntity(Entity entity) {
        entitiesByClass.computeIfAbsent(entity.getClass(), k -> new ArrayList<>()).add(entity);
    }

    /**
     * Возвращает список всех активных игровых сущностей
     */
    public List<Entity> getEntities() {
        return entitiesByClass.values().stream()
            .flatMap(Collection::stream)
            .toList();
    }
}
