package fx.aal.arduapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import fx.aal.arduapp.MainApp;
import fx.aal.arduapp.model.Dbase;
import fx.aal.arduapp.model.User;

public class PatientOverviewController {
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

	// Referencia al mainApp y base de datos.
	private Dbase dbase;
	private MainApp mainApp;

	/**
	 * The constructor. El constructor se llama antes que el metodo intialize().
	 */
	public PatientOverviewController() {

	}

	/**
	 * Inicializa la clase controlador. Este metodo es invocado automaticamente
	 * despues de cargar el fichero fxml.
	 */
	@FXML
	private void initialize() {

		// Inicializar la tabla con Nombre y Apellidos del paciente
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().appellidosProperty());

		// limpiar los detalles del paciente.
		showPatientDetails(null);

		// Escuchar cambios de seleccion, y muestra los detalles de la persona
		// cuando se cambia.
		patientTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPatientDetails(newValue));
	}

	/**
	 * Se invoca por la mainApp para devolverse una referencia asi misma
	 *
	 * @param mainApp
	 */
	public void setDataSource(Dbase dbaseCtrl) {
		this.dbase = dbaseCtrl;
		// Add observable list data to the table
		patientTable.setItems(dbase.getuserData());

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Abre el ventana con "about us".
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ArduApp: Una aplicacion para Ambient Assited Living");
		alert.setHeaderText("About: ");
		alert.setContentText("Author: Grupo 6 ");

		alert.showAndWait();
	}

	/**
	 * Cierra la aplicacion.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	/**
	 * Rellena todos los campos sobre la paciente. Si la persona especificada es
	 * null, el texto se borra.
	 *
	 * @param user the person or null
	 */
	private void showPatientDetails(User user) {
		if (user != null) {
			// Rellena las etiquetas con datos del objeto persona.
			nombrelb.setText(user.getNombre());
			apellidoslb.setText(user.getApellidos());
			callelb.setText(user.getCalle());
			codigoPostallb.setText(Integer.toString(user.getCodigoPostal()));
			ciudadlb.setText(user.getCiudad());
			// cumplelb.setText(DateUtil.format(user.getBirthday()));

		} else {
			// Si el Paciente es null, limpiar el texto de las etiquetas.
			nombrelb.setText("");
			apellidoslb.setText("");
			callelb.setText("");
			codigoPostallb.setText("");
			ciudadlb.setText("");
			// cumplelb.setText("");
		}
	}

	/**
	 * Invocada cuando se pulse "Deletebtn".
	 */
	@FXML
	private void handleDeletePatient() {
		int selectedIndex = patientTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			patientTable.getItems().remove(selectedIndex);

			// Guardamos los cambios en el archivo de cuentas
			dbase.saveUserToAccountFile();
		} else {
			// Nada seleccionado.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Sin Seleccion");
			alert.setHeaderText("Ninguna persona seleccionada");
			alert.setContentText("Selecciona un persona de la tabla.");

			alert.showAndWait();
		}
	}

	/**
	 * Se llama cuando el Usuario hace click en "NEW". Abre el dialog
	 * para añadir los datos del nuevo paciente.
	 */
	@FXML
	private void handleNewPatient() {
		User tempPerson = new User();
		boolean okClicked = mainApp.showPatientEditDialog(tempPerson);
		if (okClicked) {

			// Añadimos el usuario nuevo a la lista
			dbase.getuserData().add(tempPerson);
			// Salvamos los datos en el archivo para los usuarios.
			dbase.saveUserToAccountFile();

			
			
		}
	}

	/**
	 * Invocada cuando se pulsa el "BOTON EDITAR". Abre una ventana con los
	 * detalled del paciente seleccionado
	 */
	@FXML
	private void handleEditPatient() {
		User selectedPerson = patientTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPatientEditDialog(selectedPerson);
			if (okClicked) {
				showPatientDetails(selectedPerson);
				// Salvamos los datos en el archivo para los usuarios.
				dbase.saveUserToAccountFile();
			}

		} else {
			// Nada seleccionado.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Sin Seleccion");
			alert.setHeaderText("Ninguna persona seleccionada");
			alert.setContentText("Selecciona un persona de la tabla.");

			alert.showAndWait();
		}
	}

	public void loadData() {
		// Añade un observable list a la tabla
		patientTable.setItems(dbase.getuserData());

	}

}