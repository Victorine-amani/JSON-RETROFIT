package json_api.com

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getPosts():Call<List<Posts>>

    @GET("posts/{post_id}")
    fun getPost(@Path("post_id")id:Int):Call<Posts>

    @GET("posts/{post_id}/comments")
    fun getComments(@Path("post_id")id:Int):Call<List<Comments>>

}