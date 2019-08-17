/**
 * Class Name: Service
 * Description: Service class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.0
 * Last edited: 28/04/2019
 */
public class Service
{
    public String code; // the service code
    public String shortDescription; // short description
    public double fee; //in dollars
    public double commission; // comission rate as percentage 0.1 = 0.1%
    public int firstRecovery; // 0 = land 1 = sea  2=none
    
    // constants for firstRecovery (if you know about enumerated types feel free to change it to enum type)
    public static final int recLand =0;
    public static final int recSea = 1;
    public static final int recNone =2;
    
    public Service(String code, String shortDescription, double fee, double commission, int firstRecovery)
    {
    this.code=code.trim(); // the service code
    this.shortDescription=shortDescription; // short description
    this.fee=fee; //in dollars
    this.commission=commission; // comission rate as percentage 0.1 = 0.1%
    this.firstRecovery=firstRecovery; 
    }
    
    public static String recoveryAsString(int firstRecovery)
    {
     if (firstRecovery==recLand) return "Land"; 
     if (firstRecovery==recSea ) return "Sea ";
     if (firstRecovery==recNone) return "None";   
     return "Error> Invalid firstRecovery";
    }
 
    public String toString() 
     {
      String retv = code;
      retv = String.format("|%6s|%-14s|", retv, shortDescription)+
      String.format("%,12.0f|", fee)+
      String.format("%5.2f%%|", commission)+String.format("%5s|", recoveryAsString(firstRecovery));
      return retv;
     }
    
}
