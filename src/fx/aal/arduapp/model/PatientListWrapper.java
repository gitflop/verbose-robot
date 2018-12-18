package fx.aal.arduapp.model;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of Users. This is used for saving the
 * list of Users to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "Users")
public class PatientListWrapper {

    private List<User> Users;

    @XmlElement(name = "User")
    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> Users) {
        this.Users = Users;
    }
}