package fx.aal.arduapp.view;


import fx.aal.arduapp.MainApp;
import fx.aal.arduapp.model.Dbase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class LoginViewController {

	    @FXML
	    private Label lbBienvenida;
	    @FXML
	    private TextField tfpass;
	    @FXML
	    private TextField tfuserName;
	    private Stage dialogStage;
	    // Referencia a la clase raiz mainApp
	    private MainApp mainApp;
	    private Dbase dbaseCtrl;

		public LoginViewController() {


		}

	    /**
	     * Inicializa la clase controlador. Este metodo se llama automaticamente
	     * despues de cargar el ficher fxml
	     */
	    @FXML
	    private void initialize() {

	    }

	   public void setMainApp(MainApp mainApp) {
	       this.mainApp = mainApp;

	   }

	   public void setDataSource (Dbase dbaseCtrl){
		   this.dbaseCtrl = dbaseCtrl;

	   }
	   /**
	    *  Se llama cuando se pulsa "LOGIN"
	    */
	   @FXML
	   private void handleLoginBt() {
		 //  dbaseCtrl.verifyUser(tfuserName.getText());
			if(	dbaseCtrl.verifyUser(tfuserName.getText()))
			{
				mainApp.showPatientOverview();
			}else{
	    	// Show the error message.
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.initOwner(dialogStage);
           alert.setTitle("Invalid Fields");
           alert.setHeaderText("Please correct invalid fields");
           alert.setContentText(tfuserName.getText());
           alert.showAndWait();
			}
	   }

	   /**
	    *  Se llama cuando se pulsa "Crear Cuenta"
	    */
	   @FXML
	   private void handleCrearCuentaBt() {

		   	mainApp.showSignUpView();


			}

	   }




