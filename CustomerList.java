/**
 * Class Name: CustomerList
 * Description: CustomerList class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.0
 * Last edited: 28/04/2019
 */
public class CustomerList
{
    Customer[] custList;

    public CustomerList()
    {
        custList = new Customer[7];

        custList[0] = new Customer("ESAA", "Europe Systems Alternative Agency", "Jean-Claude Junxer", "23 Razor Road",
            "Belconnen ACT", "2617", "Azumi");
        custList[1] = new Customer("NASHA", "National Air Space Hash Agency", "Jimmy Briden", "2 Mashup Drive",
            "Bruce ACT", "2617", "Chung");
        custList[2] = new Customer("ASA", "Aussie Space Agency", "Megan Clock", "Flat 31/a, Bax Units",
            "Stix St Marble Bar", "6760", "Azumi");
        custList[3] = new Customer("TICK", "Tick Incorporated", "Mark Watson", "87 Race drive",
            "Bathurst", "2795", "James");
        custList[4] = new Customer("BINC", "Byer private space Incorporated", "Marillyn Hewson", "212 webly drive",
            "Canowindra NSW", "2804", "James");
        custList[5] = new Customer("CODC", "Corporate space Trust", "Zhang Chen", "212 Scorch drive", 
            "Beltana SA", "5730", "Azumi");
        custList[6] = new Customer("MARZ","The Mars Gen Inc","Cool Dude","26 O’Conner’s Road",
            "Werribee South VIC","3030","James");
    }

    public Customer findCustomer(String custCode)
    {
        //simple array search

        for(int i=0; i< 7 ; i++)
        {
            if (custList[i].custCode.compareToIgnoreCase(custCode.trim())==0) return custList[i];
        }
        return null;
    }

    public void printCustomer(Customer[] custList)
    {
        printCustomerFrame(30); // print out customer information
        System.out.println("\n Audit Service - Customers");
        System.out.print("+");
        printCustomerFrame(6);
        printCustomerFrame(35);
        printCustomerFrame(20);
        printCustomerFrame(20);
        printCustomerFrame(5);
        printCustomerFrame(5);
        System.out.println();
        for(int ii = 0; ii < custList.length;ii++)
        {
            System.out.println(custList[ii]);
        }
        System.out.print("+");
        printCustomerFrame(6);
        printCustomerFrame(35);
        printCustomerFrame(20);
        printCustomerFrame(20);
        printCustomerFrame(5);
        printCustomerFrame(5);
        System.out.printf("\n\n");
    }

    public void printCustomerFrame(int i)
    {
        for(int ii = 0;ii<i;ii++)
        {
            System.out.print("-");
        }
        System.out.print("+");
    }

}
