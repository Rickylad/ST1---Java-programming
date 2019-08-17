

/**
 * Class Name: LaunchReport
 * Description: LaunchReport class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.1
 * Last edited: 29/04/2019
 */
import java.util.*;
import java.io.*;
import java.text.*;
public class LaunchReport 
{
    ArrayList<LaunchClass> launchlist = new ArrayList<LaunchClass>();
    public int iFileLength;
    public String sDirectory;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    public LaunchReport()
    {
        String sInput;
        launchlist.clear();
        CustomerList cl = new CustomerList();
        ServiceList sl = new ServiceList();
        Scanner selectFile = new Scanner(System.in);
        System.out.println("Please Enter File Name (eg. mand1.txt): ");
        sInput = selectFile.nextLine();
        
        while(sInput.equals(""))// deal with null input
        {
            System.out.println("Error! Please input the the right file name: ");
            sInput = selectFile.nextLine();
        }
        
        sInput = sInput.substring(0, 1).toUpperCase().concat(sInput.substring(1));// transform the input file name
        System.out.println("Reading..." + sInput);        
        printFrame(30);
        System.out.println("Launch Report for: " + sInput);
        sInput = "./TestFiles/"+sInput;// plus the file path
        readFile(sInput);// read file method 
        printFrame(30);
        
        for(int a = 0; a < launchlist.size(); a++) // check the file format
        {
            if(cl.findCustomer(launchlist.get(a).sCustCode) !=null && sl.findService(launchlist.get(a).sCode) != null)
            {
                boolean bDateCheck = checkDate(launchlist.get(a).sDate);
                if(bDateCheck == true) // if all three data are correct, then print out
                {
                    System.out.printf("%5s%10s%15s\n", launchlist.get(a).sCustCode, launchlist.get(a).sCode, launchlist.get(a).sDate);
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
        printFrame(30);
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

    public void printFrame(int i) // print the frame
    {
        for(int ii = 0;ii<i;ii++)
        {
            System.out.print("-");
        }
        System.out.println();
    }

    public boolean checkDate(String sDate) // check the date format
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
}
