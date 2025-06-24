import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GymApp extends Application {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema da Academia");

        // Topo: logomarca e relógio
        Label logo = new Label("🏋️ LOGOMARCA DA ACADEMIA");
        Label dateTimeLabel = new Label();
        updateDateTime(dateTimeLabel);

        HBox topBar = new HBox(10, logo, dateTimeLabel);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(dateTimeLabel, new Insets(0, 0, 0, 20));

        // Menu superior
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("MENU");
        menu.getItems().addAll(
                new MenuItem("Dados Pessoais"),
                new MenuItem("Mensagens"),
                new MenuItem("Dados Academia"),
                new MenuItem("Pagamentos")
        );
        menuBar.getMenus().add(menu);

        // Abas
        Tab treinoTab = new Tab("TREINO DO DIA", new Label("🏋️ Treino de hoje: Peito e tríceps"));
        Tab avisosTab = new Tab("AVISOS", new Label("🔔 Nenhum aviso no momento."));
        Tab dicasTab = new Tab("DICAS DE SAÚDE", new Label("💡 Beba pelo menos 2L de água por dia."));
        Tab nutricionistaTab = new Tab("NUTRICIONISTA", new Label("📅 Consultas disponíveis toda quarta-feira."));
        Tab lotacaoTab = new Tab("LOTAÇÃO", new Label("👥 Pessoas na academia agora: 32"));
        Tab horarioTab = new Tab("HORÁRIOS", new Label("⏰ Segunda a Sábado: 06:00 - 22:00"));
        Tab agendamentoTab = new Tab("AGENDAR AULAS", criarPainelAgendamento());

        TabPane tabPane = new TabPane(
                treinoTab, avisosTab, dicasTab,
                nutricionistaTab, lotacaoTab, horarioTab, agendamentoTab
        );
        tabPane.getTabs().forEach(t -> t.setClosable(false));

        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(new VBox(menuBar, topBar));
        root.setCenter(tabPane);

        // Cena e exibição
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // Atualiza relógio com Timeline (thread-safe)
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

    private VBox criarPainelAgendamento() {
        ComboBox<String> aulaCombo = new ComboBox<>();
        aulaCombo.getItems().addAll("Spinning", "Yoga", "Funcional", "Pilates");

        DatePicker dataPicker = new DatePicker();
        Button reservarBtn = new Button("Reservar");
        Label resultado = new Label();

        reservarBtn.setOnAction(e -> {
            String aula = aulaCombo.getValue();
            String data = dataPicker.getValue() != null ? dataPicker.getValue().toString() : null;
            if (aula != null && data != null) {
                resultado.setText("✅ Aula '" + aula + "' reservada para " + data);
            } else {
                resultado.setText("❌ Selecione aula e data.");
            }
        });

        VBox box = new VBox(10,
                new Label("Selecione a aula:"),
                aulaCombo,
                new Label("Escolha a data:"),
                dataPicker,
                reservarBtn,
                resultado
        );
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.TOP_LEFT);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
