package fx.aal.arduapp.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import fx.aal.arduapp.MainApp;
import fx.aal.arduapp.model.User;

public class patientOverviewController {
    @FXML
    private TableView<User> patientTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private Label nombrelb;
    @FXML
    private Label apellidoslb;
    @FXML
    private Label callelb;
    @FXML
    private Label codigoPostallb;
    @FXML
    private Label ciudadlb;
    @FXML
    private Label cumplelb;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public patientOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }


    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param user the person or null
     */
    private void showPersonDetails(User user) {
        if (user != null) {
            // Fill the labels with info from the person object.
            nombrelb.setText(user.getFirstName());
            apellidoslb.setText(user.getLastName());
            callelb.setText(user.getStreet());
            codigoPostallb.setText(Integer.toString(user.getPostalCode()));
            ciudadlb.setText(user.getCity());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	nombrelb.setText("");
        	apellidoslb.setText("");
        	callelb.setText("");
        	codigoPostallb.setText("");
        	ciudadlb.setText("");
            cumplelb.setText("");
        }
    }


    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePatient() {
        int selectedIndex = patientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
           patientTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        patientTable.setItems(mainApp.getuserData());
    }
}