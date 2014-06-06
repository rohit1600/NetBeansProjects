/**
 * Author: Rohit Rajagopal Last Modified: Mar 19, 2012
 * 
 * This program is to test the account program with a different main method
 * The program can be run multiple times without getting an error
 * The changes are consistent in that the changes are not made until all the 
 * transactions are successful.
 */
package databasedemoproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBClient {

    private static final String ACCOUNT1 = "123";
    private static final String NAME1 = "Cristina Couglin";
    private static final double AMOUNT1 = 10000.0;
    private static final String ACCOUNT2 = "124";
    private static final String NAME2 = "Mary Klopot";
    private static final double AMOUNT2 = 14000.0;
    private static final String ACCOUNT3 = "125";
    private static final String NAME3 = "Mike McCarthy";
    private static final double AMOUNT3 = 100;
    private static final double TRANSFER_AMOUNT = 1.00;

    /**
     * main method
     * establishes connection with the account table in the database
     * creates accounts with the class variables
     * initiates transfer using the transfer method
     */
    public static void main(String args[]) throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con =
                DriverManager.getConnection("jdbc:derby://localhost:1527/Account", "rohit", "rohit");
        // set up three test accounts
        createAccounts(con);
// move some money around from a to b to c to a
        transfer(ACCOUNT1, ACCOUNT2, TRANSFER_AMOUNT, con);
        transfer(ACCOUNT2, ACCOUNT3, TRANSFER_AMOUNT, con);
        transfer(ACCOUNT3, ACCOUNT1, TRANSFER_AMOUNT, con);
    }

    /**
     * method to create records in the accoutn table using account objects
     * takes connection object as an argument
     */
    private static void createAccounts(Connection con) throws Exception {
        try {
            // Create three new accounts after removing the old
// versions if any.
            Account account1 = new Account();
            account1.delete(ACCOUNT1, con);
            account1.create(ACCOUNT1, NAME1, AMOUNT1, con);
            System.out.println(account1);
            Account account2 = new Account();
            account2.delete(ACCOUNT2, con);
            account2.create(ACCOUNT2, NAME2, AMOUNT2, con);
            System.out.println(account2);
            Account account3 = new Account();
            account3.delete(ACCOUNT3, con);
            account3.create(ACCOUNT3, NAME3, AMOUNT3, con);
            System.out.println(account3);
            System.out.println("Acoounts created");
        } catch (Exception e) {
            System.out.println("Exception thrown");
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * method to transfer amount from one account to another in a consistent
     * manner. The commit method is called only after all the intermediate steps
     * are fulfilled
     */
    private static void transfer(String accountIDFrom, String accountIDTo, double amount, Connection con) {
        try {
            // transfer amount from a to b
            con.setAutoCommit(false);
            Account accountFrom = new Account();
            accountFrom.read(accountIDFrom, con);
            Account accountTo = new Account();
            accountTo.read(accountIDTo, con);
            accountFrom.setAmount(accountFrom.getAmount() - amount);
            accountTo.setAmount(accountTo.getAmount() + amount);
            accountFrom.update(con);
            accountTo.update(con);
            System.out.println("Funds Transfererred");
            System.out.println("From account " + accountFrom);
            System.out.println("To account " + accountTo);
            con.commit();
        } catch (Exception e) {
            try {
                System.out.println("Transaction aborted - Rolling back changes.");
                con.rollback();
            } catch (Exception re) {
                System.out.println("Problem doing rollback. Exception " + re);
            }
            e.printStackTrace();
        }
    }
}