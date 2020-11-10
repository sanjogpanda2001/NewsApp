package com.example.mynewsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), NewsItemClicked {

    var mAdapter=NewsListAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchdata()
        mAdapter= NewsListAdapter(this)

        recyclerView.adapter=mAdapter
    }
    private fun fetchdata(){

      val url="http://newsapi.org/v2/everything?q=bitcoin&from=2020-10-10&sortBy=publishedAt&apiKey=555fd0d1d44e4c8a9c4e49b25b646a46"
       //val url="https://jsonplaceholder.typicode.com/photos"
        val jsonOjectRequest=JsonObjectRequest(Request.Method.GET,
        url,null,
                {
                    val newsjsonarray=it.getJSONArray("articles")
                    val newsArray=ArrayList<News>()
                    for (i in 0 until newsjsonarray.length()){
                        val newsJsonObject=newsjsonarray.getJSONObject(i)
                        val news=News(
                            newsJsonObject.getString("title"),
                            newsJsonObject.getString("author"),
                            newsJsonObject.getString("url"),
                            newsJsonObject.getString("urlToImage")
                        )
                        newsArray.add(news)
                    }
                    mAdapter.updateNews(newsArray)
                }, {
    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                })
        MySingleton.getInstance(this).addToRequestQueue(jsonOjectRequest)


//        val list=ArrayList<String>()
//        for (i in 0..100){
//            list.add("item number $i")
//        }
//        return list
    }

    override fun onItemClicked(item: News) {

    }
}