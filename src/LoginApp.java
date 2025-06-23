import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login - Academia");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label cpfLabel = new Label("CPF:");
        TextField cpfInput = new TextField();

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);          // Enter faz login
        loginButton.setOnAction(e -> {
            if (cpfInput.getText().trim().isEmpty()) {
                cpfInput.requestFocus();
                return;
            }
            // aqui você pode validar o CPF…
            // se válido, abre a tela principal:
            new GymApp().start(primaryStage);
        });

        GridPane.setConstraints(cpfLabel, 0, 0);
        GridPane.setConstraints(cpfInput, 1, 0);
        GridPane.setConstraints(loginButton, 1, 1);

        grid.getChildren().addAll(cpfLabel, cpfInput, loginButton);

        primaryStage.setScene(new Scene(grid, 300, 150));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
