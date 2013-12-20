import java.util.Scanner;
public class Problem1
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        
        int NumberofShares = 1;                 //::Inputs
        double PurchasePrice, SellingPrice;     //::from user
        double GrossCost, PurchaseCommission, TotalPurchasePrice;   //Purchase Variables
        double GrossProfit, SellCommission, TotalSellPrice;         //Sale variables
        double ProfitOrLoss;    //Comparison Variable
        
        System.out.println("Welcome to the Stock Commission Calculator");
        
        System.out.println("");
        
        //Get User inputs
        System.out.print("Enter number of shares:\t");
        NumberofShares = scanner.nextInt();
        System.out.print("Enter Purchase Price:\t");
        PurchasePrice = scanner.nextDouble();
        System.out.print("Enter Selling Price:\t");
        SellingPrice = scanner.nextDouble();
        
        //Calculations for Purchasing
        GrossCost = PurchasePrice * NumberofShares;
        PurchaseCommission = GrossCost * .02;
        TotalPurchasePrice = GrossCost + PurchaseCommission;
        
        //Calculations for Selling
        GrossProfit = SellingPrice * NumberofShares;
        SellCommission = GrossProfit * .02;
        TotalSellPrice = GrossProfit - SellCommission;
        
        //Calculate Profit or Loss
        ProfitOrLoss = TotalSellPrice - TotalPurchasePrice;
        
        System.out.println("");
        
        //Purchase Output
        System.out.println("Purchasing Stocks");
        System.out.print("\tNumber of Shares Bought:\t" + NumberofShares + "\n");
        System.out.print("\tPurchase Price per Share:\t" + "$" + PurchasePrice + "\n");
        System.out.print("\tCommission on Purchase:\t\t" + "$" + PurchaseCommission + "\n");
        System.out.print("\tTotal Purchase Price:\t\t" + "$" + TotalPurchasePrice + "\n");
        
        System.out.println("");
        
        //Selling Output
        System.out.println("Selling Stocks");
        System.out.print("\tNumber of Shares Sold:\t\t" + NumberofShares + "\n");
        System.out.print("\tSelling Price per Share:\t" + "$" + SellingPrice + "\n");
        System.out.print("\tCommission on Sale:\t\t" + "$" + SellCommission + "\n");
        System.out.print("\tTotal Selling Price:\t\t" + "$" + TotalSellPrice + "\n");
        
        System.out.println("");
        
        //Profit vs Loss Output
        System.out.println("Profit / Loss on Sale:\t" + "$" + ProfitOrLoss);
        
        
    }
}
