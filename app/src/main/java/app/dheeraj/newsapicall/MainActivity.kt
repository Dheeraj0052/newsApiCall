package app.dheeraj.newsapicall

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val urlforapi="https://newsapi.org/v2/everything?domains=wsj.com&apiKey=21bb241788a24064917fffc087581b4c"
     private val gson = Gson()
    private val resultarray = arrayListOf<ArticlesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client=OkHttpClient()
        val request=Request.Builder().url(urlforapi).build()
        client.newCall(request).enqueue(object :Callback{
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()
                val result =responseBody?.string()
                val parsedobject= gson.fromJson(result,ArticlesItem::class.java)
//                Log.e("tag",parsedobject.status)
                resultarray.add(parsedobject)
                runOnUiThread (){
                    val ap =NewsAdapter(this@MainActivity,resultarray)
                    rcView.layoutManager = LinearLayoutManager(baseContext)
                    rcView.adapter = ap

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }


        })

    }
}
