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

    public Food() {
    }

    public Food(Integer idFood,String title, String category, String description, String thumbnail, String address, String province, String typeStore, String price, Double latiTude, Double longiTude) {
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


}
