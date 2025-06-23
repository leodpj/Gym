import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        cpfInput.setPromptText("Somente números");

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);  // Tecla Enter aciona o botão

        loginButton.setOnAction(e -> {
            String cpf = cpfInput.getText().trim();

            if (!cpf.matches("\\d{11}")) {
                showAlert("CPF inválido", "O CPF deve conter exatamente 11 dígitos numéricos.");
                cpfInput.requestFocus();
                return;
            }

            // Se CPF válido, abre a tela principal (GymApp)
            try {
                new GymApp().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Erro", "Erro ao abrir a tela principal.");
            }
        });

        GridPane.setConstraints(cpfLabel, 0, 0);
        GridPane.setConstraints(cpfInput, 1, 0);
        GridPane.setConstraints(loginButton, 1, 1);

        grid.getChildren().addAll(cpfLabel, cpfInput, loginButton);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
