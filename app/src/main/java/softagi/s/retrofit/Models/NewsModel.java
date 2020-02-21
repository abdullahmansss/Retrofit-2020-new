package softagi.s.retrofit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel
{
    private List<Items> articles;

    public NewsModel(List<Items> articles) {
        this.articles = articles;
    }

    public List<Items> getArticles() {
        return articles;
    }

    public void setArticles(List<Items> articles) {
        this.articles = articles;
    }

    public class Items
    {
        private String title;
        @SerializedName("description")
        private String desc;
        @SerializedName("urlToImage")
        private String image;

        public Items(String title, String desc, String image)
        {
            this.title = title;
            this.desc = desc;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}