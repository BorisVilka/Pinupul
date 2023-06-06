package app.pinupul.game

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import androidx.navigation.Navigation
import app.pinupul.game.databinding.FragmentChipsBinding
import java.util.*


class ChipsFragment : Fragment() {

    private lateinit var binding: FragmentChipsBinding
    private var ind = 0
    private var code = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChipsBinding.inflate(inflater,container,false)
        binding.imageView15.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        binding.imageView17.setOnClickListener {
            ind = 0
            binding.textView5.setTextColor(requireContext().getColor(R.color.sel))
            binding.textView6.setTextColor(requireContext().getColor(R.color.nonsel))
        }
        binding.imageView18.setOnClickListener {
            ind = 1
            binding.textView6.setTextColor(requireContext().getColor(R.color.sel))
            binding.textView5.setTextColor(requireContext().getColor(R.color.nonsel))
        }
        var player = MediaPlayer.create(requireContext(),R.raw.chp)
        var balance = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("score",1000)
        binding.textView4.text = "$balance"
        val random = Random()
        val context = requireContext()
        binding.imageView16.setOnClickListener {
            if(code==0) {
                if(context.getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("sound",false)) player.start()
                binding.textView5.visibility = View.INVISIBLE
                binding.textView6.visibility = View.INVISIBLE
                binding.imageView17.visibility = View.INVISIBLE
                binding.imageView18.visibility = View.INVISIBLE
                code = 1;
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    binding.textView7.visibility = View.VISIBLE
                    binding.imageView19.visibility = View.VISIBLE
                    val tmp = random.nextInt(2)
                    if(ind==tmp) {
                        binding.textView7.text = "WIN"
                        balance+=20
                        if(ind==0) {
                            binding.imageView19.setImageResource(R.drawable.winblue)
                        } else {
                            binding.imageView19.setImageResource(R.drawable.winred)
                        }
                    } else {
                        binding.textView7.text = "UNLUCKY"
                        balance-=20
                        if(ind==1) {
                            binding.imageView19.setImageResource(R.drawable.loseblue)
                        } else {
                            binding.imageView19.setImageResource(R.drawable.losered)
                        }
                    }
                    binding.textView4.text = "$balance"
                    context.getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putInt("score",balance).apply()
                },1500)

            } else {
                binding.textView5.visibility = View.VISIBLE
                binding.textView6.visibility = View.VISIBLE
                binding.imageView17.visibility = View.VISIBLE
                binding.imageView18.visibility = View.VISIBLE

                binding.textView7.visibility = View.INVISIBLE
                binding.imageView19.visibility = View.INVISIBLE
                code = 0
            }
        }
        return binding.root
    }

}