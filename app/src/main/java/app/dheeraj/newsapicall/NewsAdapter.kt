package app.dheeraj.newsapicall

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class NewsAdapter( val context: Context , val list :ArrayList<ArticlesItem>) :RecyclerView.Adapter<NewsAdapter.NewsHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsHolder {

  return NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_row, p0, false))
    }

    override fun getItemCount(): Int {
     return list.size
    }

    override fun onBindViewHolder(p0: NewsHolder, p1: Int) {
        val current = list[p1]

      with(p0.itemView){

          tvname.text=current.title
          tvid.text=current.description
        Picasso.get().load(current.urlToImage).into(image)
      }

    }

    class NewsHolder (itemview : View) : RecyclerView.ViewHolder( itemview){
    }

}