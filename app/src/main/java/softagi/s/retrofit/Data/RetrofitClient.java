package softagi.s.retrofit.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import softagi.s.retrofit.Models.RestModel;

public class RetrofitClient
{
    private static final String BASE_URL = "http://192.168.1.10:1337/";
    private static RetrofitClient retrofitClient;
    private RetrofitHelper retrofitHelper;

    private RetrofitClient()
    {
        Retrofit retrofit = new Retrofit
                .Builder()
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

    public Call<List<RestModel>> getRest()
    {
        return retrofitHelper.getRest();
    }
}