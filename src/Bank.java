import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;


public class Bank {
    private BankAccount[] accounts ;
    private  int size;
    private AtomicLong accNumGenerator;
    private Scanner scanner;

    public Bank(){
        accounts = new BankAccount[10];
        size = 0;
        scanner = new Scanner(System.in);

        try{
            BufferedReader accNumReader = new BufferedReader(new FileReader("accNumSeq.txt"));
            long accNumSeq = Long.parseLong(accNumReader.readLine());
            accNumGenerator = new AtomicLong(accNumSeq);
        }catch (FileNotFoundException e){
            System.out.println("accNumSeq.txt is not found");
           // e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    } //Bank() close

    private void pause(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void openAccount(){
        long accNum = accNumGenerator.incrementAndGet();
        System.out.println("Enter Account Holder Name");
        String accHName = scanner.nextLine();
        System.out.println("Enter Balance");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Bank Account object creation started");
        pause();

        BankAccount account =new BankAccount(accNum,accHName,balance);
        System.out.println("BankAccount object is created");
        pause();

        accounts[size++] = account;
        System.out.println("BankAccount object is stored in Bank");
        pause();

        //Saving accnum in file
        try{
            FileWriter fw = new FileWriter("accNumSeq.txt");
            fw.write(""+accNum);
            fw.flush();
        }catch (FileNotFoundException e){
            System.out.println("accNumSeq.txt file is not found");
        }catch (IOException e){
            e.printStackTrace();
        }
    }//openAccount() method closed

    private BankAccount getAccount(long accNum) throws IllegalArgumentException{
        if(accNum<=0){
            throw new IllegalArgumentException("Account number can't be -VE number & ZERO");
        }
        System.out.println("Searching for given accNum object");
        pause();
        //Linear Search algorithm
        for(int i = 0;i<size;i++){
            BankAccount account = accounts[i];
            if(account.getAccNum() == accNum)
                return account;
        }
        return null;
    }

    private boolean amountZeroOrNegative(double amt){
        return amt<=0?true:false;
    }
    private boolean amountGreaterThanBalance(BankAccount account,double amt){
        return amt>account.getBalance()?true:false;
    }

    public void deposit(long accNum,double amt)throws IllegalArgumentException{
        System.out.println("deposit operation start");
        pause();

        //retriving thE BankAccount object of the given account number
        BankAccount account = getAccount(accNum);

        //Checking acc no &balance valid or not
        if(account == null){
            throw new IllegalArgumentException("Account is not found with the given details");
        }
        if (amountZeroOrNegative(amt)) {
            throw new IllegalArgumentException("Amount can't be -VE or ZERO");
        }

        //depositing given amount in the given account
        account.deposit(amt);
        System.out.println("Cash RS"+amt+"is credited to your account");
        pause();
    }

    public void withdraw(long accNum,double amt)throws IllegalArgumentException{
        System.out.println("withdraw operation started");
        pause();

        //retriving thE BankAccount object of the given account number
        BankAccount account = getAccount(accNum);

        //Checking acc no &balance valid or not
        if(account == null){
            throw new IllegalArgumentException("Account is not found with the given details");
        }
        if (amountZeroOrNegative(amt)) {
            throw new IllegalArgumentException("Amount can't be -VE or ZERO");
        }
        if (amountGreaterThanBalance(account, amt)) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        //withdrawing given amount in the given account
        account.withdraw(amt);
        System.out.println("Cash RS"+amt+"is debited from your account");
        pause();
    }

    public void balanceEnquiry(long accNum)throws IllegalArgumentException{
        System.out.println("balanceEnquiry operation started");
        pause();

        //retriving thE BankAccount object of the given account number
        BankAccount account = getAccount(accNum);

        //Checking acc no &balance valid or not
        if(account == null){
            throw new IllegalArgumentException("Account is not found with the given details");
        }

        System.out.println("Current Balance");
        account.currentBalance();
        pause();
    }

    public void transferMoney(long srcAccNum,long destinAccNum,double amt)throws IllegalArgumentException{
        System.out.println("transferMoney operation started");
        pause();

        withdraw(srcAccNum,amt);
        deposit(destinAccNum,amt);
        System.out.println("transferMoney operation end");
        pause();
    }

    public void updateAccount(long accNum)throws IllegalArgumentException{
        BankAccount account = getAccount(accNum);
        if(account == null){
            throw new IllegalArgumentException("Given account number is wrong");
        }

        String option = "N";
        do {
          System.out.println("Choose one option to update your account data");
          System.out.println("1.Name");
          System.out.println("2.Mobile");
          System.out.println("3.Email");
          System.out.println("4.Address");

          System.out.println("Enter Option");
          int Option = scanner.nextInt();
          scanner.nextLine();

          switch (Option){
              case 1:{
                  System.out.println("Enter new Name");
                  account.setAccHName(scanner.nextLine());
                  break;
              }
              case 2:{
                  System.out.println("Enter new Mobile number");
                  //  account.setMobile(scanner.nextLong());scanner.nextLine();
                  break;
              }
              case 3:{
                  System.out.println("Enter new Email");
                  //  account.setEmail(scanner.nextLine());
                  break;
              }
              case 4:{
                  System.out.println("Enter new Address");
                  //  account.setAddress(scanner.nextLine());
                  break;
              }
              default:
                  System.out.println("Invalid Option");
          }

          System.out.println("Do you want update other field ? (Y/N)");
          option = scanner.nextLine();
        }while (option.equalsIgnoreCase("Y"));

    }//updateAccount() method closed

    public void closeAccount(long accNum)throws IllegalArgumentException{
        System.out.println("closeAccount operation start");
        pause();
        if(accNum<=0){
            throw new IllegalArgumentException("Account no can't be -VE & Zero");
        }
        System.out.println("Searching for given accountNumber object");
        pause();

        for(int i = 0;i<size;i++){
            BankAccount account = accounts[i];
            if(account.getAccNum() == accNum) {
                for (int j = i; j < size - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[size - 1] = null;
                size--;
                System.out.println("Account is deleted");
                pause();
                return;
            }
        }
        throw new IllegalArgumentException("Account is not found with the given details");
    }

    public void displayAccount(long accNum)throws IllegalArgumentException{
        System.out.println("displayAccount operation started ");
        pause();

        BankAccount account = getAccount(accNum);
        if(account == null){
            throw new IllegalArgumentException("Account is not found with the given details");
        }
        System.out.println("\nAccount details");
        System.out.println(account);
    }

    public String toString(){
        if (size==0){
            System.out.println("No records found");
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<size;i++){
            sb.append(accounts[i]);
        }
        return sb.toString();
    }
}
