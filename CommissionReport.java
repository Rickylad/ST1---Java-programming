/**
 * Class Name: CommissionReport
 * Description: CommissionReport class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.1
 * Last edited: 29/04/2019
 */
import java.util.*;
import java.io.*;
import java.text.*;
public class CommissionReport
{   
    ArrayList<LaunchClass> launchlist = new ArrayList<LaunchClass>();
    public int iFileLength;
    public String sInput;
    public double dTotalAzumi, dTotalChung, dTotalJames;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    public CommissionReport()
    {
        dTotalAzumi = 0;
        dTotalChung = 0;
        dTotalJames = 0;
        launchlist.clear();
        CustomerList cl = new CustomerList();
        ServiceList sl = new ServiceList();
        Scanner selectFile = new Scanner(System.in);
        System.out.println("Please Enter File Name:");
        sInput = selectFile.nextLine();

        while(sInput.equals("")) // deal with null input
        {
            System.out.println("Error! Please input the the right file name: ");
            sInput = selectFile.nextLine();
        }

        sInput = sInput.substring(0, 1).toUpperCase().concat(sInput.substring(1)); // transform the input file name
        System.out.println("Reading..." + sInput);        
        printFrame(40);
        System.out.println("Commission Report For File: " + sInput);
        printFrame(60);
        sInput = "./TestFiles/"+sInput; // plus the file path
        readFile(sInput); // read file method        
        boolean bCorrectFormat = false;
        
        for(int a = 0; a < launchlist.size(); a++) // check the file format
        {
            if(cl.findCustomer(launchlist.get(a).sCustCode) !=null && sl.findService(launchlist.get(a).sCode) != null)
            {
                boolean bDateCheck = checkDate(launchlist.get(a).sDate);
                if(bDateCheck == true) // if all three data are correct, then print out
                {
                    bCorrectFormat = true;
                    String sSalesPerson = cl.findCustomer(launchlist.get(a).sCustCode).salesPerson;
                    double dCommission = sl.findService(launchlist.get(a).sCode).commission;
                    double dFee = sl.findService(launchlist.get(a).sCode).fee;
                    String sFirstRecovery = sl.findService(launchlist.get(a).sCode).recoveryAsString(sl.findService(launchlist.get(a).sCode).firstRecovery);

                    CRClass crc = new CRClass(launchlist.get(a).sCustCode, sFirstRecovery, dCommission, dFee, sSalesPerson);
                    
                    System.out.printf("%10s%10s%7s%,14.0f%,10.0f%8s\n", launchlist.get(a).sDate, launchlist.get(a).sCustCode, launchlist.get(a).sCode, 
                        crc.dFee, crc.dCFee, crc.sSalesPerson);
                    if(crc.sSalesPerson.equalsIgnoreCase("Azumi"))
                    {
                        dTotalAzumi = dTotalAzumi + crc.dCFee;
                    }
                    if(crc.sSalesPerson.equalsIgnoreCase("Chung"))
                    {
                        dTotalChung = dTotalChung + crc.dCFee;
                    }
                    if(crc.sSalesPerson.equalsIgnoreCase("James"))
                    {
                        dTotalJames = dTotalJames + crc.dCFee;
                    }
                }
                if(bDateCheck == false)
                {
                    System.out.println("Error>>invalid line syntax (Date)");
                }
            }
            if(cl.findCustomer(launchlist.get(a).sCustCode) != null)
            {
                if(sl.findService(launchlist.get(a).sCode) == null)
                {
                    boolean bDateCheck = checkDate(launchlist.get(a).sDate);
                    if(bDateCheck == true)
                    {
                        System.out.println("Error>>invalid line syntax (Service)");
                    }
                    if(bDateCheck == false)
                    {
                        System.out.println("Error>>invalid line syntax (Service)(Date)");
                    }
                }
            }
            if(cl.findCustomer(launchlist.get(a).sCustCode) ==null)
            {
                if(sl.findService(launchlist.get(a).sCode) != null)
                {
                    boolean bDateCheck = checkDate(launchlist.get(a).sDate);
                    if(bDateCheck == true)
                    {
                        System.out.println("Error>>invalid line syntax (Customer)");
                    }
                    if(bDateCheck == false)
                    {
                        System.out.println("Error>>invalid line syntax (Customer)(Date)");
                    }
                }
                if(sl.findService(launchlist.get(a).sCode) == null)
                {
                    boolean bDateCheck = checkDate(launchlist.get(a).sDate);
                    if(bDateCheck == true)
                    {
                        System.out.println("Error>>invalid line syntax (Customer)(Service)");
                    }
                    if(bDateCheck == false)
                    {
                        System.out.println("Error>>invalid line syntax (All Wrong!)");
                    }
                }
            }
        }
        if(bCorrectFormat == true)
        {
            printFrame(60);
            calculateTotal(dTotalChung, "Chung");
            calculateTotal(dTotalAzumi, "Azumi");
            calculateTotal(dTotalJames, "James");
            printFrame(60);
            System.out.printf("%s%,13.0f\n", "Total Commission:", dTotalChung + dTotalAzumi + dTotalJames);
            printFrame(60);
        }
    }

    public void printFrame(int i) // print the frame
    {
        for(int ii = 0;ii<i;ii++)
        {
            System.out.print("-");
        }
        System.out.println();
    }

    public void readFile(String sFileName) // read file method
    {
        iFileLength = 0;
        try
        {
            Scanner inputFile = new Scanner(new FileReader(sFileName));
            while(inputFile.hasNextLine())
            {
                String[] gotFile = inputFile.nextLine().split(",");
                LaunchClass launch = new LaunchClass(gotFile[0], gotFile[1], gotFile[2]);
                launchlist.add(launch);
                iFileLength++;
            }
            inputFile.close();
        }
        catch(Exception e)
        {
            System.out.println("Not Found In The Directory!");
            new LaunchReport();
        }
    }

    public boolean checkDate(String sDate) // check date formate
    {
        boolean bDateCheck = true;
        try
        {
            dateformat.setLenient(false);
            dateformat.parse(sDate);
        }
        catch(ParseException pe)
        {
            bDateCheck = false;
        }
        return bDateCheck;
    }

    public void calculateTotal(double dCFee, String sSalesPerson) // print out the total commission fee for each sals person
    {
        System.out.printf("%s%6s%,10.0f\n", "Commission for", sSalesPerson, dCFee);
    }
}
