package softagi.s.retrofit.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import softagi.s.retrofit.Models.RestModel;

public interface RetrofitHelper
{
    @GET("restaurants")
    Call<List<RestModel>> getRest();
}
