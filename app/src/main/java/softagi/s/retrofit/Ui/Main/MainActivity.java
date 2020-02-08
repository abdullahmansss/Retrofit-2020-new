package softagi.s.retrofit.Ui.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import softagi.s.retrofit.Data.RetrofitClient;
import softagi.s.retrofit.Models.RestModel;
import softagi.s.retrofit.R;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private restAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
        getData();
    }

    private void getData()
    {
        RetrofitClient.getInstance().getRest().enqueue(new Callback<List<RestModel>>()
        {
            @Override
            public void onResponse(Call<List<RestModel>> call, Response<List<RestModel>> response)
            {
                if (response.isSuccessful() && response.code() == 200)
                {
                    List<RestModel> restModelList = response.body();
                    adapter = new restAdapter(restModelList);
                    recyclerView.setAdapter(adapter);
                } else
                    {
                        Toast.makeText(MainActivity.this, response.code() + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<List<RestModel>> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecycler()
    {
        recyclerView = findViewById(R.id.recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    class restAdapter extends RecyclerView.Adapter<restAdapter.restVH> {
        List<RestModel> restModels;

         restAdapter(List<RestModel> restModels)
        {
            this.restModels = restModels;
        }

        @NonNull
        @Override
        public restVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View item = LayoutInflater.from(getApplicationContext()).inflate(R.layout.rest_item, parent, false);
            return new restVH(item);
        }

        @Override
        public void onBindViewHolder(@NonNull restVH holder, int position)
        {
            RestModel restModel = restModels.get(position);

            String restTitle = restModel.getRestTitle();
            String restMobile = restModel.getRestMobile();
            String restDesc = restModel.getRestDesc();

            holder.title.setText(restTitle);
            holder.mobile.setText(restMobile);
            holder.desc.setText(restDesc);
        }

        @Override
        public int getItemCount()
        {
            return restModels.size();
        }

        class restVH extends RecyclerView.ViewHolder
        {
            TextView title,mobile,desc;

            restVH(@NonNull View itemView)
            {
                super(itemView);

                title = itemView.findViewById(R.id.rest_title);
                mobile = itemView.findViewById(R.id.rest_mobile);
                desc = itemView.findViewById(R.id.rest_desc);
            }
        }
    }
}
