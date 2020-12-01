import java.util.ArrayList;

public class OrderedCustomer extends Customer {

    //variable declaration
    private ArrayList<MenuItem> orderedCustomrItems;

    //Default constructor
    public OrderedCustomer() {
        super();
        this.orderedCustomrItems = new ArrayList<>();
    }

    //mutator of MenuItem
    public ArrayList<MenuItem> getOrderedCustomrItems() {
        return orderedCustomrItems;
    }

    //accessor of MenuItem
    public void setOrderedCustomrItems(ArrayList<MenuItem> orderedCustomrItems) {
        this.orderedCustomrItems = orderedCustomrItems;
    }

    //toString()
    @Override
    public String toString() {
        String tempString = "Customer Name : " + super.getCustomerName() + "\nTable No : " + super.getCustomerTableNumber() + "\n";
        for (int i = 0; i < orderedCustomrItems.size(); i++) {
            tempString += "\n" + orderedCustomrItems.get(i).toString();
        }
        tempString += "******************************************************************************";
        return tempString;
    }

}
