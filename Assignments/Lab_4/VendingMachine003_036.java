import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachine003_036{
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            VendingMachine v1 = new VendingMachine(getInventory(), getMenu());
            while(true) {
                System.out.println("Would you like to order some beverages?");
                System.out.println("Beverages in this vending machine: " + getMenu());
                System.out.println("1. Yes.");
                System.out.println("2. No.");
                int x = scanner.nextInt();
                if(x == 2)
                    break;

                System.out.println("Insert Coins");
                v1.insertCoin();
                System.out.println("1. Order Beverage");
                System.out.println("2. Cancel Transaction");
                x = scanner.nextInt();
                if(x == 1)
                {
                    System.out.println("Which beverage to order? " + getMenu());
                    System.out.print("Insert String: ");
                    String order = scanner.next();
                    order = order.toUpperCase(Locale.ROOT);
                    Hashtable<String, Double> hash = (Hashtable<String, Double>)getMenu();
                    if(hash.containsKey(order)) {
                        if (v1.orderBeverage(order)) {
                            v1.returnChange();
                            v1.dispenseBeverage();
                            v1.takeItem();
                        } else {
                            v1.cancelTransaction();
                        }
                    }
                    else
                    {
                        System.out.println("This item is not served in this vending machine.");
                        v1.cancelTransaction();
                    }
                }
                else
                {
                    v1.cancelTransaction();
                }
                v1.changeState(new IdleState(v1));
            }
        }
    }

    private static Dictionary<String, Double> getMenu() {
        Dictionary<String, Double> menu = new Hashtable<>();
        menu.put("CAPPUCCINO", 1.5);
        menu.put("COFFEE", 1.2);
        return menu;
    }

    private static Dictionary<String, Integer> getInventory() {
        Dictionary<String, Integer> inventory = new Hashtable<>();
        inventory.put("CAPPUCCINO", 5);
        inventory.put("COFFEE", 5);
        return inventory;
    }
}

class VendingMachine {
    MachineState currentState;
    double cashCollected;
    Dictionary<String, Integer> inventory = new Hashtable<>();
    Dictionary<String, Double> menu = new Hashtable<>();
    Dictionary<Integer, Integer> customerInserted = new Hashtable<>();
    String currentlyMaking;

    VendingMachine(Dictionary<String, Integer> inventory, Dictionary<String, Double> menu)
    {
        this.currentState = new IdleState(this);
        currentlyMaking = "";
        this.inventory = inventory;
        this.menu = menu;
        this.cashCollected = 0;
        clearCashBox();
    }

    public void clearCashBox() {
        this.customerInserted.put(10, 0);
        this.customerInserted.put(20, 0);
        this.customerInserted.put(50, 0);
    }

    public void changeState(MachineState state){
        this.currentState = state;
    }
    public void insertCoin(){
        currentState.insertMoney();
    }
    public void cancelTransaction(){
        this.currentState = new CancelTransactionState(this);
        currentState.ejectMoney();
    }
    public boolean orderBeverage(String choice){
        return currentState.orderBeverage(choice);
    }
    public void returnChange(){
        currentState.returnChange();
    }
    public void dispenseBeverage(){
        currentState.dispenseBeverage();
    }
    public void takeItem(){
        currentState.takeItem();
    }

}

abstract class MachineState {
    VendingMachine vendingMachine = null;
    void insertMoney()
    {
        throw new RuntimeException("Wrong State");
    }
    boolean orderBeverage(String choice)
    {
        throw new RuntimeException("Wrong State");
    }
    void returnChange()
    {
        throw new RuntimeException("Wrong State");

    }
    void ejectMoney()
    {
        throw new RuntimeException("Wrong State");
    }

    void dispenseBeverage()
    {
        throw new RuntimeException("Wrong State");
    }
    void takeItem()
    {
        throw new RuntimeException("Wrong State");
    }

    String calculateChange(double amount)
    {
        String change = "";
        if(amount > 0) {
            int cnt50 = 0;
            int cnt20 = 0;
            int cnt10 = 0;

            int cust50 = this.vendingMachine.customerInserted.get(50);
            int cust20 = this.vendingMachine.customerInserted.get(20);
            int cust10 = this.vendingMachine.customerInserted.get(10);


            int amountt = (int) (Math.round(amount * 10 ) );


            while(amountt - 5 >= 0 && cust50 > 0) {
                cnt50++;
                amountt -= 5;
                cust50--;
            }

            while(amountt - 2 >= 0 && cust20 > 0) {
                cnt20++;
                amountt -= 2;
                cust20--;
            }

            while(amountt - 1 >= 0 && cust10 > 0) {
                cnt10++;
                amountt -= 1;
                cust10--;
            }


            while (amountt >= 5) {
                amountt -= 5;
                cnt50++;
            }
            while (amountt >= 2) {
                amountt -= 2;
                cnt20++;
            }
            while (amountt >= 1) {
                amountt -= 1;
                cnt10++;
            }

            if (cnt50 > 0) {
                change += "50 cents : " + cnt50;
            }
            if (cnt20 > 0) {
                change += "\n";
                change += "20 cents : " + cnt20;
            }
            if (cnt10 > 0) {
                change += "\n";
                change += "10 cents : " + cnt10 + "\n";
            }
        }
        else
        {
            change = "There is no change.";
        }
        return change;

    }

}

