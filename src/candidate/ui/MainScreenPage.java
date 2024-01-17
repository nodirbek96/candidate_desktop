package candidate.ui;

import candidate.Candidate;
import candidate.entity.candidate.CandidateCallbacks;
import candidate.entity.candidate.CandidateDto;
import candidate.entity.candidate.CandidateRepository;
import candidate.entity.uploaded_file.UploadedFileCallbacks;
import candidate.entity.uploaded_file.UploadedFileRepository;
import candidate.st.StaticValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MainScreenPage implements AddCandidateStageClosedCallbacks {

    private Candidate candidate;
    private Stage primaryStage;
    private final CandidateCallbacks candidateCallbacks;
    private final UploadedFileCallbacks uploadedFileCallbacks;
    private TableView<CandidateDto> tableView;

    public MainScreenPage() {
        candidateCallbacks = new CandidateRepository();
        uploadedFileCallbacks = new UploadedFileRepository();
        candidate = new Candidate();
        this.primaryStage = StaticValues.STAGE;
        primaryStage.setScene(new Scene(makePane()));
    }

    private BorderPane makePane() {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        BorderPane root = new BorderPane();
        Alert alert = new Alert(Alert.AlertType.NONE);

        root.setMinHeight(height);
        root.setMinWidth(width);
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("App");
        menuBar.getMenus().add(menu);
        MenuItem menuUserRegister = new MenuItem("Зарегистрировать пользователя");

        MenuItem menuContacts = new Menu("Contacts");
        menu.getItems().addAll(menuUserRegister, menuContacts);

        HBox hboxTop = new HBox();
        Button btnAddCandidate = new Button(StaticValues.ADD_CANDIDATE);
        Label labelSearchByFio = new Label(StaticValues.FIO);
        labelSearchByFio.setFont(Font.font(StaticValues.MAINPAGE_FONT_VALUE));
        TextField txtFio = new TextField();
        Label labelSearchPhone = new Label(StaticValues.PHONEORPASS);
        labelSearchPhone.setFont(Font.font(StaticValues.MAINPAGE_FONT_VALUE));
        TextField txtPhone = new TextField();
        Label labelBirthDate = new Label(StaticValues.BIRTHDATE);
        labelBirthDate.setFont(Font.font(StaticValues.MAINPAGE_FONT_VALUE));
        TextField txtBirthDate = new TextField();

        Label labelSearch = new Label(StaticValues.SEARCH);
        labelSearch.setFont(Font.font(StaticValues.MAINPAGE_FONT_VALUE));
        Button btnUpdate = new Button("Обновить");

        hboxTop.getChildren().addAll(btnAddCandidate, labelSearch, labelSearchByFio,
                txtFio, labelSearchPhone, txtPhone, labelBirthDate, txtBirthDate, btnUpdate);
        hboxTop.setSpacing(10);
        VBox vboxTop = new VBox();
        vboxTop.setPadding(new Insets(5, 5, 5, 5));
        vboxTop.getChildren().addAll(menuBar, hboxTop);
        vboxTop.setSpacing(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(vboxTop);

        tableView = new TableView<>();


        //tableView.setPadding(new Insets(10, 0, 0, 0));
        TableColumn<CandidateDto, String> colId = new TableColumn<>("id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<CandidateDto, String> colFirstname = new TableColumn<>("Имя");
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        TableColumn<CandidateDto, String> colMiddlename = new TableColumn<>("Отчество");
        colMiddlename.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        TableColumn<CandidateDto, String> colLastname = new TableColumn<>("Фамилия");
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        TableColumn<CandidateDto, String> colBirthDate = new TableColumn<>("Дата рождения");
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        makeHeaderWrappable(colBirthDate);
        TableColumn<CandidateDto, String> colAddress = new TableColumn<>("Адрес");
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<CandidateDto, String> colPhone = new TableColumn<>("Телефон");
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TableColumn<CandidateDto, String> colJobPlace = new TableColumn<>("Место работы");
        colJobPlace.setCellValueFactory(new PropertyValueFactory<>("jobPlace"));
        makeHeaderWrappable(colJobPlace);
        TableColumn<CandidateDto, String> colOccupation = new TableColumn<>("Профессион");
        colOccupation.setCellValueFactory(new PropertyValueFactory<>("occupation"));
        TableColumn<CandidateDto, String> colEducationPlace = new TableColumn<>("Место обучения");
        colEducationPlace.setCellValueFactory(new PropertyValueFactory<>("educationPlace"));
        makeHeaderWrappable(colEducationPlace);
        TableColumn<CandidateDto, String> colEducation = new TableColumn<>("Образование");
        colEducation.setCellValueFactory(new PropertyValueFactory<>("education"));
        makeHeaderWrappable(colEducation);
        TableColumn<CandidateDto, String> colRelative = new TableColumn<>("Родственник");
        colRelative.setCellValueFactory(new PropertyValueFactory<>("relative"));
        TableColumn<CandidateDto, String> colPosition = new TableColumn<>("Ha какую должность рассматривается");
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        makeHeaderWrappable(colPosition);
        TableColumn<CandidateDto, String> colPassport = new TableColumn<>("Пасспорт");
        colPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        TableColumn<CandidateDto, String> colEndDate = new TableColumn<>("Дата окончания");
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        makeHeaderWrappable(colEndDate);
        TableColumn<CandidateDto, String> colResult = new TableColumn<>("Результат");
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        TableColumn<CandidateDto, String> colCreatedDate = new TableColumn<>("Дата создания");
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        makeHeaderWrappable(colCreatedDate);
        ContextMenu contextMenu = new ContextMenu();

        // create menuitems
        MenuItem showMenuItem = new MenuItem("Скачать сохраненный файл");
        MenuItem editMenuItem = new MenuItem("Редактировать");
        MenuItem deleteMenuItem = new MenuItem("Удалить");

        // add menu items to menu
        contextMenu.getItems().add(showMenuItem);
        contextMenu.getItems().add(editMenuItem);
        contextMenu.getItems().add(deleteMenuItem);

        tableView.setContextMenu(contextMenu);

        tableView.getColumns().addAll(colId, colFirstname, colMiddlename, colLastname,
                colBirthDate, colAddress, colPhone, colJobPlace, colOccupation, colEducationPlace,
                colEducation, colRelative, colPosition, colPassport, colCreatedDate,
                colResult, colEndDate);
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableView.setItems(getCandidatesFromDb());
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        btnAddCandidate.setOnAction(e -> {
            AddCandidatePage addCandidatePage = new AddCandidatePage();
            addCandidatePage.makeStage(StaticValues.NEW_CANDIDATE, null);
        });

        editMenuItem.setOnAction(e -> {
            CandidateDto selectedCandidateDto = tableView.getSelectionModel().getSelectedItem();
            if (selectedCandidateDto != null) {
                AddCandidatePage addCandidatePage = new AddCandidatePage();
                addCandidatePage.makeStage(StaticValues.UPDATE_CANDIDATE, selectedCandidateDto);
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Кандидат не выбран");
                alert.show();
            }
        });

        showMenuItem.setOnAction(e -> {
            CandidateDto selectedCandidateDto = tableView.getSelectionModel().getSelectedItem();
            if (selectedCandidateDto != null) {
                String extension = isFileUploaded(selectedCandidateDto.getId());
                if (extension.equals("NONE")) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Файл не загружен этому кандидату");
                    alert.show();
                } else {
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter =
                            new FileChooser.ExtensionFilter(extension + " файл ", "*." + extension);
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showSaveDialog(primaryStage);
                    if (file != null) {
                        if (downloadFile(selectedCandidateDto.getId(), file)) {
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setContentText("Файл успешно загружен " + file.getAbsolutePath());
                            alert.show();
                        } else {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setContentText("Ошибка загрузки файла");
                            alert.show();
                        }
                    }
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Кандидат не выбран");
                alert.show();
            }
        });
        menuUserRegister.setOnAction(e -> {
            UserRegister userRegister = new UserRegister();
            userRegister.makeStage();
        });

        txtFio.setOnKeyPressed(e -> {
            if (e.getCode().isLetterKey()) {
                String suffix = txtFio.getText();
                tableView.setItems(sortedFioData(suffix));
            } else if (!txtFio.getText().isEmpty() && e.getCode() == KeyCode.BACK_SPACE) {
                String suffix = txtFio.getText();
                tableView.setItems(sortedFioData(suffix));
            }
        });
        txtPhone.setOnKeyPressed(e -> {
            if (e.getCode().isLetterKey() || e.getCode().isDigitKey()) {
                String suffix = txtPhone.getText();
                tableView.setItems(sortedPhoneData(suffix));
            } else if (!txtPhone.getText().isEmpty() && e.getCode() == KeyCode.BACK_SPACE) {
                String suffix = txtPhone.getText();
                tableView.setItems(sortedPhoneData(suffix));
            }
        });
        txtBirthDate.setOnKeyPressed(e -> {
            if (e.getCode().isLetterKey() || e.getCode().isDigitKey()) {
                String suffix = txtBirthDate.getText();
                tableView.setItems(sortedBirthDate(suffix));
            } else if (!txtBirthDate.getText().isEmpty() && e.getCode() == KeyCode.BACK_SPACE) {
                String suffix = txtBirthDate.getText();
                tableView.setItems(sortedBirthDate(suffix));
            }
        });
        btnUpdate.setOnAction(e -> {
            tableView.setItems(getCandidatesFromDb());
        });
        return root;
    }

    private ObservableList<CandidateDto> sortedFioData(String suffix) {
        candidateCallbacks.createCandidateTable();
        List<CandidateDto> sorted = candidateCallbacks.searchByFirstFirstnameOrLastnameOrMiddlename(suffix);
        return FXCollections.observableArrayList(sorted);
    }

    private ObservableList<CandidateDto> sortedPhoneData(String suffix) {
        candidateCallbacks.createCandidateTable();
        List<CandidateDto> sorted = candidateCallbacks.searchByPhoneOrPassport(suffix);
        return FXCollections.observableArrayList(sorted);
    }

    private ObservableList<CandidateDto> sortedBirthDate(String suffix) {
        candidateCallbacks.createCandidateTable();
        List<CandidateDto> sorted = candidateCallbacks.searchByBirthDate(suffix);
        return FXCollections.observableArrayList(sorted);
    }

    private String isFileUploaded(Integer candidateId) {
        uploadedFileCallbacks.createFileUploadTable();
        return uploadedFileCallbacks.isFileUploaded(candidateId);
    }

    private boolean downloadFile(Integer candidateId, File file) {
        uploadedFileCallbacks.createFileUploadTable();
        return uploadedFileCallbacks.downloadFile(candidateId, file);
    }

    private ObservableList<CandidateDto> getCandidatesFromDb() {
        candidateCallbacks.createCandidateTable();
        List<CandidateDto> listOfCandidates = candidateCallbacks.getAllCandidates();
        listOfCandidates.add(new CandidateDto());
        return FXCollections.observableArrayList(listOfCandidates);
    }

    private void makeHeaderWrappable(TableColumn<CandidateDto, String> col) {
        Label label = new Label(col.getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(col.widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        col.setGraphic(stack);
    }

    @Override
    public void stageClosed() {
        tableView.setItems(getCandidatesFromDb());
    }

}
