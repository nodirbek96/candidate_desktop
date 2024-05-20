package candidate;

import candidate.entity.user.User;
import candidate.entity.user.UserRepository;
import candidate.entity.user.UserTableCallbacks;
import candidate.st.StaticValues;
import candidate.ui.MainScreenPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class Candidate extends Application {

    @Override
    public void start(Stage primaryStage) {

        UserTableCallbacks userTableCallbacks = new UserRepository();
        userTableCallbacks.createUserTable();
        userTableCallbacks.insertUser(new User("Nodirbek", "Hasanboev", "creator", "1234"));
        Alert alert = new Alert(Alert.AlertType.NONE);

        BorderPane borderPane = new BorderPane();
        // borderPane.setTop(buttonAddUser);
        borderPane.setPadding(new Insets(10, 10, 10, 10));

        GridPane grid = new GridPane();
        VBox vbox = new VBox();
        Label labelTitle = new Label(StaticValues.TITLE_LOGIN);
        labelTitle.setFont(Font.font(StaticValues.LOGIN_TITLE_FONT));
        StackPane stackTitle = new StackPane();
        Label labelUsername = new Label(StaticValues.USERNAME);
        labelUsername.setFont(Font.font(StaticValues.LOGIN_FONT_VALUE));
        Label labelPassword = new Label(StaticValues.PASSWORD);
        labelPassword.setFont(Font.font(StaticValues.LOGIN_FONT_VALUE));
        TextField textFieldUsername = new TextField();
        textFieldUsername.setFont(Font.font(StaticValues.LOGIN_FONT_VALUE));
        PasswordField passwordField = new PasswordField();
        passwordField.clear();
        passwordField.setFont(Font.font(StaticValues.LOGIN_FONT_VALUE));
        Button btnSubmit = new Button(StaticValues.SUBMIT);
        btnSubmit.setFont(Font.font(StaticValues.LOGIN_FONT_VALUE));
        StackPane stackSubmit = new StackPane();
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('" + StaticValues.IMAGE_URL + "');"
                + "-fx-background-position: center center;"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: cover;");
        // grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(labelUsername, 0, 0);
        grid.add(textFieldUsername, 1, 0);
        grid.add(labelPassword, 0, 1);
        grid.add(passwordField, 1, 1);
        // stack title
        stackTitle.getChildren().add(labelTitle);
        stackTitle.setAlignment(Pos.CENTER);
        stackSubmit.getChildren().add(btnSubmit);
        stackSubmit.setAlignment(Pos.CENTER);
        // vbox
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(stackTitle, grid, stackSubmit);
        borderPane.setCenter(vbox);
        root.getChildren().add(borderPane);

        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        borderPane.setMinSize(width, height);
        Scene scene = new Scene(root);
        StaticValues.STAGE = primaryStage;
        StaticValues.sceneStart = scene;
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.setTitle("Candidate");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // actions
        btnSubmit.setOnAction(e -> {
            boolean status = userTableCallbacks.checkUser(textFieldUsername.getText(), passwordField.getText());
            if (status) {
                new MainScreenPage();
            } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Пароль или имя пользователя неверны");
                alert.show();
            }
        });

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                btnSubmit.fire();
            }
        });
        textFieldUsername.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                btnSubmit.fire();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
