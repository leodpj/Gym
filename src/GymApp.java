import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GymApp extends Application {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Academia");

        /* Top bar: logomarca + data/hora */
        Label logo = new Label("LOGOMARCA DA ACADEMIA");
        Label dateTimeLabel = new Label();
        updateDateTime(dateTimeLabel);

        HBox topBar = new HBox(10, logo, dateTimeLabel);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(dateTimeLabel, new Insets(0, 0, 0, 20));

        /* Tabs centrais */
        TabPane tabPane = new TabPane(
                new Tab("TREINO DO DIA"),
                new Tab("AVISOS"),
                new Tab("DICAS DE SAÚDE")
        );
        tabPane.getTabs().forEach(t -> t.setClosable(false));

        /* Menu no topo */
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("MENU");
        menu.getItems().addAll(
                new MenuItem("Dados Pessoais"),
                new MenuItem("Mensagens"),
                new MenuItem("Dados Academia"),
                new MenuItem("Pagamentos")
        );
        menuBar.getMenus().add(menu);

        BorderPane root = new BorderPane();
        root.setTop(new VBox(menuBar, topBar));
        root.setCenter(tabPane);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        /* Relógio em Timeline (thread-safe) */
        Timeline clock = new Timeline(
                new KeyFrame(Duration.ZERO, e -> updateDateTime(dateTimeLabel)),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void updateDateTime(Label label) {
        label.setText(LocalDateTime.now().format(formatter));
    }
}
