/**
 * Class Name: Customer
 * Description: Customer class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.0
 * Last edited: 28/04/2019
 */
public class Customer
{
    public String custCode=""; // The customer code 
    public String custName=""; // The customer business name 
    public String custContact=""; // The customer contact person name 
    public String custAddress1=""; // The customer address line 1 
    public String custAddress2=""; // The customer address line 2
    public String custPcode=""; // The customer post code 
    public String salesPerson=""; // The customer post code 
    
    // Partial constructor to keep it on 1 line and re-use code from MT1
    public Customer(String custCode, String salesPerson)
    {
    this.custCode=custCode.trim();
    this.salesPerson=salesPerson;  
    }

    
    // Full proper constructor to keep it on 1 line and re-use code from MT1
    public Customer(String custCode, String custName, String custContact, String custAddress1,
                    String custAddress2, String custPcode, String salesPerson)
    {
    this.custCode=custCode.trim();
    this.custName=custName; // The customer business name 
    this.custContact=custContact; // The customer contact person name 
    this.custAddress1=custAddress1; // The customer address line 1 
    this.custAddress2=custAddress2; // The customer address line 2
    this.custPcode=custPcode;
    this.salesPerson=salesPerson;  
    }
    
    public String toString() 
    {
        String retv =custCode;
        retv = String.format("|%6s|%-35s|%-20s|%-20s|%-5s|%-5s|", retv, custName, custContact, custAddress1, custPcode, salesPerson)+
        String.format("\n%65s%s", "|", custAddress2);
        return retv;
    }

}
