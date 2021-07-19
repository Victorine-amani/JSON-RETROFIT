package json_api.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    lateinit var title:TextView
    lateinit var body:TextView
    lateinit var commentLayout:RecyclerView
    var post_Id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        getPost()
        getComments()
        title=findViewById(R.id.tvTitle2)
        body=findViewById(R.id.tvBody3)
        commentLayout=findViewById(R.id.rvComments)

    }

    fun getPost(){
        post_Id=intent.getIntExtra("postId",0)
        if(post_Id==0){
            finish()
            Toast.makeText(baseContext,"Post not found",Toast.LENGTH_LONG).show()
        }

        val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.getPost(post_Id)
        request.enqueue(object : Callback<Posts?> {
            override fun onResponse(call: Call<Posts?>, response: Response<Posts?>) {
                if(response.isSuccessful){
                    val responsePost=response.body()
                   title.text=responsePost?.title
                    body.text=responsePost?.body


                }
            }

            override fun onFailure(call: Call<Posts?>, t: Throwable) {
               Toast.makeText(baseContext,"Sorry we are unable to fetch your data",Toast.LENGTH_LONG).show()
            }
        })

    }

    fun getComments(){
        post_Id=intent.getIntExtra("postId",0)
        val retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=retrofit.getComments(post_Id)
        request.enqueue(object : Callback<List<Comments>?> {
            override fun onResponse(call: Call<List<Comments>?>, response: Response<List<Comments>?>) {
             if(response.isSuccessful){
                 val commentResponse=response.body()!!
                 val commentsAdapter=CommentsRecyclerViewAdapter(commentResponse)
                 commentLayout.adapter=commentsAdapter
                 commentLayout.layoutManager=LinearLayoutManager(baseContext)
             }
            }

            override fun onFailure(call: Call<List<Comments>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}