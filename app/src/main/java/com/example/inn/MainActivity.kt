package com.example.inn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadApiOfNews()
        val arrayOfNews = ArrayList<CustomListView>()

        val activityListView = findViewById<ListView>(R.id.activiyLayoutView)

        mAdapter = CustomAdapter(this,R.layout.inn_custom_layout,arrayOfNews)
        activityListView.adapter = mAdapter

    }

    private fun loadApiOfNews(){

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=IAqxDeZiRSvUBWfbPw0hQIOvP3TGZ5qn"
        val dataArray = ArrayList<CustomListView>()

        //IAqxDeZiRSvUBWfbPw0hQIOvP3TGZ5qn

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                // Display the first 500 characters of the response string.
                val data:JSONArray = response.getJSONArray("results")
                val length:Int  = data.length()

                for(i in 0 until length-1){
                    val objects = data.getJSONObject(i)

                    val news = CustomListView(
                        objects.getString("title"),
                        objects.getJSONArray("multimedia").getJSONObject(0).getString("url"),
                        objects.getString("section"),
                        objects.getString("url")
//                        "date"
                    )

                    dataArray.add(news)
                }

                mAdapter.updateNews(dataArray)

            },
            {
                Log.d("error","not successful")
            })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

//    private fun setData(): ArrayList<CustomListView>{
//
//        val news = ArrayList<CustomListView>()
//
//        news.add(CustomListView(data.getJSONObject(0).getString("caption"),R.drawable.crypto,"Market","22/06/2021"))
//
//        return news
//
//    }

}