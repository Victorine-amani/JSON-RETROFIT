package json_api.com

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PostRecyclerViewAdapter(var postList: List<Posts>, var context: Context):RecyclerView.Adapter<postsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postsViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return postsViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: postsViewHolder, position: Int) {
        val currentPosts=postList.get(position)
        holder.tvTitle.text=currentPosts.title
        holder.tvBody.text=currentPosts.body
        holder.id.text=currentPosts.id.toString()
        holder.postings.setOnClickListener {
            var intent=Intent(context,CommentsActivity::class.java)
            intent.putExtra("postId",currentPosts.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}

class postsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var postings=itemView.findViewById<CardView>(R.id.cvComments)
    var tvTitle=itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvBody)
    var id=itemView.findViewById<TextView>(R.id.tvId)
}