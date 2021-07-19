package json_api.com

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsRecyclerViewAdapter(var commentList: List<Comments>):RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
       var itemView=LayoutInflater.from(parent.context).inflate(R.layout.comments_list_item,parent,false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComments=commentList[position]
        holder.name.text=currentComments.name
        holder.email.text=currentComments.email
        holder.body.text=currentComments.body
    }

    override fun getItemCount(): Int {
       return commentList.size
    }
}

class CommentsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var name=itemView.findViewById<TextView>(R.id.tvName)
    var email=itemView.findViewById<TextView>(R.id.tvEmail)
    var body=itemView.findViewById<TextView>(R.id.tvBody2)
}