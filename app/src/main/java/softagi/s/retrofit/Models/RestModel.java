package softagi.s.retrofit.Models;

import com.google.gson.annotations.SerializedName;

public class RestModel
{
    @SerializedName("id")
    private int restId;
    @SerializedName("name")
    private String restTitle;
    @SerializedName("desc")
    private String restDesc;
    @SerializedName("mobile")
    private String restMobile;
    @SerializedName("address")
    private String restAddress;

    public RestModel(int restId, String restTitle, String restDesc, String restMobile, String restAddress) {
        this.restId = restId;
        this.restTitle = restTitle;
        this.restDesc = restDesc;
        this.restMobile = restMobile;
        this.restAddress = restAddress;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getRestTitle() {
        return restTitle;
    }

    public void setRestTitle(String restTitle) {
        this.restTitle = restTitle;
    }

    public String getRestDesc() {
        return restDesc;
    }

    public void setRestDesc(String restDesc) {
        this.restDesc = restDesc;
    }

    public String getRestMobile() {
        return restMobile;
    }

    public void setRestMobile(String restMobile) {
        this.restMobile = restMobile;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }
}
