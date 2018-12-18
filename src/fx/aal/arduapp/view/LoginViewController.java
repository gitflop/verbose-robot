package fx.aal.arduapp.view;


import fx.aal.arduapp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginViewController {


	    @FXML
	    private Label lbBienvenida;
	    @FXML
	    private TextField tfpass;
	    @FXML
	    private TextField tfuser;



	    // Reference to the main application.
	    private MainApp mainApp;




		public LoginViewController() {

		}

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	   public void setMainApp(MainApp mainApp) {
	       this.mainApp = mainApp;

	   }



}

