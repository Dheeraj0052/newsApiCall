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
    private val urlforapi =
        "https://newsapi.org/v2/everything?q=bitcoin&from=2019-02-21&sortBy=publishedAt&apiKey=5f4aee5cfeb7406d9c6a40cc4bdd978e"
    private val gson = Gson()
    private val resultarray = arrayListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = OkHttpClient()
        val request = Request.Builder().url(urlforapi).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()
                val result = responseBody?.string()
                val parsedobject = gson.fromJson(result, Result::class.java)
                Log.e("tag", parsedobject.status)
                resultarray.add(parsedobject)
                runOnUiThread() {
                    val ap = adapter(this@MainActivity, resultarray)
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
