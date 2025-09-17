import java.util.Scanner;
public class BankFrontOffice {
    public static void main(String[] args) {
        Bank hdfcBank = new Bank();
        Scanner scn = new Scanner(System.in);

        loop:while (true){
            System.out.println("\nChoose one option");

            System.out.println("1.Open Account");
            System.out.println("2.Account details");
            System.out.println("3.Deposit");
            System.out.println("4.Withdraw");
            System.out.println("5.Balance Enquiry");
            System.out.println("6.Transfer Money");
            System.out.println("7.Display All Accounts");
            System.out.println("8.Exit");

            System.out.println("Enter Option:");
            int option = scn.nextInt();
            scn.nextLine();

            try{
                switch (option){
                    case 1:{
                        hdfcBank.openAccount();
                        break;
                    }
                    case 2:{
                        System.out.println("Enter account number:");
                        long accNum =  scn.nextLong();
                        scn.nextLine();
                        hdfcBank.displayAccount(accNum);
                        break;
                    }
                    case 3:{
                        System.out.println("Enter account number:");
                        long accNum = scn.nextLong();
                        scn.nextLine();

                        System.out.println("Enter deposit amount:");
                        double amt = scn.nextDouble();
                        scn.nextLine();

                        hdfcBank.deposit(accNum,amt);
                        break;
                    }
                    case 4:{
                        System.out.println("Enter account number:");
                        long accNum = scn.nextLong();
                        scn.nextLine();

                        System.out.println("Enter withdraw amount:");
                        double amt = scn.nextDouble();
                        scn.nextLine();

                        hdfcBank.withdraw(accNum,amt);
                        break;
                    }
                    case 5:{
                        System.out.println("Enter account number");
                        long accNum = scn.nextLong();
                        scn.nextLine();
                        hdfcBank.balanceEnquiry(accNum);
                        break;
                    }
                    case 6:{
                        System.out.println("Enter source account number");
                        long srcAccNum = scn.nextLong();
                        scn.nextLine();

                        System.out.println("Enter destination account number");
                        long destinAccNum = scn.nextLong();
                        scn.nextLine();

                        System.out.println("Enter amount");
                        double amt = scn.nextDouble();
                        scn.nextLine();

                        hdfcBank.transferMoney(srcAccNum,destinAccNum,amt);
                        break;
                    }
                    case 7:{
                        System.out.println("The available account in Bank");
                        System.out.println(hdfcBank);//hdfcBank.toString()
                        break;
                    }
                    case 8:{
                        System.out.println("ThankYou, Have a good day ");
                        break loop;
                    }
                    default:{
                        System.out.println("Invalid option ");
                    }

                }
            }catch(IllegalArgumentException e){
                System.out.println("Error :"+e.getMessage());
            }

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println();
            }
        }
    }
}
