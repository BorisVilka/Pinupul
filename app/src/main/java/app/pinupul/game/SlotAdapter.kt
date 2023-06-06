package app.pinupul.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.pinupul.game.databinding.ListItemBinding
import java.util.*

class SlotAdapter: RecyclerView.Adapter<SlotAdapter.MyViewHolder>(


) {

    private val arr: Array<Int> = arrayOf(R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5,R.drawable.t6,)
    private val random: Random = Random()

    class MyViewHolder(
        public val binding: ListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ListItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.icon.setImageResource(arr[random.nextInt(arr.size)])
    }

    override fun getItemCount(): Int {
        return 1100;
    }
}