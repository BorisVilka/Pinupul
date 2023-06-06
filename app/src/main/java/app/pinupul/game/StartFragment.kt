package app.pinupul.game

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import app.pinupul.game.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var ind = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater,container,false)
        binding.textView.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.navigate(R.id.selectFragment)
        }
        ind = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("ind",1)
        set()
        binding.imageView23.setOnClickListener {
            (requireActivity() as MainActivity).player.start()
            ind = 0
            set()
        }
        binding.imageView21.setOnClickListener {
            (requireActivity() as MainActivity).player.pause()
            ind = 1
            set()
        }
        binding.imageView22.setOnClickListener {
            (requireActivity() as MainActivity).player.pause()
            ind = 2
            set()
        }
        return binding.root
    }

    fun set() {
        when(ind) {
            0 -> {
                requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putBoolean("sound",true).putBoolean("music",true).putInt("ind",ind).apply()
                binding.imageView23.setBackgroundColor(requireContext().getColor(R.color.red))
                binding.imageView23.setColorFilter(Color.WHITE)

                binding.imageView22.setBackgroundColor(requireContext().getColor(android.R.color.transparent))
                binding.imageView22.setColorFilter(requireContext().getColor(R.color.sel))

                binding.imageView21.setBackgroundColor(requireContext().getColor(android.R.color.transparent))
                binding.imageView21.setColorFilter(requireContext().getColor(R.color.sel))
            }
            1 -> {
                requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putBoolean("sound",true).putBoolean("music",false).putInt("ind",ind).apply()
                binding.imageView21.setBackgroundColor(requireContext().getColor(R.color.red))
                binding.imageView21.setColorFilter(Color.WHITE)

                binding.imageView22.setBackgroundColor(requireContext().getColor(android.R.color.transparent))
                binding.imageView22.setColorFilter(requireContext().getColor(R.color.sel))

                binding.imageView23.setBackgroundColor(requireContext().getColor(android.R.color.transparent))
                binding.imageView23.setColorFilter(requireContext().getColor(R.color.sel))
            }
            2 -> {
                requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putBoolean("sound",false).putBoolean("music",false).putInt("ind",ind).apply()
                binding.imageView22.setBackgroundColor(requireContext().getColor(R.color.red))
                binding.imageView22.setColorFilter(Color.WHITE)

                binding.imageView23.setBackgroundColor(requireContext().getColor(android.R.color.transparent))
                binding.imageView23.setColorFilter(requireContext().getColor(R.color.sel))

                binding.imageView21.setBackgroundColor(requireContext().getColor(android.R.color.transparent))
                binding.imageView21.setColorFilter(requireContext().getColor(R.color.sel))
            }
        }
    }

}