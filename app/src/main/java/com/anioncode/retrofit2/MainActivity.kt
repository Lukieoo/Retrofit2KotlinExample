package com.anioncode.retrofit2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

  var Lista= mutableListOf<User>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit2.create(ApiService::class.java)
        api.fetchAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                response.body()?.let { createDataUser(it) }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }



        })
    }
    fun createDataUser(lista:List<User>){

        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter = ListAdapter(lista)
        }

    }
}
