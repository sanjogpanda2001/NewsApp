package com.example.mynewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val item=ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener{
       listener.onItemClicked(item[viewHolder.adapterPosition])
   }
    return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=item[position]
        holder.title_news.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imgurl).into(holder.image)
    }

    override fun getItemCount(): Int {
    return  item.size
    }

    fun updateNews(updatdNews:ArrayList<News>){
        item.clear()
        item.addAll(updatdNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title_news: TextView =itemView.findViewById(R.id.title_news)
    val image: ImageView =itemView.findViewById<ImageView>(R.id.imageView)
    val author: TextView =itemView.findViewById<TextView>(R.id.author)
}

interface NewsItemClicked{
    fun onItemClicked(item:News)
}