/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.sql.DataSource;

/**
 *
 * @author Vextroid
 */
@Named(value = "manageLogin")
@ApplicationScoped
public class ManageLogin {
@Resource(mappedName = "jms/messageQue")
    private Queue messageQue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    /**
     * Creates a new instance of ManageLogin
     */
    //
    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;
    
    @PostConstruct
    public void initialize(){
        try{
            connection = dataSource.getConnection();
        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    /**
     *
     */
    @PreDestroy
     public void close()
    {
        try{
            connection.close();
        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
    }
    
    @ManagedProperty(value="#{atmBean.fName}")
    private String firstName;
    @ManagedProperty(value="#{atmBean.cusID}")
    private String customerID;
    private String id;
    private String extra;
    private String isOk;
    private String fName ="w";
    
    public ManageLogin() {
    }

    public Queue getMessageQue() {
        return messageQue;
    }

    public void setMessageQue(Queue messageQue) {
        this.messageQue = messageQue;
    }

    public JMSContext getContext() {
        return context;
    }

    public void setContext(JMSContext context) {
        this.context = context;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getIsOk() {
        return isOk;
    }

    public void setIsOk(String isOk) {
        this.isOk = isOk;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    
    
    public String validLogin()
    {
        id = this.getCustomerID();
        firstName = this.getFirstName();

        try{
        PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM DBUSR.CUSTOMER WHERE C_ID =" + id);
        ResultSet result = sqlStatement.executeQuery();
        result.next();
        fName = result.getString("FIRST_NAME");
        }
        catch(SQLException sqlException)
        {
         System.out.println("Could not find customer.");
         sqlException.printStackTrace(); 
         isOk = "false";    
         return isOk;
        }
        
         if(fName.equals(firstName))
        {
         isOk ="true";
         return isOk;
         //return "conformation.xhtml";
        }
        else
        {
         isOk = "false";    
         return isOk;
         //return "index.xhtml";
        }               
        //return "String";
    }
    
    
    
}
