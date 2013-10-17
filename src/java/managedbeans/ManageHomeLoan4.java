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
@Named(value = "homeloanBeanFour")
@RequestScoped
public class ManageHomeLoan4 {

    /**
     * Creates a new instance of ManageHomeLoan4
     */
    //FROM HOMELOAN ONE BEAN
    //@ManagedProperty(value="#{atmBean.cusID}")
    private String title;
    //@ManagedProperty(value="#{atmBean.cusID}")
    private String fName;
    //@ManagedProperty(value="#{atmBean.cusID}")
    private String lName;
    //@ManagedProperty(value="#{atmBean.cusID}")
    private String address;
    //@ManagedProperty(value="#{atmBean.cusID}")
    private Date date;
    
    //FROM HOMELOAN TWO
    //@ManagedProperty(value="#{homeloanBeanFour.currJob}")
    private String currJob;
    //@ManagedProperty(value="#{homeloanBeanFour.salaryPerYear}")
    private int salaryPerYear;
    
    //FROM HOMELOAN THREE
    //@ManagedProperty(value="#{homeloanBeanFour.email}")
    private String email;
    //@ManagedProperty(value="#{homeloanBeanFour.phoneNum}")
    private int phoneNum;
    //@ManagedProperty(value="#{homeloanBeanFour.address}")
    //private String address;
    
    //FROM HOMELOAN FOUR
    
    
    public ManageHomeLoan4() {
    }
}
