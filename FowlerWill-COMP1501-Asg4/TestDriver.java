public class TestDriver
{
    public static void main(String args[])
    {
        Inventory testInventoryItem = new Inventory(12345, "Widgets", 30, 5.50, 8.00);
        System.out.print(testInventoryItem);
        
        // TEST LE GETTERS
        
        System.out.print("\n\nGetters Testing Results\n-----------------------\n");
        System.out.println(testInventoryItem.getItemNo());
        System.out.println(testInventoryItem.getItemName());
        System.out.println(testInventoryItem.getOnHand());
        System.out.println(testInventoryItem.getCommitted());
        System.out.println(testInventoryItem.getOnorder());
        System.out.println(testInventoryItem.getUnitCost());
        System.out.println(testInventoryItem.getSellPrice());
        
        // TEST LE SETTERS
        
        testInventoryItem.setItemNo(54321);
        testInventoryItem.setItemName("Thingamajigs");
        testInventoryItem.setUnitCost(100.00);
        testInventoryItem.setSellPrice(150.00);
        
        System.out.print("\nSetters Testing Results\n-----------------------\n" + testInventoryItem);
        
        //TEST LE INVENTORY PROCCESSING METHODS
        
        System.out.print("\n\nInventory Processing Method Testing Results\n-----------------------\n");
        System.out.println(testInventoryItem.orderItems(20));
        System.out.println(testInventoryItem.receiveItems(15));
        System.out.println(testInventoryItem.returnItems(5));
        System.out.println(testInventoryItem.custOrder(12));
        System.out.println(testInventoryItem.shipItems(6));
        System.out.println(testInventoryItem.calcInvValue());
        
        System.out.print(testInventoryItem);
        
    }
}
