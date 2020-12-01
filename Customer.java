public class Customer {

    private String customerName;
    private int customerTableNumber;

//constructor
    public Customer() {
        this.customerName = "";
        this.customerTableNumber = 0;
    }

//accessor
    public String getCustomerName() {
        return customerName;
    }

//accessor
    public void setCustomerName(String customerName) throws Exception {
        //check if the customer customerName is less than 20
        if (customerName.length() <= 20) {
            this.customerName = customerName;
        } else {
            throw new Exception("Customer name cannot be more than 20 characters.");
        }
    }

    public int getCustomerTableNumber() {
        return customerTableNumber;
    }

//mutator
    public void setCustomerTableNumber(int customerTableNumber) throws Exception {
        //check if the customerTableNumber is between 1 and 8
        if (customerTableNumber >= 1 && customerTableNumber <= 8) {
            this.customerTableNumber = customerTableNumber;
        } else {
            throw new Exception("Table number must be between 1 and 8.");
        }
    }

//toString()
    @Override
    public String toString() {
        return "Name :  " + this.customerName + "\n Table Number : " + this.customerTableNumber;
    }

}
