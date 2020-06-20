package hcmute.edu.vn.foody_17;

public class SearchFood {
    private String Title;
    private String Address;
    private String TypeStore;
    private String Province;
    private int Thumbnail;

    public SearchFood() {
    }

    public SearchFood(String title, String address, String typeStore, String province, int thumbnail) {
        Title = title;
        Address = address;
        TypeStore = typeStore;
        Province = province;
        Thumbnail = thumbnail;
    }

    public String getTitle() { return Title; }

    public void setTitle(String title) { Title = title; }

    public String getAddress() { return Address; }

    public void setAddress(String address) { Address = address; }

    public String getTypeStore() { return TypeStore; }

    public void setTypeStore(String typeStore) { TypeStore = typeStore; }

    public String getProvince(){ return Province;}

    public void setProvince(String province){ Province = province;}

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}

