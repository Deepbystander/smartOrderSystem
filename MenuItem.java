import java.util.ArrayList;

public class MenuItem {

    private String menuItemName;
    private int menuItemId;
    private ArrayList<Nutrient> menuNutrientList;

    //Default Constructor for MenuItem
    public MenuItem() {
        this.menuItemName = "";
        this.menuNutrientList = new ArrayList<>();
    }

    public MenuItem(String menuItemName, int menuItemId, ArrayList<Nutrient> menuNutrientList) {
        this.menuItemName = menuItemName;
        this.menuItemId = menuItemId;
        this.menuNutrientList = menuNutrientList;
    }

    //Copy contructor of MenuItem
    public MenuItem(MenuItem menuItem) {
        this(menuItem.getMenuItemName(), menuItem.getMenuItemId(), menuItem.getMenuNutrientList());
    }

    //mutator of MenuItem
    public String getMenuItemName() {
        return menuItemName;
    }

    //accessor of MenuItem
    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    //mutator of MenuItem
    public ArrayList<Nutrient> getMenuNutrientList() {
        return menuNutrientList;
    }

    //accessor of MenuItem
    public void setMenuNutrientList(ArrayList<Nutrient> menuNutrientList) {
        this.menuNutrientList = menuNutrientList;
    }

    ///mutator of MenuItem
    public int getMenuItemId() {
        return menuItemId;
    }

    //accessor of MenuItem
    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    //toString()
    @Override
    public String toString() {
        String tempString = "Item Name : " + this.menuItemName + "\n" + "Item Id : " + menuItemId + "\n";
        for (int i = 0; i < menuNutrientList.size() - 1; i++) {
            tempString += menuNutrientList.get(i).toString();
        }
        return tempString;
    }
}
