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
@Named(value = "homeloanBeanTwo")
@RequestScoped
public class ManageHomeLoan2 {

    /**
     * Creates a new instance of ManageHomeLoan2
     */
    public String getCurrJob() {
        return currJob;
    }

    public void setCurrJob(String currJob) {
        this.currJob = currJob;
    }

    public int getSalaryPerYear() {
        return salaryPerYear;
    }

    public void setSalaryPerYear(int salaryPerYear) {
        this.salaryPerYear = salaryPerYear;
    }

    
    
    
    @ManagedProperty(value="#{homeloanBeanFour.currJob}")
    private String currJob;
    @ManagedProperty(value="#{homeloanBeanFour.salaryPerYear}")
    private int salaryPerYear;
    //@ManagedProperty(value="#{homeloanBeanFour.lName}")
    //private String lName;
    //@ManagedProperty(value="#{homeloanBeanFour.address}")
    //private String address;
    //@ManagedProperty(value="#{homeloanBeanFour.date}")
    //private Date date;
    public ManageHomeLoan2() {
    }
}