class IdleState extends MachineState{
    Scanner scanner = new Scanner(System.in);
    IdleState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        this.vendingMachine.clearCashBox();
        while(true)
        {
            System.out.println("Do you want to insert coins?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            int x = scanner.nextInt();
            if(x == 2)
                break;
            System.out.println("Insert Coins");
            System.out.println("1 - 10 cent");
            System.out.println("2 - 20 cent");
            System.out.println("3 - 50 cent");
            x = scanner.nextInt();
            switch(x) {
                case 1:
                    vendingMachine.cashCollected += .1;
                    int cnt = vendingMachine.customerInserted.get(10);
                    cnt += 1;
                    vendingMachine.customerInserted.put(10, cnt);
                    break;
                case 2:
                    vendingMachine.cashCollected += .2;
                    cnt = vendingMachine.customerInserted.get(20);
                    cnt += 1;
                    vendingMachine.customerInserted.put(20, cnt);
                    break;
                case 3:
                    vendingMachine.cashCollected += .5;
                    cnt = vendingMachine.customerInserted.get(50);
                    cnt += 1;
                    vendingMachine.customerInserted.put(50, cnt);
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;

            }
            System.out.println("Cash inserted so far: " + Math.round(this.vendingMachine.cashCollected * 100.0) / 100.0);
        }
    }

    @Override
    public boolean orderBeverage(String choice) {
        if(this.vendingMachine.inventory.get(choice) > 0)
        {
            double price = this.vendingMachine.menu.get(choice);
            if (vendingMachine.cashCollected >= price)
            {
                this.vendingMachine.inventory.put(choice, this.vendingMachine.inventory.get(choice)-1);
                System.out.println("Currently making: " + choice);
                this.vendingMachine.currentlyMaking = choice;
                vendingMachine.changeState(new DispenseChangeState(this.vendingMachine));
            }
            else
            {
                System.out.println("Not enough money inserted. Returning money.");
                this.vendingMachine.changeState(new CancelTransactionState(this.vendingMachine));
                return false;
            }
        }
        else
        {
            System.out.println("No more " + choice + " is available, cancelling transaction.");
            this.vendingMachine.changeState(new CancelTransactionState(this.vendingMachine));
            return false;
        }
        return true;
    }

}

class DispenseBeverageState extends MachineState{
    DispenseBeverageState(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    void dispenseBeverage() {
        System.out.println(this.vendingMachine.currentlyMaking + " is being made.");
        System.out.println(this.vendingMachine.currentlyMaking + " is served. Take it.");
        this.vendingMachine.changeState(new WaitState(this.vendingMachine));
    }
}

class DispenseChangeState extends MachineState{
    DispenseChangeState(VendingMachine vendingMachine)
    {
        // System.out.println("The vending machine is in dispensing change state.");
        this.vendingMachine = vendingMachine;
    }

    @Override
    void returnChange() {
        System.out.println("Change is being dispensed.");
        double price =  this.vendingMachine.menu.get(this.vendingMachine.currentlyMaking);
        System.out.println(this.calculateChange(
                this.vendingMachine.cashCollected - price));
        this.vendingMachine.cashCollected = 0;
        this.vendingMachine.clearCashBox();
        this.vendingMachine.changeState(new DispenseBeverageState(this.vendingMachine));
    }
}

class CancelTransactionState extends MachineState{
    CancelTransactionState(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    void ejectMoney() {
        if(this.vendingMachine.cashCollected > 0) {
            System.out.println("Inserted Money "+ Math.round(this.vendingMachine.cashCollected * 100.0) / 100.0 + " is being ejected.");
            System.out.println(super.calculateChange(this.vendingMachine.cashCollected));
            this.vendingMachine.clearCashBox();
            this.vendingMachine.cashCollected = 0;
            this.vendingMachine.changeState(new IdleState(this.vendingMachine));
        } else {
            System.out.println("Nothing to return");
        }
    }
}

class WaitState extends MachineState{
    WaitState(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    void takeItem()
    {
        System.out.println("Item has been taken");
        this.vendingMachine.changeState(new IdleState(this.vendingMachine));
    }
}



