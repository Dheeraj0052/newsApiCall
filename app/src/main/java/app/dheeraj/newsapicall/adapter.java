package app.dheeraj.newsapicall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {

    private Context context;
    private ArrayList<Result> results = new ArrayList<>();
    private ArrayList<ArticlesItem> articlesItems;

    public adapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        articlesItems = (ArrayList<ArticlesItem>) results.get(i).getArticles();
        viewholder.tvname.setText(articlesItems.get(i).getSource().getName());
        viewholder.tvid.setText(articlesItems.get(i).getSource().toString());
//          viewholder.tvid.setText((Integer) articlesItems.get(i).getSource().getId());

        Picasso.get().load(articlesItems.get(i).getUrlToImage()).into(viewholder.urltoNewsImage);


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvname, tvid;
        ImageView urltoimage;
        ImageView urltoNewsImage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvNews);
            tvid = itemView.findViewById(R.id.tvSubtitle);
            urltoimage = itemView.findViewById(R.id.UrlToImage);
            urltoNewsImage = itemView.findViewById(R.id.UrlToNewsImage);
        }
    }
}
