package hcmute.edu.vn.foody_17;

public class Menu {
    private String NameItem;
    private String Price;


    public Menu(){

    }

    public Menu(String nameItem,String price){
        NameItem = nameItem;
        Price = price;
    }

    public String getNameItem() {
        return NameItem;
    }

    public void setNameItem(String nameItem) {
        NameItem = nameItem;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) { Price = price; }
}
