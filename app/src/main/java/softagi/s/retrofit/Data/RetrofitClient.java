package softagi.s.retrofit.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import softagi.s.retrofit.Models.NewsModel;

public class RetrofitClient
{
    private final static String BASE_URL = "http://newsapi.org/";
    private static RetrofitClient retrofitClient;
    private RetrofitHelper retrofitHelper;

    private RetrofitClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitHelper = retrofit.create(RetrofitHelper.class);
    }

    public static RetrofitClient getInstance()
    {
        if (retrofitClient == null)
        {
            retrofitClient = new RetrofitClient();
        }

        return retrofitClient;
    }

    public Call<NewsModel> getNews(String country, String category, String apiKey)
    {
        return retrofitHelper.getNews(country, category, apiKey);
    }
}