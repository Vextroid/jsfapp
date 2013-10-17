/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.Date;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Vextroid
 */
@Named(value = "homeloanBeanOne")
@RequestScoped
public class ManageHomeLoan {

    /**
     * Creates a new instance of ManageHomeLoan
     */
    @ManagedProperty(value="#{homeloanBeanFour.title}")
    private String title;
    @ManagedProperty(value="#{homeloanBeanFour.fName}")
    private String fName;
    @ManagedProperty(value="#{homeloanBeanFour.lName}")
    private String lName;
    @ManagedProperty(value="#{homeloanBeanFour.address}")
    private String address;
    @ManagedProperty(value="#{homeloanBeanFour.date}")
    private Date date;
    
    
    public ManageHomeLoan() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





}
