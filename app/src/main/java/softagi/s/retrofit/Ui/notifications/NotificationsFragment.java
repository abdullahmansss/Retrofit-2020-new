package softagi.s.retrofit.Ui.notifications;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import softagi.s.retrofit.Data.RetrofitClient;
import softagi.s.retrofit.Models.NewsModel;
import softagi.s.retrofit.R;

public class NotificationsFragment extends Fragment
{
    private View view;
    private String tt;
    private newsAdapter adapter;
    private RecyclerView recyclerView;

    public NotificationsFragment(String t)
    {
        this.tt = t;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.notifications_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        initRecycler();
        getData(tt);
    }

    private void getData(String tt)
    {
        RetrofitClient.getInstance().getNews("eg", tt, "65f7f556ec76449fa7dc7c0069f040ca").enqueue(new Callback<NewsModel>()
        {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response)
            {
                if (response.isSuccessful() && response.code() == 200)
                {
                    NewsModel newsModel = response.body();
                    if (newsModel != null)
                    {
                        List<NewsModel.Items> items = newsModel.getArticles();
                        adapter = new newsAdapter(items);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t)
            {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecycler()
    {
        recyclerView = view.findViewById(R.id.news_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    class newsAdapter extends RecyclerView.Adapter<newsAdapter.newsVH>
    {
        List<NewsModel.Items> newsModels;

        newsAdapter(List<NewsModel.Items> newsModels)
        {
            this.newsModels = newsModels;
        }

        @NonNull
        @Override
        public newsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
            return new newsVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull newsVH holder, int position)
        {
            NewsModel.Items items = newsModels.get(position);

            String title = items.getTitle();
            String image = items.getImage();
            String desc = items.getDesc();

            holder.textView.setText(title);
            Picasso.get()
                    .load(image)
                    .error(R.drawable.homeicon)
                    .placeholder(R.drawable.homeicon)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount()
        {
            return newsModels.size();
        }

        class newsVH extends RecyclerView.ViewHolder
        {
            ImageView imageView;
            TextView textView;

            newsVH(@NonNull View itemView)
            {
                super(itemView);

                imageView = itemView.findViewById(R.id.news_image);
                textView = itemView.findViewById(R.id.news_title);
            }
        }
    }
}