package fx.aal.arduapp.view;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fx.aal.arduapp.util.DateUtil;

import java.time.LocalDate;

import fx.aal.arduapp.model.Patient;
import fx.aal.arduapp.model.User;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PatientEditDialogController {

    @FXML
    private TextField nombreTf;
    @FXML
    private TextField apellidosTf;
    @FXML
    private TextField calleTf;
    @FXML
    private TextField codigoPostalTf;
    @FXML
    private TextField ciudadTf;
    @FXML
    private TextField cumpleTf;


    private Stage dialogStage;
    private User patient;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param patient
     */
    public void setPatient(User patient) {
        this.patient = patient;


        nombreTf.setText(patient.getFirstName());
        apellidosTf.setText(patient.getLastName());
        calleTf.setText(patient.getStreet());
        codigoPostalTf.setText(Integer.toString(patient.getPostalCode()));
        ciudadTf.setText(patient.getCity());
        cumpleTf.setText(DateUtil.format(patient.getBirthday()));
        cumpleTf.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            patient.setFirstName(nombreTf.getText());
            patient.setLastName(apellidosTf.getText());
            patient.setStreet(calleTf.getText());
            patient.setPostalCode(Integer.parseInt(codigoPostalTf.getText()));
            patient.setCity(ciudadTf.getText());
            patient.setBirthday(DateUtil.parse(cumpleTf.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreTf.getText() == null || nombreTf.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (apellidosTf.getText() == null || apellidosTf.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (calleTf.getText() == null || calleTf.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (codigoPostalTf.getText() == null || codigoPostalTf.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(codigoPostalTf.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (ciudadTf.getText() == null || ciudadTf.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (cumpleTf.getText() == null || cumpleTf.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(cumpleTf.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}