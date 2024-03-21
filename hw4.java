package application;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class hw4 extends Application {
	public void start(Stage primaryStage) {
		Label label = new Label("Welcome to Heart Health Imaging and Recording System");
        Button PatientIntake = new Button("Patient Intake");
        Button CTScanTechView = new Button("CT Scan Tech View");
        Button PatientView = new Button("Patient View");
        
        String buttonStyle = "-fx-font-size: 16px; -fx-background-color: #0000FF; -fx-text-fill: black;";
        PatientIntake.setStyle(buttonStyle);
        CTScanTechView.setStyle(buttonStyle);
        PatientView.setStyle(buttonStyle);
        PatientIntake.setPrefSize(300, 50);
        CTScanTechView.setPrefSize(300, 50);
        PatientView.setPrefSize(300, 50);
        
        PatientIntake.setMinWidth(200);
        CTScanTechView.setMinWidth(200);
        PatientView.setMinWidth(200);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(25); 
        vBox.getChildren().addAll(label, PatientIntake, CTScanTechView, PatientView);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20)); // Margin around the VBox

        Scene scene = new Scene(vBox, 450, 300); // Width and height of the window
        primaryStage.setScene(scene);
        primaryStage.show();
        
        PatientIntake.setOnAction(e -> {
            showPatientIntakeForm();
        });
        CTScanTechView.setOnAction(e -> {
            showTechnicianView();
        });
        PatientView.setOnAction(e -> {
        	showPatientView();
        });
    }

    private void showPatientIntakeForm() {
    	Stage intakeStage = new Stage();
    	GridPane grid = createPatientIntakeFormGrid();

        Scene scene = new Scene(grid, 500, 325);
        intakeStage.setScene(scene);
        intakeStage.show();
		
	}
    
    private GridPane createPatientIntakeFormGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label lblFormTitle = new Label("Patient Intake Form");
        Label lblFirstName = new Label("First Name:");
        TextField txtFirstName = new TextField();
        Label lblLastName = new Label("Last Name:");
        TextField txtLastName = new TextField();
        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        Label lblPhone = new Label("Phone Number:");
        TextField txtPhone = new TextField();
        Label lblHealthHistory = new Label("Health History:");
        TextField txtHealthHistory = new TextField();
        Label lblInsuranceID = new Label("Insurance ID:");
        TextField txtInsuranceID = new TextField();
        
        grid.add(lblFormTitle, 1, 0);
        grid.add(lblFirstName, 0, 1);
        grid.add(txtFirstName, 1, 1);
        grid.add(lblLastName, 0, 2);
        grid.add(txtLastName, 1, 2);
        grid.add(lblEmail, 0, 3);
        grid.add(txtEmail, 1, 3);
        grid.add(lblPhone, 0, 4);
        grid.add(txtPhone, 1, 4);
        grid.add(lblHealthHistory, 0, 5);
        grid.add(txtHealthHistory, 1, 5);
        grid.add(lblInsuranceID, 0, 6);
        grid.add(txtInsuranceID, 1, 6);

        Button Save = new Button("Save");
        String buttonStyle = "-fx-font-size: 16px; -fx-background-color: #0000FF; -fx-text-fill: black;";
        Save.setStyle(buttonStyle);
        Save.setOnAction(e -> savePatientInfo(
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtPhone.getText(),
                txtHealthHistory.getText(),
                txtInsuranceID.getText()
            ));

        grid.add(Save, 3, 7);

        return grid;
    }
    
    private void savePatientInfo(String firstName, String lastName, String email, String phone, String healthHistory, String insuranceID) {
        // Generate a random 5-digit ID for the patient
        String patientID = String.format("%05d", new Random().nextInt(100000));
        String filename = patientID + "_PatientInfo.txt";

        // Save patient info to the file
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)))) {
            out.println("Patient ID: " + patientID);
            out.println("First Name: " + firstName);
            out.println("Last Name: " + lastName);
            out.println("Email: " + email);
            out.println("Phone Number: " + phone);
            out.println("Health History: " + healthHistory);
            out.println("Insurance ID: " + insuranceID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Saved");
        alert.setHeaderText(null);
        alert.setContentText("Patient information saved with ID: " + patientID);
        alert.showAndWait();
    }
    
    private void showTechnicianView() {
        Stage techStage = new Stage();
        VBox layout = new VBox(10);
        GridPane grid = createTechnicianDataEntryGrid();
        layout.getChildren().addAll(grid);

        Scene scene = new Scene(layout, 450, 350); // Adjust the size as needed
        techStage.setScene(scene);
        techStage.show();
    }
    
    private GridPane createTechnicianDataEntryGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label lblPatientID = new Label("Patient ID:");
        TextField txtPatientID = new TextField();
        Label lblTotalScore = new Label("The total Agatston CAC score:");
        TextField txtTotalScore = new TextField();
        Label lblVesselScore = new Label("Vessel level Agatston CAC score");
        Label lblLM = new Label("LM:");
        TextField txtLM = new TextField();
        Label lblLAD = new Label("LAD:");
        TextField txtLAD = new TextField();
        Label lblLCX = new Label("LCX:");
        TextField txtLCX = new TextField();
        Label lblRCA = new Label("RCA:");
        TextField txtRCA = new TextField();
        Label lblPDA = new Label("PDA:");
        TextField txtPDA = new TextField();

        grid.add(lblPatientID, 0, 0);
        grid.add(txtPatientID, 1, 0);
        grid.add(lblTotalScore, 0, 1);
        grid.add(txtTotalScore, 1, 1);
        grid.add(lblVesselScore, 0, 2);
        grid.add(lblLM, 0, 3);
        grid.add(txtLM, 1, 3);
        grid.add(lblLAD, 0, 4);
        grid.add(txtLAD, 1, 4);
        grid.add(lblLCX, 0, 5);
        grid.add(txtLCX, 1, 5);
        grid.add(lblRCA, 0, 6);
        grid.add(txtRCA, 1, 6);
        grid.add(lblPDA, 0, 7);
        grid.add(txtPDA, 1, 7);

        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #0000FF; -fx-text-fill: black;");
        btnSave.setOnAction(e -> saveCTScanData(txtPatientID.getText(), txtTotalScore.getText(), 
                                                txtLM.getText(), txtLAD.getText(), txtLCX.getText(), txtRCA.getText(), txtPDA.getText()));
        
        GridPane.setHalignment(btnSave, HPos.RIGHT);
        grid.add(btnSave, 1, 8); 

        return grid;
    }
    
    private void saveCTScanData(String patientID, String totalScore, String lmScore, String ladScore, String lcxScore, String rcaScore, String pdaScore) {
        if (patientID.isEmpty() || totalScore.isEmpty() || lmScore.isEmpty() || ladScore.isEmpty() || lcxScore.isEmpty() || rcaScore.isEmpty() || pdaScore.isEmpty()) {
            showAlert("Error", "All fields must be filled out before saving.");
        } else {
            String ctScanDataFilename = patientID + "CTResults.txt";
            try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get(ctScanDataFilename)))) {
                out.println("The total Agatston CAC score: " + totalScore);
                out.println("LM : " + lmScore);
                out.println("LAD: " + ladScore);
                out.println("LCX: " + lcxScore);
                out.println("RCA: " + rcaScore);
                out.println("PDA: " + pdaScore);
                showAlert("Success", "CT Scan data saved successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to save CT Scan data.");
            }
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showPatientView() {
        Stage patientStage = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label lblPatientID = new Label("Enter Patient ID:");
        TextField txtPatientID = new TextField();
        Button btnView = new Button("View Report");
        btnView.setOnAction(e -> viewPatientReport(txtPatientID.getText()));

        grid.add(lblPatientID, 0, 0);
        grid.add(txtPatientID, 1, 0);
        grid.add(btnView, 1, 1);

        Scene scene = new Scene(grid, 350, 200);
        patientStage.setScene(scene);
        patientStage.show();
    }
    
    private void viewPatientReport(String patientID) {
    	String patientInfoFilename = patientID + "_PatientInfo.txt";
        String ctScanDataFilename = patientID + "CTResults.txt";

        try {
        	List<String> patientInfoLines = Files.readAllLines(Paths.get(patientInfoFilename));
            List<String> ctScanDataLines = Files.readAllLines(Paths.get(ctScanDataFilename));
            
            String firstName = patientInfoLines.stream()
                    .filter(line -> line.startsWith("First Name: "))
                    .findFirst()
                    .map(line -> line.substring(12))
                    .orElse("Unknown");

                String lastName = patientInfoLines.stream()
                    .filter(line -> line.startsWith("Last Name: "))
                    .findFirst()
                    .map(line -> line.substring(11))
                    .orElse("Unknown");

                String patientName = firstName + " " + lastName;

            Stage reportStage = new Stage();
            VBox layout = new VBox(10);
            TextArea reportArea = new TextArea();
            reportArea.setEditable(false);

            reportArea.appendText("                                                       Hello " + patientName + "\n");
            for (String line : ctScanDataLines) {
                reportArea.appendText(line + "\n");
            }

            layout.getChildren().add(reportArea);
            Scene scene = new Scene(layout, 500, 400); // Adjust the size as needed
            reportStage.setScene(scene);
            reportStage.show();

        } catch (IOException e) {
            showAlert("Error", "Wrong patient ID or no data available yet.");
        }
    }
    
	public static void main(String[] args) {
        launch(args);
    }
}
