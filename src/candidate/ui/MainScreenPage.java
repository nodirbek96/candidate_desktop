package candidate.ui;

import candidate.Candidate;
import candidate.entity.candidate.CandidateCallbacks;
import candidate.entity.candidate.CandidateDto;
import candidate.entity.candidate.CandidateRepository;
import candidate.st.StaticValues;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author nodirbek
 */
public class MainScreenPage implements AddCandidateStageClosedCallbacks{

    private Candidate candidate;
    private Stage primaryStage;
    private CandidateCallbacks candidateCallbacks;
    private TableView tableView;

    public MainScreenPage() {
        candidateCallbacks=new CandidateRepository();
        candidate = new Candidate();
        this.primaryStage = StaticValues.STAGE;
        primaryStage.setScene(new Scene(makePane()));
    }

    private BorderPane makePane() {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        BorderPane root = new BorderPane();
        root.setMinHeight(height);
        root.setMinWidth(width);
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("App");
        menuBar.getMenus().add(menu);
        MenuItem menuInfo = new MenuItem("Info");
        MenuItem menuContacts = new Menu("Contacts");
        menu.getItems().addAll(menuInfo, menuContacts);

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
        TextField txtBirhtDate = new TextField();

        Label labelSearch = new Label(StaticValues.SEARCH);
        labelSearch.setFont(Font.font(StaticValues.MAINPAGE_FONT_VALUE));

        hboxTop.getChildren().addAll(btnAddCandidate, labelSearch, labelSearchByFio,
                txtFio, labelSearchPhone, txtPhone, labelBirthDate, txtBirhtDate);
        hboxTop.setSpacing(10);
        VBox vboxTop = new VBox();
        vboxTop.getChildren().addAll(menuBar, hboxTop);
        vboxTop.setSpacing(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(vboxTop);

        tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        
        tableView.setPadding(new Insets(10, 0, 0, 0));
        TableColumn colId = new TableColumn("id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colFirstname = new TableColumn("Имя");
        colFirstname.setCellValueFactory(new PropertyValueFactory("firstname"));
        TableColumn colMiddlename = new TableColumn("Отчество");
        colMiddlename.setCellValueFactory(new PropertyValueFactory("middleName"));
        TableColumn colLastname = new TableColumn("Фамилия");
        colLastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        TableColumn colBirthDate = new TableColumn("Дата рождения");
        colBirthDate.setCellValueFactory(new PropertyValueFactory("birthDate"));
        makeHeaderWrappable(colBirthDate);
        TableColumn colAddress = new TableColumn("Адрес");
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        TableColumn colPhone = new TableColumn("Телефон");
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        TableColumn colJobPlace = new TableColumn("Место работы");
        colJobPlace.setCellValueFactory(new PropertyValueFactory("jobPlace"));
        makeHeaderWrappable(colJobPlace);
        TableColumn colOccupation = new TableColumn("Профессион");
        colOccupation.setCellValueFactory(new PropertyValueFactory("occupation"));
        TableColumn colEducationPlace = new TableColumn("Место обучения");
        colEducationPlace.setCellValueFactory(new PropertyValueFactory("educationPlace"));
        makeHeaderWrappable(colEducationPlace);
        TableColumn colEducation = new TableColumn("Образование");
        colEducation.setCellValueFactory(new PropertyValueFactory("education"));
        makeHeaderWrappable(colEducation);
        TableColumn colRelative = new TableColumn("Родственник");
        colRelative.setCellValueFactory(new PropertyValueFactory("relative"));
        TableColumn colPostion = new TableColumn("Ha какую должность рассматривается");
        colPostion.setCellValueFactory(new PropertyValueFactory("position"));
        makeHeaderWrappable(colPostion);
        TableColumn colPassport = new TableColumn("Пасспорт");
        colPassport.setCellValueFactory(new PropertyValueFactory("passport"));
        TableColumn colFile = new TableColumn("Файл");
        colFile.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colEndDate = new TableColumn("Дата окончания");
        colEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
        makeHeaderWrappable(colEndDate);
        TableColumn colResult = new TableColumn("Результат");
        colResult.setCellValueFactory(new PropertyValueFactory("result"));
        TableColumn colCreatedDate = new TableColumn("Дата создания");
        colCreatedDate.setCellValueFactory(new PropertyValueFactory("createdDate"));
        makeHeaderWrappable(colCreatedDate);
        
        tableView.getColumns().addAll(colId, colFirstname, colMiddlename, colLastname,
                colBirthDate, colAddress, colPhone, colJobPlace, colOccupation, colEducationPlace,
                colEducation, colRelative, colPostion, colPassport, colFile, colCreatedDate,
                colResult, colEndDate);
        
        tableView.setItems(getCandidatesFromDb());
        root.setCenter(tableView);

        btnAddCandidate.setOnAction(e->{
            AddCandidatePage addCandidatePage=new AddCandidatePage();
            addCandidatePage.makeStage();
        });
        return root;
    }

    private ObservableList<CandidateDto> getCandidatesFromDb(){
        candidateCallbacks.createCandidateTable();
        List<CandidateDto> listOfCandidates=candidateCallbacks.getAllCandidates();
        listOfCandidates.add(new CandidateDto());
        return FXCollections.observableArrayList(listOfCandidates);
    }
    private void makeHeaderWrappable(TableColumn col) {
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
