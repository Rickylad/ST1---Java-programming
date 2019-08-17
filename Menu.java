/**
 * Class Name: Menu
 * Description: Menu class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.0
 * Last edited: 28/04/2019
 */
import java.util.*;
public class Menu
{
    public String sMenuOpt;
    public Menu() 
    {
        System.out.print("\u000c");
        Scanner input = new Scanner(System.in);
        do
        {
            System.out.printf("\nWelcome to SpaceZ Marketing Menu\n");
            System.out.printf("A - Audit Report\n");
            System.out.printf("L - Launch Report\n");
            System.out.printf("C - Commission Report\n");
            System.out.printf("S - Launch Schedule\n");
            System.out.printf("E - Exit\n");
            System.out.printf("\nSelect Option:\n");          
            sMenuOpt = input.nextLine();
            while(sMenuOpt.equals("")) // check for empty input
            {
                System.out.println("Please enter the right option: ");
                sMenuOpt = input.nextLine();
            }
            sMenuOpt = sMenuOpt.substring(0, 1); // only check for the first input letter
            System.out.printf("You selected: %s\n",sMenuOpt.toUpperCase());
            if(sMenuOpt.compareToIgnoreCase("A")==0) // input a to print audit report
            {
                CustomerList cl = new CustomerList();
                cl.printCustomer(cl.custList);
                ServiceList sl = new ServiceList();
                sl.printService(sl.servList);
            }

            else if(sMenuOpt.compareToIgnoreCase("L")==0) // input l to go to launch report class
            {
                new LaunchReport();
            }

            else if(sMenuOpt.compareToIgnoreCase("C")==0) // input c to go to Commission Report class
            {
                new CommissionReport();
            }

            else if(sMenuOpt.compareToIgnoreCase("S")==0) // input l to go to Launch Schedule class
            {
                new LaunchSchedule();
            }
            else if(sMenuOpt.compareToIgnoreCase("E")==0) // input l to go to exit the program
            {
                System.out.printf("\nEnding System...\n");
                System.exit(1);
            }
            else
            {
                System.out.println("Error: Please enter the right option!"); // whatever other inputs apart from these options will give an error
            }

        }while(sMenuOpt.compareToIgnoreCase("E")!=0); 
    }

}
