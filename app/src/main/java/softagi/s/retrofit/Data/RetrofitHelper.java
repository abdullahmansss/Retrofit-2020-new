package softagi.s.retrofit.Data;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;
import softagi.s.retrofit.Models.NewsModel;

public interface RetrofitHelper
{
    @GET("v2/top-headlines")
    Call<NewsModel> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey);
}