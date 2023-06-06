package app.pinupul.game

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import app.pinupul.game.databinding.FragmentSlotsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SlotsFragment : Fragment() {

    private lateinit var binding: FragmentSlotsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSlotsBinding.inflate(inflater,container,false)
        binding.list1.adapter = SlotAdapter()
        binding.list2.adapter = SlotAdapter()
        binding.list3.adapter = SlotAdapter()
        binding.list4.adapter = SlotAdapter()
        binding.list5.adapter = SlotAdapter()
        binding.imageView15.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        binding.list1.setOnTouchListener { p0, p1 -> true }
        binding.list2.setOnTouchListener { p0, p1 -> true }
        binding.list3.setOnTouchListener { p0, p1 -> true }
        binding.list4.setOnTouchListener { p0, p1 -> true }
        binding.list5.setOnTouchListener { p0, p1 -> true }
        var balance = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).getInt("score",1000)
        binding.textView4.text = "$balance"
        val context = requireContext()
        val activity = requireActivity()
        var player = MediaPlayer.create(requireContext(),R.raw.slot)
        binding.textView8.setOnClickListener {
            if(context.getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("sound",false)) player.start()
            binding.list1.smoothScrollToPosition((binding.list1.adapter as RecyclerView.Adapter).itemCount-2)
            binding.list2.smoothScrollToPosition((binding.list2.adapter as RecyclerView.Adapter).itemCount-2)
            binding.list3.smoothScrollToPosition((binding.list3.adapter as RecyclerView.Adapter).itemCount-2)
            binding.list4.smoothScrollToPosition((binding.list4.adapter as RecyclerView.Adapter).itemCount-2)
            binding.list5.smoothScrollToPosition((binding.list5.adapter as RecyclerView.Adapter).itemCount-2)
            val completable = Completable.create {
                activity.runOnUiThread {
                    binding.list1.scrollToPosition(0)
                    binding.list2.scrollToPosition(0)
                    binding.list3.scrollToPosition(0)
                    binding.list4.scrollToPosition(0)
                    binding.list5.scrollToPosition(0)
                    balance += 50
                    binding.textView4.text = "$balance"
                }
                it.onComplete()
            }.delaySubscription(10, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread())
            completable.subscribe()
        }
        return binding.root
    }


}