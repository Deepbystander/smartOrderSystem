public class Nutrient {

    //Nutrient Object varible declarations
    private String nutrientName;
    private double nutrientQuantity;

    //Default Construtor of Nutrient class
    public Nutrient() {
        nutrientName = "";
        nutrientQuantity = 0;
    }

    //Parameterised Constructor of Nutriend class
    public Nutrient(String nutrientName, double nutrientQuantity) {
        this.nutrientName = nutrientName;
        this.nutrientQuantity = nutrientQuantity;
    }

    //Nutrient Name accessor
    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    //Nutrient Name mutator
    public String getNutrientName() {
        return nutrientName;
    }

    //Nutrinet Quantity accessor
    public void setNutrientQuantity(int nutrientQuantity) {
        this.nutrientQuantity = nutrientQuantity;
    }

    //Nutrient Quantity mutator
    public double getNutrientQuantity() {
        return nutrientQuantity;
    }

    //Method Overriding to toString
    @Override
    public String toString() {
        return nutrientName + " :  " + String.format("%.2f", nutrientQuantity) + "\n";
    }
}
