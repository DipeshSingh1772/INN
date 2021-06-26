package com.example.inn

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide

class CustomAdapter(private var mContext:Context,private var mresource:Int, var listview:ArrayList<CustomListView>):ArrayAdapter<CustomListView>(mContext,mresource,listview) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        Log.d("succes","called view")
        val enflate:LayoutInflater = LayoutInflater.from(mContext)
        val itemsInCustomLayout:View = enflate.inflate(mresource,null)

        val HeadlineView = itemsInCustomLayout.findViewById<TextView>(R.id.NewsHeadlineView)
        val NewsImage = itemsInCustomLayout.findViewById<ImageView>(R.id.NewsImageView)
        val NewsCategory = itemsInCustomLayout.findViewById<TextView>(R.id.NewsCategoryView)
//        val NewsDate = itemsInCustomLayout.findViewById<TextView>(R.id.NewsDateView)

        val positionOnScreen = listview[position]
        HeadlineView.text = positionOnScreen.getNewsHeadline()
        Glide.with(itemsInCustomLayout.context).load(positionOnScreen.getNewsImage()).into(NewsImage)
//      NewsImage.setImageResource(positionOnScreen.getNewsImage())
        NewsCategory.text = positionOnScreen.getNewsCategory()
//        NewsDate.text = positionOnScreen.getNewsDate()

        NewsImage.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabs = builder.build()
            customTabs.launchUrl(itemsInCustomLayout.context, Uri.parse(positionOnScreen.getNewsurl()))
        }

        return itemsInCustomLayout
    }

    fun updateNews(_updateNews:ArrayList<CustomListView>){
        listview.clear()
        listview.addAll(_updateNews)

        notifyDataSetChanged()
    }
}