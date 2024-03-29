package candidate.ui;

import candidate.entity.candidate.CandidateCallbacks;
import candidate.entity.candidate.CandidateDto;
import candidate.entity.candidate.CandidateRepository;
import candidate.entity.uploaded_file.UploadedFile;
import candidate.entity.uploaded_file.UploadedFileCallbacks;
import candidate.entity.uploaded_file.UploadedFileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import candidate.st.StaticValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCandidatePage {

    private final CandidateCallbacks candidateCallbacks;
    private final AddCandidateStageClosedCallbacks addCandidateStageClosedCallbacks;
    private final UploadedFileCallbacks uploadedFileCallbacks;
    private File selectedFile;

    private boolean isFileSelected = false;

    public AddCandidatePage() {
        candidateCallbacks = new CandidateRepository();
        addCandidateStageClosedCallbacks = new MainScreenPage();
        uploadedFileCallbacks = new UploadedFileRepository();
    }

    public void makeStage(String def, CandidateDto selectedCandidate) {

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        Label labelTitle = new Label(StaticValues.ADD_CANDIDATE);
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
        hb.setSpacing(500);
        bottomStack.getChildren().addAll(hb);

        Label labelFirstname = new Label("Имя");
        TextField txtFirstname = new TextField();
        Label labelMiddleName = new Label("Отчество");
        TextField txtMiddlename = new TextField();
        Label labelLastname = new Label("Фамилия");
        TextField txtLastname = new TextField();
        Label labelBirthDate = new Label("Дата рождения");
        DatePicker datePickerBirthDate = new DatePicker();
        Label labelAddress = new Label("Адрес");
        TextField txtAddress = new TextField();
        Label labelPhone = new Label("Телефон");
        TextField txtPhone = new TextField();
        Label labelJobPlace = new Label("Место работы");
        TextField txtJobPlace = new TextField();
        Label labelOccupation = new Label("Профессион");
        TextField txtOccupation = new TextField();
        Label labelEducationPlace = new Label("Место обучения");
        TextField txtEducationPlace = new TextField();
        Label labelEducation = new Label("Образование");
        TextField txtEducation = new TextField();
        Label labelRelative = new Label("Родственник");
        TextField txtRelative = new TextField();
        Label labelPosition = new Label("Ha какую должность\nрассматривается");
        TextField txtPosition = new TextField();
        Label labelPassport = new Label("Пасспорт");
        TextField txtPassport = new TextField();
        Label labelFile = new Label("Файл выбора");
        Button btnFileChooser = new Button("Файл выбора");
        Label labelResult = new Label("Результат");
        TextArea txtResult = new TextArea();
        Label labelSelectedFile = new Label();
        labelSelectedFile.setTextFill(Color.GREEN);
        txtResult.setPrefHeight(50);
        txtResult.setPrefWidth(300);

        FileChooser fileChooser = new FileChooser();

        DatePicker datePickerEndDate = new DatePicker();
        datePickerEndDate.setValue(LocalDate.now());

        if (def.equalsIgnoreCase(StaticValues.UPDATE_CANDIDATE)) {
            txtFirstname.setText(selectedCandidate.getFirstname());
            txtLastname.setText(selectedCandidate.getLastname());
            txtMiddlename.setText(selectedCandidate.getMiddleName());
            txtAddress.setText(selectedCandidate.getAddress());
            txtPhone.setText(selectedCandidate.getPhone());
            txtPassport.setText(selectedCandidate.getPassport());
            txtJobPlace.setText(selectedCandidate.getJobPlace());
            txtEducation.setText(selectedCandidate.getEducation());
            txtEducationPlace.setText(selectedCandidate.getEducationPlace());
            txtOccupation.setText(selectedCandidate.getOccupation());
            txtRelative.setText(selectedCandidate.getRelative());
            txtResult.setText(selectedCandidate.getResult());
            txtPosition.setText(selectedCandidate.getPosition());
            datePickerBirthDate.setValue(selectedCandidate.getBirthDate());
            datePickerEndDate.setValue(selectedCandidate.getEndDate());
        }

        grid.add(labelFirstname, 0, 0);
        grid.add(txtFirstname, 1, 0);
        grid.add(labelMiddleName, 2, 0);
        grid.add(txtMiddlename, 3, 0);
        grid.add(labelLastname, 0, 1);
        grid.add(txtLastname, 1, 1);
        grid.add(labelBirthDate, 2, 1);
        grid.add(datePickerBirthDate, 3, 1);
        grid.add(labelAddress, 0, 2);
        grid.add(txtAddress, 1, 2);
        grid.add(labelPhone, 2, 2);
        grid.add(txtPhone, 3, 2);
        grid.add(labelJobPlace, 0, 3);
        grid.add(txtJobPlace, 1, 3);
        grid.add(labelOccupation, 2, 3);
        grid.add(txtOccupation, 3, 3);
        grid.add(labelEducationPlace, 0, 4);
        grid.add(txtEducationPlace, 1, 4);
        grid.add(labelEducation, 2, 4);
        grid.add(txtEducation, 3, 4);
        grid.add(labelRelative, 0, 5);
        grid.add(txtRelative, 1, 5);
        grid.add(labelPosition, 2, 5);
        grid.add(txtPosition, 3, 5);
        grid.add(labelPassport, 0, 6);
        grid.add(txtPassport, 1, 6);
        grid.add(labelFile, 2, 6);
        grid.add(btnFileChooser, 3, 6);
        grid.add(labelResult, 0, 7);
        grid.add(txtResult, 1, 7, 2, 1);
        grid.add(datePickerEndDate, 3, 7);
        grid.add(labelSelectedFile, 0, 8, 3, 1);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);
        root.setTop(titleHolder);
        root.setCenter(stackPane);
        root.setBottom(bottomStack);
        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        //actions 
        btnCancel.setOnAction(e -> {
            System.out.println("cancel is pressed");
            stage.close();
        });

        btnFileChooser.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                isFileSelected = true;
                labelSelectedFile.setText(selectedFile.getAbsolutePath());
            } else {
                isFileSelected = false;
                labelSelectedFile.setText("Файл не выбран");
            }
        });

        btnSave.setOnAction(e -> {
            System.out.println("Saved is pressed");
            Path path = null;

            String filePath;
            String fileName = null;
            if (!isFileSelected) {
                labelSelectedFile.setText("Файл не выбран");
                filePath = "Файл не выбран";
            } else {
                filePath = selectedFile.getAbsolutePath();
                fileName = selectedFile.getName();
                path = Paths.get(selectedFile.getAbsolutePath());
            }

            String firstname = txtFirstname.getText();
            String lastname = txtLastname.getText();
            String middlename = txtMiddlename.getText();
            LocalDate birthdate = datePickerBirthDate.getValue();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();
            String jobPlace = txtJobPlace.getText();
            String occupation = txtOccupation.getText();
            String educationPlace = txtEducationPlace.getText();
            String education = txtEducation.getText();
            String relative = txtRelative.getText();
            String position = txtPosition.getText();
            String passport = txtPassport.getText();
            String result = txtResult.getText();
            LocalDate endDate = datePickerEndDate.getValue();

            CandidateDto candidateDto = new CandidateDto();

            candidateDto.setFirstname(firstname);
            candidateDto.setLastname(lastname);
            candidateDto.setAddress(address);
            candidateDto.setMiddleName(middlename);
            candidateDto.setBirthDate(birthdate);
            candidateDto.setPhone(phone);
            candidateDto.setJobPlace(jobPlace);
            candidateDto.setOccupation(occupation);
            candidateDto.setEducationPlace(educationPlace);
            candidateDto.setEducation(education);
            candidateDto.setRelative(relative);
            candidateDto.setPosition(position);
            candidateDto.setPassport(passport);
            candidateDto.setResult(result);
            candidateDto.setEndDate(endDate);
            candidateDto.setUserId(1);

            if (def.equalsIgnoreCase(StaticValues.NEW_CANDIDATE)) {
                CandidateDto savedCandidateDto = insertCandidate(candidateDto);
                if (savedCandidateDto != null) {
                    System.out.println("Candidate is saved into db ");
                    System.out.println("Candidate " + savedCandidateDto);

                    if (uploadedFileCallbacks.createFileUploadTable()) {
                        if (fileName != null) {
                            try {
                                UploadedFile uploadedFile = uploadedFileCallbacks.insertFile(new UploadedFile(
                                        savedCandidateDto.getId(),
                                        fileName,
                                        fileName.substring(fileName.lastIndexOf(".") + 1),
                                        filePath,
                                        null,
                                        Math.toIntExact(Files.size(path))
                                ));
                                UploadedFile saved = uploadedFileCallbacks.insertFile(uploadedFile);
                                System.out.println("File: " + saved);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } else {
                        System.out.println("files table not created");
                    }
                } else {
                    System.out.println("Candidate is not saved ");
                }
            } else {
                candidateDto.setId(selectedCandidate.getId());
                CandidateDto updatedCandidateDto = updateCandidate(candidateDto);
                if (updatedCandidateDto != null) {
                    System.out.println("Candidate is updated into db ");
                    System.out.println("Candidate " + updatedCandidateDto);

                    if (uploadedFileCallbacks.createFileUploadTable()) {
                        if (fileName != null) {
                            try {
                                UploadedFile uploadedFile = uploadedFileCallbacks.insertFile(new UploadedFile(
                                        updatedCandidateDto.getId(),
                                        fileName,
                                        fileName.substring(fileName.lastIndexOf(".") + 1),
                                        filePath,
                                        null,
                                        Math.toIntExact(Files.size(path))
                                ));
                                UploadedFile saved = uploadedFileCallbacks.insertFile(uploadedFile);
                                System.out.println("File: " + saved);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } else {
                        System.out.println("files table not created");
                    }
                } else {
                    System.out.println("Candidate is not updated ");
                }
            }
            addCandidateStageClosedCallbacks.stageClosed();
            stage.close();
        });
        stage.setResizable(false);
        stage.showAndWait();
    }

    private CandidateDto insertCandidate(CandidateDto candidateDto) {
        candidateCallbacks.createCandidateTable();
        return candidateCallbacks.insertCandidate(candidateDto);
    }

    private CandidateDto updateCandidate(CandidateDto candidateDto) {
        return candidateCallbacks.updateCandidate(candidateDto);
    }

}
