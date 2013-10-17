/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.Date;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Vextroid
 */

    @Named(value = "custoemrBean")
    @RequestScoped
public class Customer {
    @Resource(mappedName = "jms/messageQue")
    private Queue messageQue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    /**
     * Creates a new instance of Customer
     */
    
    private int id;
    private String fName;
    private String lName;
    private Date dob;
    private String address;
    
    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private void sendJMSMessageToMessageQue(String messageData) {
        context.createProducer().send(messageQue, messageData);
    }
    
    
    
}
