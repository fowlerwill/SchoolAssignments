import java.text.*;
public class Inventory
{
    private DecimalFormat dec2 = new DecimalFormat("#.##");
    private int itemNo, onHand, committed, onOrder;
    private double unitCost, sellPrice;
    private String itemName;
    
    public Inventory(int initItemNo, String initItemName, int initOnHand, double initUnitCost, double initSellPrice)
    {
        itemNo      = initItemNo;
        itemName    = initItemName;
        onHand      = initOnHand;
        unitCost    = initUnitCost;
        sellPrice   = initSellPrice;
    }
    
    //------------------------------------------------------
    // ACCESSORS
    //------------------------------------------------------
    
    public int getItemNo()
    {
        return itemNo;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public int getOnHand()
    {
        return onHand;
    }
    
    public int getCommitted()
    {
        return committed;
    }
    
    public int getOnorder()
    {
        return onOrder;
    }
    
    public double getUnitCost()
    {
        return unitCost;
    }
    
    public double getSellPrice()
    {
        return sellPrice;
    }
    
    //------------------------------------------------------
    // MUTATORS
    //------------------------------------------------------
    
    public void setItemNo(int newItemNo)
    {
        itemNo = newItemNo;
    }
    
    public void setItemName(String newItemName)
    {
        itemName = newItemName;
    }
    
    public void setUnitCost(double newUnitCost)
    {
        unitCost = newUnitCost;
    }
    
    public void setSellPrice(double newSellPrice)
    {
        sellPrice = newSellPrice;
    }
    
    //------------------------------------------------------
    // INVENTORY PROCCESSING METHODS
    //------------------------------------------------------
    
    public String orderItems(int unitsOrdered)
    {
        onOrder = onOrder + unitsOrdered;
        return "\n" + unitsOrdered + " Units added to " + this.getItemName()+ " onOrder";
    }
    
    public String receiveItems(int unitsReceived)
    {
        onHand = onHand + unitsReceived;
        onOrder = onOrder - unitsReceived;
        return unitsReceived + " " + this.getItemName() + " Units received.\n" +
                "\t" + onHand + "\tUnits onHand\n" + 
                "\t" + onOrder + "\tUnits onOrder";
    }
    
    public String returnItems(int unitsReturned) //Can add error checking to make sure units returned cannot be more than onHand
    {
        onHand = onHand - unitsReturned;
        return unitsReturned + " " + this.getItemName() + " Units Returned";
    }
    
    public String custOrder(int custUnitsOrdered)
    {
        onHand = onHand - custUnitsOrdered;
        committed = committed + custUnitsOrdered;
        return custUnitsOrdered + " " + this.getItemName() + " Units Ordered by customer\n" +
                "\t" + onHand + "\tUnits onHand\n" + 
                "\t" + committed + "\tUnits commited";
    }
    
    public String shipItems(int unitsShipped)
    {
        committed = committed - unitsShipped;
        return unitsShipped + " " + this.getItemName() + " Units Shipped";
    }
    
    public double calcInvValue()
    {
        return (onHand + committed) * unitCost;
    }
    
    public String toString()
    {
        return  "Inventory Number:\t"   + itemNo +
                "\nInventory Name:\t\t"     + itemName + 
                "\nAmount on Hand:\t\t"     + onHand + 
                "\nAmount Committed:\t"   + committed + 
                "\nAmount on Order:\t"    + onOrder +
                "\nUnit Cost:\t\t"        + "$" + dec2.format(unitCost) + //ADD DECIMAL FORMAT
                "\nSelling Price:\t\t"    + "$" + dec2.format(sellPrice) +
                "\nValue of item:\t\t"    + "$" + dec2.format(calcInvValue());
    }  
}
