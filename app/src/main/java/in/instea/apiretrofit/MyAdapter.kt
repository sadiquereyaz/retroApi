package `in`.instea.apiretrofit

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, private val productsArrayList: List<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView
        var price: TextView
        var thumbnailIV: ImageView

        init {
            title = itemView.findViewById(R.id.titleTV)
            description = itemView.findViewById(R.id.descriptionTV)
            price = itemView.findViewById(R.id.priceTV)
            thumbnailIV = itemView.findViewById(R.id.thumbnailIV)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productsArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productsArrayList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.price.text = currentItem.price.toString()+" Rs."
        // use glide or picasso library to get image resource using url
        Picasso.get().load(currentItem.thumbnail).into(holder.thumbnailIV)

    }
}