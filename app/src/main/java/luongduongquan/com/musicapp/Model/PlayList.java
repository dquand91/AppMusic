package luongduongquan.com.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable{

@SerializedName("IdPlayList")
@Expose
private String idPlayList;
@SerializedName("TenPlayList")
@Expose
private String tenPlayList;
@SerializedName("HinhAnh")
@Expose
private String hinhAnh;
@SerializedName("Icon")
@Expose
private String icon;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getTenPlayList() {
return tenPlayList;
}

public void setTenPlayList(String tenPlayList) {
this.tenPlayList = tenPlayList;
}

public String getHinhAnh() {
return hinhAnh;
}

public void setHinhAnh(String hinhAnh) {
this.hinhAnh = hinhAnh;
}

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

}