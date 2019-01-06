package fx.aal.arduapp.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Dbase {

	// los datos como una Obsevable list de Usuarios .
    private ObservableList<User> userData = FXCollections.observableArrayList();
    File fileAccounts;

public Dbase(){

	File file = new File("C://Users//Messiks//Documents//UEM 2018//Proyecto Informatica I//archivos//accounts.xml");
	loadUserDataFromFile(file);
	this.fileAccounts = file;


}
    /**
     *Validar cuenta del usuario
     * **/
public boolean verifyUser(String userName) {
	String str1;


boolean hasMatch = false;
	for (User user : getuserData()){
		str1 = user.getNombre();
		if (str1.equals(userName))
		{
			hasMatch = true;
			break;
		}

	}
	return hasMatch;

// if(userName.equals(str1) || userName.equals(str2))
// {
//	 	return true;
// }else{
//	 	return false;
// }

}

/**
 * Returns the data as an observable list of Persons.
 * @return
 */
public ObservableList<User> getuserData() {
    return this.userData;
}



/**
 * Saves the current person data to the specified file.
 *
 * @param file
 */
public void saveUserDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PatientListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PatientListWrapper wrapper = new PatientListWrapper();
        wrapper.setUsers(userData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        //setUserFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());
        alert.showAndWait();
    }
}
/**
 * Guarda los datos de la lista de usuarios en el fichero accounts.xml.
 *
 * @param file
 */
public void saveUserToAccountFile() {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PatientListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PatientListWrapper wrapper = new PatientListWrapper();
        wrapper.setUsers(userData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, fileAccounts);

        // Save the file path to the registry.
        //setUserFilePath(file);
    } catch (Exception e) { // Captura cualquier exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + fileAccounts.getPath());
        alert.showAndWait();
    }
}

/**
 * Guarda un usuario nuevo en en el fichero accounts.xml.
 *
 * @param file
 */
public void saveUserToAccountFile(User _user) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PatientListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PatientListWrapper wrapper = new PatientListWrapper();
        userData.add(_user);
        wrapper.setUsers(userData);

        // Marshalling and saving XML to the file.
       m.marshal(wrapper, fileAccounts);

        // Save the file path to the registry.
        //setUserFilePath(file);
    } catch (Exception e) { // Captura cualquier exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + fileAccounts.getPath());
        alert.showAndWait();
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
        JAXBContext context = JAXBContext
                .newInstance(PatientListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PatientListWrapper wrapper = (PatientListWrapper) um.unmarshal(file);

       // userData.clear();
        userData.addAll(wrapper.getUsers());

        // Save the file path to the registry.
        //setUserFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}



}
