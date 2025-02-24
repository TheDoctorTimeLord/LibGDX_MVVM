package ru.example.mvvm;

import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.example.mvvm.model.GameModel;
import ru.example.mvvm.model.entities.Background;
import ru.example.mvvm.model.entities.MovingCircle;
import ru.example.mvvm.utils.Vector2Utils;
import ru.example.mvvm.view.TextureStorage;
import ru.example.mvvm.viewmodel.viewmodels.BackgroundViewModel;
import ru.example.mvvm.viewmodel.viewmodels.CircleViewModel;
import ru.example.mvvm.viewmodel.ViewModelManager;

/**
 * В LibGDX точкой расширения игры считается реализации интерфейса ApplicationListener. Приложение запускается из
 * класса Lwjgl3Launcher, в котором назначается главный слушатель игрового цикла. В данный момент это класс Main
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private TextureStorage textureStorage;
    private ViewModelManager viewModelManager;
    private GameModel gameModel;

    /**
     * В методе <code>create</code> происходит инициализация игры перед её запуском
     */
    @Override
    public void create() {
        // Создаём объект через который мы будем просить OpenGL отрисовывать игровые спрайты (по сути, двумерные
        // картинки). SpriteBatch позволяет размещать спрайты в разных частях окна. Он преобразует спрайты в полигоны,
        // каждому полигону назначает шейдерную программу (см. раздел общей инструкции "Шейдеры") и отправляет весь
        // результат в OpenGL.
        spriteBatch = new SpriteBatch();

        // Создаём объект рендерера фигур. Практически то же самое, что и SpriteBatch, только рисует геометрические
        // фигуры вместо спрайтов. В остальном аналогичен SpriteBatch
        shapeRenderer = new ShapeRenderer();

        // Создаём объект через который будем контролировать загруженные в видеопамять изображения
        textureStorage = new TextureStorage();

        // Сразу загрузим нужный нам спрайт
        textureStorage.loadInternalTexture("logo", "libgdx.png");

        // Подготовим фабрику ViewModel'ов. Каждой логической сущности сопоставляем ViewModel, который будет
        // заниматься её отображением
        viewModelManager = new ViewModelManager();
        viewModelManager.addViewModelMapping(Background.class, () -> new BackgroundViewModel(textureStorage));
        viewModelManager.addViewModelMapping(MovingCircle.class, CircleViewModel::new);

        //Создаём и инициализируем игровую симуляцию. В данном случае мы выводим фоновую картинку и создаём 40
        // шариков, которые будут находиться в случайных местах экрана и двигаться в случайных направлениях со
        // скоростью 1
        gameModel = new GameModel();
        gameModel.addEntity(new Background(0, "logo"));
        for (int i = 1; i <= 40; i++) {
            gameModel.addEntity(createMovingCircle(i));
        }
        gameModel.start();

        //Сообщаем менеджеру ViewModel'ов список сущностей, которым хотим забиндить изображение на экране
        viewModelManager.updateViewModelBounds(gameModel.getEntities(), List.of());
    }

    private static MovingCircle createMovingCircle(int entityId) {
        return new MovingCircle(
            entityId,
            Vector2Utils.randomVector2(240).add(new Vector2(300, 220)),
            Vector2Utils.randomVector2(100),
            MathUtils.random(1, 20)
        );
    }

    /**
     * В методе <code>render</code> происходит обновление логики приложения и отрисовка картинки на экране. Метод
     * вызывается каждый такт игрового цикла. Метод НЕ вызывается через фиксированные отрезки времени, а сразу, как
     * только будет возможность произвести новую отрисовку
     */
    @Override
    public void render() {
        // Каждый такт полностью очищаем экран, заливая его одним цветом. Цвет кодируется в RGBA (red, green, blue,
        // alpha - прозрачность). Каждый из цветов кодируется диапазоном от 0 до 1.
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Обновляем состояние нашей симуляции
        gameModel.update();

        // Обновляем состояния view model-ов для каждой игровой сущности
        viewModelManager.updateViewModels(gameModel.getEntities());

        //Отрисовываем все сущности на экране.
        shapeRenderer.setAutoShapeType(true); // Позволяем автоматически определять тип рисуемой фигуры
        viewModelManager.drawView(spriteBatch, shapeRenderer);
    }

    /**
     * Метод вызывается в перед закрытием окна. Нужен для освобождения всех используемых ресурсов (как правило, такие
     * ресурсы реализуют интерфейс {@link com.badlogic.gdx.utils.Disposable})
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        textureStorage.dispose();
    }
}
