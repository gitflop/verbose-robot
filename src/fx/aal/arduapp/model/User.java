package fx.aal.arduapp.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase modelo Usuario
 *
 *
 */
public class User {

    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty calle;
    private final IntegerProperty codigoPostal;
    private final StringProperty ciudad;
    private final ObjectProperty<LocalDate> cumple;

    /**
     * Default constructor.
     */
    public User() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.nombre = new SimpleStringProperty(firstName);
        this.apellidos = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.calle = new SimpleStringProperty("some street");
        this.codigoPostal = new SimpleIntegerProperty(1234);
        this.ciudad = new SimpleStringProperty("some city");
        this.cumple = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return nombre.get();
    }

    public void setFirstName(String firstName) {
        this.nombre.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return nombre;
    }

    public String getLastName() {
        return apellidos.get();
    }

    public void setLastName(String lastName) {
        this.apellidos.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return apellidos;
    }

    public String getStreet() {
        return calle.get();
    }

    public void setStreet(String street) {
        this.calle.set(street);
    }

    public StringProperty streetProperty() {
        return calle;
    }

    public int getPostalCode() {
        return codigoPostal.get();
    }

    public void setPostalCode(int postalCode) {
        this.codigoPostal.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return codigoPostal;
    }

    public String getCity() {
        return ciudad.get();
    }

    public void setCity(String city) {
        this.ciudad.set(city);
    }

    public StringProperty cityProperty() {
        return ciudad;
    }

    
    public LocalDate getBirthday() {
        return cumple.get();
    }
    

    public void setBirthday(LocalDate birthday) {
        this.cumple.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return cumple;
    }
}