package com.example.inn


class CustomListView(_headline:String,_imageview:String,_category:String,_newsurl:String) {

    private var NewsHeadline:String= _headline
    private var NewsImage:String = _imageview
    private var NewsCategory:String = _category
//    private var NewsDate:String = _date
    private var Newsurl:String =_newsurl

    fun getNewsHeadline(): String {
        return NewsHeadline
    }

    fun getNewsurl(): String {
        return Newsurl
    }

    fun getNewsImage(): String {
        return NewsImage
    }
    fun getNewsCategory(): String {
        return NewsCategory
    }
//    fun getNewsDate(): String {
//        return NewsDate
//    }

}