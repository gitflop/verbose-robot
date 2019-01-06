package fx.aal.arduapp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase modelo Usuario
 *
 */
public class User {

	private final StringProperty userType;
	private final StringProperty nombre;
	private final StringProperty apellidos;
	private final StringProperty calle;
	private final IntegerProperty codigoPostal;
	private final StringProperty ciudad;
	private final IntegerProperty ID;
	private final StringProperty userName;
	private final StringProperty email;
	// private DataSnap[] DataSnaps = new DataSnap[10];
	// private final ObjectProperty<LocalDate> cumple;

	/**
	 * Constructor por defecto.
	 */
	public User() {
		this(null, null);
	}

	/**
	 * Constructor con datos algunos datos iniciales
	 *
	 * @param nombre
	 * @param apellidos
	 */
	public User(String nombre, String apellidos) {

		this.nombre = new SimpleStringProperty(nombre);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.userName = new SimpleStringProperty("username");
		this.calle = new SimpleStringProperty("some street");
		this.codigoPostal = new SimpleIntegerProperty(1234);
		this.ciudad = new SimpleStringProperty("some city");
		this.ID = new SimpleIntegerProperty(000);
		this.userType = new SimpleStringProperty("noValue");
		this.email = new SimpleStringProperty("noEmail@gmail.com");
		
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String firstName) {
		this.nombre.set(firstName);
	}

	public StringProperty nombreProperty() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos.get();
	}

	public void setApellidos(String lastName) {
		this.apellidos.set(lastName);
	}

	public StringProperty appellidosProperty() {
		return apellidos;
	}

	public String getCalle() {
		return calle.get();
	}

	public void setCalle(String street) {
		this.calle.set(street);
	}

	public StringProperty calleProperty() {
		return calle;
	}

	public int getCodigoPostal() {
		return codigoPostal.get();
	}

	public void setPostalCode(int postalCode) {
		this.codigoPostal.set(postalCode);
	}

	public IntegerProperty postalCodeProperty() {
		return codigoPostal;
	}

	public String getCiudad() {
		return ciudad.get();
	}

	public void setCiudad(String city) {
		this.ciudad.set(city);
	}

	public StringProperty ciudadProperty() {
		return ciudad;
	}

	// public DataSnap[] getDataSnaps() {
	// return DataSnaps;
	// }
	//
	// public void setDataSnaps(DataSnap[] dataSnaps) {
	// DataSnaps = dataSnaps;
	// }
	public int getID() {
		return ID.get();
	}

	public void setID(int id) {
		this.ID.set(id);
	}

	public IntegerProperty IDProperty() {
		return ID;
	}

	public StringProperty userTypeProperty() {
		return userType;
	}

	public String getUserType() {
		return userType.get();
	}

	public void setUserType(String type) {
		this.userType.set(type);
	}

	public StringProperty userNameProperty() {
		return userName;
	}

	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String _userName) {
		this.userName.set(_userName);
	}

	public StringProperty emailProperty() {
		return email;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String _email) {
		this.email.set(_email);
	}

}