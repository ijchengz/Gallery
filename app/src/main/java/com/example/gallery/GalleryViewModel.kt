package com.example.gallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive: LiveData<List<PhotoItem>>
        get() = _photoListLive

    fun fetchData() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            getURL(),
            Response.Listener {
                _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {
                Log.d("hello", it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getURL(): String {
        return "https://pixabay.com/api/?key=&q=${keywords.random()}"
    }

    private val keywords = arrayOf(
        "buffalo",
        "antelope",
        "eagle",
        "lion",
        "leopard",
        "elephant",
        "giraffe",
        "tiger",
        "jackal"
    )
}