package hcmute.edu.vn.foody_17;

import android.content.Intent;

public class Food {
    private Integer IdFood;
    private String Title;
    private String Category;
    private String Description;
    private String Thumbnail;
    private String Address;
    private String Province;
    private String TypeStore;
    private String Price;
    private Double LatiTude;
    private Double LongiTude;
    private String Account;
    private String Pass;
    private String Sdt;

    public Food() {
    }

    public Food(Integer idFood,String title, String category, String description, String thumbnail, String address, String province, String typeStore, String price, Double latiTude, Double longiTude, String account, String pass,String sdt) {
        IdFood = idFood;
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        Address = address;
        Province = province;
        TypeStore = typeStore;
        Price = price;
        LatiTude = latiTude;
        LongiTude = longiTude;
        Account = account;
        Pass = pass;
        Sdt = sdt;
    }
    public Integer getIdFood(){return IdFood;}

    public void setIdFood(Integer idFood){IdFood = idFood;}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getAddress(){return Address;}

    public void setAddress(String address){ Address = address;}

    public String getProvince(){return Province;}

    public void setProvince(String province){ Province = province;}

    public String getTypeStore(){return TypeStore;}

    public void setTypeStore(String typeStore){ TypeStore = typeStore;}

    public String getPrice(){return Price;}

    public void setPrice(String price){ Price = price;}

    public Double getLatiTude(){return LatiTude;}

    public void setLatiTude(Double latiTude){ LatiTude = latiTude;}

    public Double getLongiTude(){return LongiTude;}

    public void setLongiTude(Double longiTude){ LongiTude = longiTude;}

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) { Pass = pass; }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }
}
