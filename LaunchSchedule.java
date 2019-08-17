
/**
 * Class Name: LaunchSchedule
 * Description: LaunchSchedule class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.1
 * Last edited: 29/04/2019
 */
import java.util.*;
import java.io.*;
import java.text.*;
public class LaunchSchedule
{
    ArrayList<LaunchClass> launchlist = new ArrayList<LaunchClass>();
    ArrayList<LSClass> lsclasslist = new ArrayList<LSClass>();
    ArrayList<LSRepeatedClass> lsrepeatedlist = new ArrayList<LSRepeatedClass>();
    public int iFileLength;
    public String sInput, sYear;
    public boolean bCheckFile;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yearformat = new SimpleDateFormat("yyyy");

    public LaunchSchedule()
    {
        launchlist.clear();
        lsclasslist.clear();
        lsrepeatedlist.clear();
        CustomerList cl = new CustomerList();
        ServiceList sl = new ServiceList();
        Scanner selectFile = new Scanner(System.in);
        System.out.println("Please Enter File Name:");
        sInput = selectFile.nextLine();

        while(sInput.equals(""))// deal with null input
        {
            System.out.println("Error! Please input the the right file name: ");
            sInput = selectFile.nextLine();
        }

        sInput = sInput.substring(0, 1).toUpperCase().concat(sInput.substring(1)); // transform the input file name
        System.out.println("Reading..." + sInput);
        String sDirectory = "./TestFiles/"+sInput;// plus the file path
        bCheckFile = readFile(sDirectory); // read file method 

        while(bCheckFile == false) // deal with wrong input file name
        {
            System.out.println("Not Found In The Directory! Please input the the right file name:");
            sInput = selectFile.nextLine();
            sInput = sInput.substring(0, 1).toUpperCase().concat(sInput.substring(1));
            System.out.println("Reading..." + sInput);
            sDirectory = "./TestFiles/"+sInput;
            readFile(sDirectory);
        }

        boolean bCheckYear = true;

        do // deal with wrong input year
        {
            try
            {
                System.out.println("Please Input Year (eg 2019): ");
                sYear = selectFile.nextLine();
                if(sYear.length() == 4)
                {
                    yearformat.setLenient(false);
                    yearformat.parse(sYear);
                    bCheckYear = true;
                }
                if(sYear.length() != 4)
                {
                    System.out.println("Error! Please input the the right year!\n");
                    bCheckYear = false;
                }

            }
            catch(Exception e)
            {
                System.out.println("Error! Please input the the right year!\n");
                bCheckYear = false;
            }
        }while(bCheckYear == false);

        printFrame(40);
        System.out.println("Launch Schedule for: " + sYear + " " + "File: " + sInput);
        printFrame(40);
        boolean bYearNotExist = false;

        for(int a = 0; a < launchlist.size(); a++)
        {
            if(cl.findCustomer(launchlist.get(a).sCustCode) !=null && sl.findService(launchlist.get(a).sCode) != null)
            {
                boolean bDateCheck = checkDate(launchlist.get(a).sDate);                
                if(bDateCheck == true) // if all the three data are correct, store them in the class LSClass
                {
                    String sSalesPerson = cl.findCustomer(launchlist.get(a).sCustCode).salesPerson;                    
                    LSClass lsclass = new LSClass(launchlist.get(a).sDate, launchlist.get(a).sCustCode, launchlist.get(a).sCode, sSalesPerson);

                    if(lsclass.sDate.indexOf(sYear) != -1) // find the match year, then store the corresponding data into arraylist
                    {
                        lsclasslist.add(lsclass);
                        bYearNotExist = true;
                    }
                }
                
            }

        }

        Date[] dates = new Date[lsclasslist.size()];
        try
        {
            for(int i =0; i<lsclasslist.size();i++)
            {
                dates[i] = dateformat.parse(lsclasslist.get(i).sDate); // store dates into array
            }
            Arrays.sort(dates); // sort the dates
        }
        catch(ParseException pe)
        {
            System.out.println("Not a problem I think.");
        }

        boolean bGotDate = false;

        for(int ii =0; ii<dates.length;ii++) // find the corresponding data to each date
        {
            for(int j =0;j<dates.length;j++)
            {
                try{
                    if(dates[ii].equals(dateformat.parse(lsclasslist.get(j).sDate))) // bug: if there are same dates, will be both detected and print out times                    
                    {
                        LSRepeatedClass lsrepeated = new LSRepeatedClass(lsclasslist.get(j).sDate, lsclasslist.get(j).sCustCode, lsclasslist.get(j).sCode, 
                                lsclasslist.get(j).sSalesPerson); // ordered arraylist but includes repeated data
                        lsrepeatedlist.add(lsrepeated);
                        bGotDate = true;
                    }
                }
                catch(ParseException pe)
                {
                    System.out.println("Not a problem I think.");
                }
            }
        }
        if(bGotDate == true)
        {
            for(int i = 0;i<lsrepeatedlist.size() -1;i++) // removes duplicates
            {
                for(int j = lsrepeatedlist.size() -1;j>i;j--)
                {
                    if(lsrepeatedlist.get(j).sDate.equals(lsrepeatedlist.get(i).sDate) 
                    && lsrepeatedlist.get(j).sCustCode.equals(lsrepeatedlist.get(i).sCustCode)
                    && lsrepeatedlist.get(j).sCode.equals(lsrepeatedlist.get(i).sCode))
                    {
                        lsrepeatedlist.remove(j);
                    }
                }
            }
            for(LSRepeatedClass lsrepeated : lsrepeatedlist)
            {
                System.out.printf("%10s%10s%7s%8s\n", lsrepeated.sDate, lsrepeated.sCustCode, lsrepeated.sCode, 
                    lsrepeated.sSalesPerson);
            }

        }
        if(bYearNotExist == false)
        {
            System.out.println("No launch for this year!");
        }
        printFrame(40);
    }

    public void printFrame(int i)// print the frame
    {
        for(int ii = 0;ii<i;ii++)
        {
            System.out.print("-");
        }
        System.out.println();
    }

    public boolean readFile(String sFileName)// read file method
    {
        iFileLength = 0;
        bCheckFile = true;
        try
        {
            Scanner inputFile = new Scanner(new FileReader(sFileName));
            while(inputFile.hasNextLine())
            {
                String[] gotFile = inputFile.nextLine().split(",");
                LaunchClass launch = new LaunchClass(gotFile[0], gotFile[1], gotFile[2]);
                launchlist.add(launch);
                iFileLength++;
                bCheckFile = true;
            }
            inputFile.close();
        }
        catch(Exception e)
        {
            bCheckFile = false;
        }
        return bCheckFile;
    }

    public boolean checkDate(String sDate) // check date format
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
