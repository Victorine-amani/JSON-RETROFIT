package json_api.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var postView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
        postView=findViewById(R.id.rvPosts)
    }
    fun getPosts(){
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getPosts()
        request.enqueue(object :Callback<List<Posts>?>{
            override fun onResponse(call: Call<List<Posts>?>, response: Response<List<Posts>?>) {
                if (response.isSuccessful) {
                    var postresponse = response.body()!!
                    var postAdapter = PostRecyclerViewAdapter(postresponse,baseContext)
                    postView.adapter = postAdapter
                    postView.layoutManager = LinearLayoutManager(baseContext)
                    Log.d("Just add", "onResponse: ${response.body()!![1].body}")
                    var poster = mutableListOf<Posts>()
                    for (i in 1..100) {
                        poster.add(
                            Posts(
                                3,
                                89,
                                "Just add",
                                "Hello sorry for the intrusion just chilling"
                            )
                        )
                    }
                }
            }
            override fun onFailure(call: Call<List<Posts>?>, t: Throwable) {
                Log.d("Just add", "onFailure")
            }

        })
    }
}