package candidate.ui;

import candidate.entity.user.User;
import candidate.entity.user.UserRepository;
import candidate.entity.user.UserTableCallbacks;
import candidate.st.StaticValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserRegister {
    private final UserTableCallbacks userTableCallbacks;

    public UserRegister() {
        userTableCallbacks = new UserRepository();
    }

    public void makeStage() {

        Alert alert = new Alert(Alert.AlertType.NONE);

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        Label labelTitle = new Label("Зарегистрировать пользователя");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setFont(Font.font(StaticValues.LOGIN_TITLE_FONT));
        StackPane titleHolder = new StackPane();
        titleHolder.getChildren().add(labelTitle);
        Button btnSave = new Button("Сохранять");
        Button btnCancel = new Button("Отмена");
        StackPane bottomStack = new StackPane();
        bottomStack.setPadding(new Insets(10, 10, 10, 10));
        HBox hb = new HBox();
        hb.getChildren().addAll(btnCancel, btnSave);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(250);
        bottomStack.getChildren().addAll(hb);


        Label labelFirstname = new Label("Имя");
        TextField txtFirstname = new TextField();
        Label labelLastname = new Label("Фамилия");
        TextField txtLastname = new TextField();
        Label labelUsername = new Label("Имя пользователя");
        TextField txtUsername = new TextField();
        Label labelPassword = new Label("Пароль");
        TextField txtPassword = new TextField();

        grid.add(labelFirstname, 0, 0);
        grid.add(txtFirstname, 1, 0);
        grid.add(labelLastname, 0, 1);
        grid.add(txtLastname, 1, 1);
        grid.add(labelUsername, 0, 2);
        grid.add(txtUsername, 1, 2);
        grid.add(labelPassword, 0, 3);
        grid.add(txtPassword, 1, 3);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);
        root.setTop(titleHolder);
        root.setBottom(bottomStack);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);


        // actions..
        btnSave.setOnAction(e -> {
            userTableCallbacks.createUserTable();
            if (!txtFirstname.getText().isEmpty() && !txtLastname.getText().isEmpty() && !txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
                userTableCallbacks.insertUser(new User(txtFirstname.getText(),
                        txtLastname.getText(),
                        txtUsername.getText(),
                        txtPassword.getText()));
                stage.close();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Данные заполнены не верно");
                alert.show();
            }
        });
        btnCancel.setOnAction(e -> {
            stage.close();
        });

        stage.showAndWait();
    }
}
