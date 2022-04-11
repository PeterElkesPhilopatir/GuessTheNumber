package com.peter.guessthenumber.framework.ui.game

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.peter.guessthenumber.R
import com.peter.guessthenumber.databinding.FragmentGameBinding
import com.peter.guessthenumber.databinding.FragmentLevelSelectionBinding
import com.peter.guessthenumber.framework.ui.selection.LevelSelectionViewModel
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.setLevel(GameFragmentArgs.fromBundle(requireArguments()).level)
        viewModel.apply {
            userAttempts.observe(viewLifecycleOwner) {
                if (it == 0)
                    binding.attempts.visibility = View.GONE
                else {
                    binding.attempts.visibility = View.VISIBLE
                    binding.attempts.text = "Steps : $it"
                }
            }

            status.observe(viewLifecycleOwner) {
                if (it == null)
                    binding.status.visibility = View.GONE
                else
                    binding.status.visibility = View.VISIBLE
            }
            showPlayAgain.observe(viewLifecycleOwner) {
                if (it) {
                    binding.playAgain.visibility = View.VISIBLE
                    binding.share.visibility = View.VISIBLE
                }
            }
            back.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigateUp()
                    viewModel.onPlayAgainCompleted()
                }
            }

            share.observe(viewLifecycleOwner) {
                if (it)
                    share()
            }
        }

        return binding.root
    }

    private fun share() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Look ! Find The Number on Playstore! \n\n" +
                        "https://play.google.com/store/apps/details?id=com.peter.guessthenumber"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share")

        try {
            context?.startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                context,
                context?.getString(R.string.sharing_not_available),
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.onShareCompleted()
    }

}