package pro.shivansh.scoreboard.adapter

import android.media.session.PlaybackState.CustomAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import pro.shivansh.scoreboard.R
import pro.shivansh.scoreboard.data.testData

class testAdapter(private val mList:List<testData>):RecyclerView.Adapter<testAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_test, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.itemView.findViewById<TextView>(R.id.textView1).text=ItemsViewModel.testName
        holder.itemView.findViewById<TextView>(R.id.textView2).text=ItemsViewModel.testDate




    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name:TextView = itemView.findViewById(R.id.textView1)
        val date: TextView = itemView.findViewById(R.id.textView2)
    }
}
