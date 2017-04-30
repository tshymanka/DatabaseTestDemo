package skeleton;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class StepDefs {
	private Connection mySqlConn = null;
	private ResultSet rs = null;
	
    @Given("^User is successfully connected to the database$")
    public void getDbConnection () throws Throwable {
    	String url = "jdbc:mysql://localhost:3306/adv_works";
        String user = "root";
        String password = "password";

        mySqlConn = DriverManager.getConnection(url, user, password);
        if (mySqlConn == null) {
        	throw new Exception("Can't connect to selected database");
        }
    }
        
       @When("^User validates Person Hub for business keys duplicates$")
        public void getPersonHubs () throws Throwable {
            try {
            	String sqlTemplate = "select PersonID, count(*) as cnt from Hub_PersID " +
   	                 "group by PersonID " +
   	                 "having count(*) > ?;";
        	 
        	   PreparedStatement stmt = mySqlConn.prepareStatement(sqlTemplate);
        	   stmt.setInt(1, 1);
        	   rs = stmt.executeQuery();
            } catch(Exception e) {
            	mySqlConn.close();
            	throw (e);
            }
        }
        
        @Then("^0 records returned from the database$")
        public void ResultSet_Checked () throws Throwable {  
        	assertThat(rs.next(), equalTo(false));  
        }
        
        @After
        public void closeConnection() throws SQLException {
        	mySqlConn.close();
        }
}