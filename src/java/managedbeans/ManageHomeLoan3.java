/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Vextroid
 */
@Named(value = "homeloanBeanThree")
@RequestScoped
public class ManageHomeLoan3 {

    /**
     * Creates a new instance of ManageHomeLoan3
     */
    
    @ManagedProperty(value="#{homeloanBeanFour.email}")
    private String email;
    @ManagedProperty(value="#{homeloanBeanFour.phoneNum}")
    private int phoneNum;
    @ManagedProperty(value="#{homeloanBeanFour.address}")
    private String address;
    
    
    public ManageHomeLoan3() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
