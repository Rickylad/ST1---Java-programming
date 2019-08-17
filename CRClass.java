/**
 * Class Name: CRClass
 * Description: CRClass class code for Assignment Part1
 * Author: Ricky Liu
 * Version: 1.0
 * Last edited: 28/04/2019
 */
public class CRClass // a class holds custcode, Sales Person, First Recovery and fees, and calculating the fees here
{
    String sCustCode, sSalesPerson, sFirstRecovery;
    double dCommission, dFee, dCFee;

    public CRClass(String sCustCode, String sFirstRecovery, double dCommission, double dFee, String sSalesPerson)
    {
        this.sCustCode = sCustCode;
        this.dCommission = dCommission;
        this.dFee = dFee;
        this.sSalesPerson = sSalesPerson;
        this.sFirstRecovery = sFirstRecovery;

        if(this.sCustCode.equalsIgnoreCase("codc"))
        {
            this.dCommission = 0;
            this.dFee =0;
            this.dCFee =0;
        }
        else if(this.sFirstRecovery.equalsIgnoreCase("none"))
        {
            this.dCFee = (this.dFee - 2900000.0) * this.dCommission * 0.01;
        }
        else if(this.sFirstRecovery.equalsIgnoreCase("sea "))
        {
            this.dCFee = (this.dFee - 265000.0) * this.dCommission * 0.01;
        }
        else
        {
            this.dCFee = this.dFee * this.dCommission * 0.01;
        }
    }
}
