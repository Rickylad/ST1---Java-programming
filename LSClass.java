
/**
 * Class Name: LSClass
 * Description: LSClass class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.0
 * Last edited: 28/04/2019
 */
import java.util.*;
import java.io.*;
public class LSClass
{
    String sDate, sCustCode, sCode, sSalesPerson;

    public LSClass(String sDate, String sCustCode, String sCode, String sSalesPerson)
    {
        this.sDate = sDate;
        this.sCustCode = sCustCode;
        this.sCode = sCode;
        this.sSalesPerson = sSalesPerson;

    }

    public String getDate()
    {
        return sDate; 
    }

    public String getCustCode()
    {
        return sCustCode;
    }

    public String getCode()
    {
        return sCode;
    }

    public String getSalesPerson()
    {
        return sSalesPerson;
    }
}
