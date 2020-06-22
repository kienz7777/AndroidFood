package hcmute.edu.vn.foody_17;

import android.widget.ImageView;

import java.nio.charset.IllegalCharsetNameException;

public class MenuGroup {
    private Integer IdMenuGroup;
    private String NameGroup;
    private String Image;
    private Integer IdFood;

    public  MenuGroup(){

    }

    public MenuGroup(Integer idMenuGroup, String nameGroup, String image, Integer idFood){
        IdMenuGroup = idMenuGroup;
        NameGroup = nameGroup;
        Image = image;
        IdFood = idFood;
    }

    public Integer getIdMenuGroup(){return IdMenuGroup;}

    public void setIdMenuGroup(Integer idMenuGroup){ IdMenuGroup = idMenuGroup;}

    public String getNameGroup(){return NameGroup;}

    public void setNameGroup(String nameGroup){ NameGroup = nameGroup;}

    public String getImage(){return Image;}

    public void setImage(String image){ Image = image;}

    public Integer getIdFood(){return IdFood;}

    public  void setIdFood(Integer idFood){ IdFood = idFood;}
}
