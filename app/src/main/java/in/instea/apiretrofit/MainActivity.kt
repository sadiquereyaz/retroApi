package `in`.instea.apiretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")      //starting point of API Url
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object :
            Callback<MyData?> {       //press ctrl+shift+space then enter in braces of  enqueue()
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if api call is successful
                val allResponseBody = response.body()       //we'll get all the members of the MyData i.e. skip, products, limit etc
                val productsList = allResponseBody?.products!!
                myAdapter = MyAdapter(this@MainActivity, productsList )
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if api call is unsuccessful
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }


}