package fx.aal.arduapp.model;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of Users. This is used for saving the
 * list of Users to XML.
 *
 */
@XmlRootElement(name = "Users")
public class PatientListWrapper {

    private List<User> Users;

    @XmlElement(name = "User")
    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        this.Users = users;
    }

    public void setUser(User user){
    	if(this.Users == null ){

    	}else{
    	this.Users.add(user);
    	}
   }

}