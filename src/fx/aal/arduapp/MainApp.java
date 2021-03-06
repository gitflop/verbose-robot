package fx.aal.arduapp;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fx.aal.arduapp.model.Dbase;
import fx.aal.arduapp.model.PatientListWrapper;
import fx.aal.arduapp.model.User;
import fx.aal.arduapp.view.LoginViewController;
import fx.aal.arduapp.view.PatientEditDialogController;
import fx.aal.arduapp.view.PatientOverviewController;
import fx.aal.arduapp.view.SignUpViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	// private static final Dbase DbaseCtrl = null;
	private Stage primaryStage;
	private BorderPane rootLayout;
	// private Dbase DbaseCtrl;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<User> userData = FXCollections.observableArrayList();
	private Dbase MainDbaseCtrl;

	/**
	 * Constructor
	 */
	public MainApp() {

		MainDbaseCtrl = new Dbase();
		/**
		 * crear controlador base de datos
		 */
		//
	}

	/**
	 * Devuelve los datos como una lista Observable de Pacientes.
	 *
	 * @return
	 */
	public ObservableList<User> getuserData() {
		return userData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Ambient Assited Living System");

		initRootLayout();

		// showPatientOverview();
		showLoginView();
	}

	/**
	 * Inicializa el Layout raiz.
	 */
	public void initRootLayout() {
		try {

			// loadUserDataFromFile(file);

			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra la vista Login dentro del Layout raiz.
	 */
	public void showLoginView() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginView.fxml"));
			AnchorPane loginView = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(loginView);

			// Give the controller access to the main app.
			LoginViewController controller = loader.getController();
			Dbase DbaseCtrl = new Dbase();
			controller.setMainApp(this);
			controller.setDataSource(DbaseCtrl);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra la vista Login dentro del Layout raiz.
	 */
	public void showSignUpView() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SignUpView.fxml"));
			AnchorPane SignUpView = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(SignUpView);

			// Give the controller access to the main app.
			SignUpViewController controller = loader.getController();
		//	Dbase DbaseCtrl = new Dbase();
			controller.setMainApp(this);
			controller.setDataSource(this.MainDbaseCtrl);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra la vista general de los pacientes dentro del Layout raiz.
	 *
	 */
	public void showPatientOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/patientOverview.fxml"));
			AnchorPane patientOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(patientOverview);

			// Give the controller access to the main app.
			PatientOverviewController controller = loader.getController();
			controller.setMainApp(this);
			Dbase DbaseCtrl = new Dbase();

			controller.setDataSource(DbaseCtrl);
			// controller.loadData();

			// controller.setDataSource(DbaseCtrl);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 *
	 * @return
	 */
	public File getUserFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 *
	 * @param file
	 *            the file or null to remove the path
	 */
	public void setUserFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("Arduapp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("Arduapp");
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPatientEditDialog(User person) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PatientEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editar Paciente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PatientEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPatient(person);
			Dbase DbaseCtrl = new Dbase();
			controller.setDataSource(DbaseCtrl);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 *
	 * @param file
	 */
	public void loadUserDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(PatientListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			PatientListWrapper wrapper = (PatientListWrapper) um.unmarshal(file);

			// userData.clear();
			userData.addAll(wrapper.getUsers());

			// Save the file path to the registry.
			// setUserFilePath(file);

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 *
	 * @param file
	 */
	public void saveUserDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(PatientListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PatientListWrapper wrapper = new PatientListWrapper();
			wrapper.setUsers(userData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			// setUserFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	/**
	 * Returns the main stage.
	 *
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}