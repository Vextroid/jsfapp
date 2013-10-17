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
import javax.enterprise.context.RequestScoped;
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
@Named(value = "manageATM")
@RequestScoped
public class ManageATM {
    @Resource(mappedName = "jms/messageQue")
    private Queue messageQue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    
    /**
     * Creates a new instance of ManageATM
     */
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
    
    private String cusID;
    private String fName;
    private int depositAmount;
    private int withdrawlAmount;
    private int transferAmount;
    private int otherAccount;
    private String isOk;

    public String getCusID() {
        return cusID;
    }

    public String getfName() {
        return fName;
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

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getWithdrawlAmount() {
        return withdrawlAmount;
    }

    public void setWithdrawlAmount(int withdrawlAmount) {
        this.withdrawlAmount = withdrawlAmount;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public int getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(int otherAccount) {
        this.otherAccount = otherAccount;
    }

    public String getIsOk() {
        return isOk;
    }

    public void setIsOk(String isOk) {
        this.isOk = isOk;
    }
    
    
    public String getAccNum(String id)
    {
     String i = id;
     try{
     PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM DBUSR.CUSTOMER WHERE C_ID =" + id);
     ResultSet result = sqlStatement.executeQuery();
     result.next();
     i = result.getString("ACC_NUM");     
     return i;
    }catch (SQLException sqlException)
     {
      System.out.println("Could not make a deposit.");
      sqlException.printStackTrace();
      return "x";
     }
    }
    
    public void makeDeposit(int balance)
    {
        try{
            //CustomerID
            //Customer First Name
            String acc = this.getAccNum(this.getCusID());
            //int amount = this.getDepositAmount();
            int amount = balance;
            //Getting current amount to use for upcomming equation
            PreparedStatement sqlGetBal = connection.prepareStatement("SELECT * FROM DBUSR.SAVINGS WHERE C_ID =" + this.getCusID());
            ResultSet result = sqlGetBal.executeQuery();
            result.next();
            int currBal = result.getInt("BALANCE");
            
            currBal = currBal + amount;
            
            
                                                                       //UPDATE DBUSR.SAVINGS SET BALANCE = BALANCE+100 WHERE ACC_NUM = 'ROTT2013';
            PreparedStatement sqlStatement = connection.prepareStatement("UPDATE DBUSR.SAVINGS SET BALANCE = ? WHERE ACC_NUM = ? ");
            sqlStatement.setInt(1,amount);
            sqlStatement.setString(2,acc);
            sqlStatement.executeUpdate();
         
          //PreparedStatement sqlStatement2 = connection.prepareStatement("INSERT INTO DBUSR.SAVINGS      (C_ID, ACC_NUM, BALANCE)"       + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);   
//            PreparedStatement inserter = connection.prepareStatement("INSERT INTO DBUSR.TRANSACTIONS (ACC_NUM, AMOUNT, DESCRIPTION)" + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
//          
//          //sqlStatement2.setInt(1, C_ID);
//            inserter.setString(1,acc);
//            inserter.setInt(2, depositAmount);
//            inserter.setString(3,desc);
       
            //inserter.executeUpdate();
          
          //ResultSet result = inserter.getGeneratedKeys();
          //result.next();
          //customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          //System.out.println("Customer" + customer.C_ID + "created.");
          
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not make a deposit.");
                  sqlException.printStackTrace();
                }
    }
    
        public String makeWithdrawl(int balance)
    {
        try{
            String isOK;
            //CustomerID
            //Customer First Name
            String acc = this.getAccNum(this.getCusID());
           //int amount = this.getWithdrawlAmount();
            int amount = balance;
            //Getting current amount to use for upcomming equation
            PreparedStatement sqlGetBal = connection.prepareStatement("SELECT * FROM DBUSR.SAVINGS WHERE C_ID =" + this.getCusID());
            ResultSet result = sqlGetBal.executeQuery();
            result.next();
            int currBal = result.getInt("BALANCE");
            
            currBal = currBal - amount;
            //Withdrawl                                                 //UPDATE DBUSR.SAVINGS SET BALANCE = BALANCE+100 WHERE ACC_NUM = 'ROTT2013';
            PreparedStatement sqlStatement = connection.prepareStatement("UPDATE DBUSR.SAVINGS SET BALANCE = ? WHERE ACC_NUM = ? ");
            sqlStatement.setInt(1,currBal);
            sqlStatement.setString(2,acc);
            sqlStatement.executeUpdate();
            isOk = "True";
            return isOk;
          //PreparedStatement sqlStatement2 = connection.prepareStatement("INSERT INTO DBUSR.SAVINGS      (C_ID, ACC_NUM, BALANCE)"       + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);   
//            PreparedStatement inserter = connection.prepareStatement("INSERT INTO DBUSR.TRANSACTIONS (ACC_NUM, AMOUNT, DESCRIPTION)" + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
//          
//          //sqlStatement2.setInt(1, C_ID);
//            inserter.setString(1,acc);
//            inserter.setInt(2, depositAmount);
//            inserter.setString(3,desc);
       
            //inserter.executeUpdate();
          
          //ResultSet result = inserter.getGeneratedKeys();
          //result.next();
          //customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          //System.out.println("Customer" + customer.C_ID + "created.");
         // ret
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not make a deposit.");
                  sqlException.printStackTrace();
                  isOk = "False";
            return isOk;
                }
    }
    
    public ManageATM() {
    }
}
