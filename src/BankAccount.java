import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class BankAccount {
    private static String bankName;
    private static String branchName;
    private static String ifsc;

    private long accNum;
    private String accHName;
    private double balance;

    static {
        out.println("\n BankAccount class is loaded");
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){}

        out.println("SV memory allocated with default values");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){}
        out.println("Reading static variables values from file and initializing them");

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }

        try{
            //Connection to file
            BufferedReader fileReader = new BufferedReader(new FileReader("bankDetails.txt"));

            //Reading values from file and storing in static variables
            bankName = fileReader.readLine();
            branchName  = fileReader.readLine();
            ifsc = fileReader.readLine();

            out.println("Static variables are initialized with file values\n");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}

        }catch (FileNotFoundException e){
            out.println("Error : bankDetails.txt file is not found");
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
    }//static block end

    public BankAccount(long accNum,String accHName,double balance){
        out.println("NSV's memory allocated with default values");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        out.println("NSV's are being initialized with given values\n ");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}

        this.accNum = accNum;
        this.accHName = accHName;
        this.balance = balance;
        out.println("NSV's are initialized with given object values");

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e0){}
    }//Constructor closed

    public static  void setBankName(String bankName){
        BankAccount.bankName=bankName;
    }
    public static String getBankName(){
        return bankName;
    }

    public static  void setBranchName(String branchName){
        BankAccount.branchName=branchName;
    }
    public static String getBranchName(){
        return branchName;
    }

    public static void ifsc(String ifsc){
        BankAccount.ifsc = ifsc;
    }
    public static String getIfsc(){
        return ifsc;
    }

    public long getAccNum(){
        return accNum;
    }

    public void setAccHName(String accHName){
        this.accHName = accHName;
    }
    public String getAccHName(){
        return accHName;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amt){
        this.balance = this.balance+amt;
    }

    public void withdraw(double amt){
        this.balance = this.balance-amt;
    }

    public void currentBalance(){
        out.println(balance);
    }

    public String toString(){
        return ("\n Bank name:"+bankName)+"\n"+
                ("Branch name:"+branchName)+ "\n"+
                ("IFSC:"+ifsc)+"\n"+
                ("Acc no:"+accNum)+"\n"+
                ("Acc Holder name:"+accHName)+"\n"+
                ("Balance:"+balance);
    }
}
