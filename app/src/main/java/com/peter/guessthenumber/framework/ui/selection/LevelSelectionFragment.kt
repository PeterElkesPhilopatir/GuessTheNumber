package com.peter.guessthenumber.framework.ui.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.peter.guessthenumber.R
import com.peter.guessthenumber.databinding.FragmentLevelSelectionBinding

class LevelSelectionFragment : Fragment() {
    private lateinit var binding: FragmentLevelSelectionBinding
    private lateinit var viewModel: LevelSelectionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentLevelSelectionBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(LevelSelectionViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.level.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(
                    LevelSelectionFragmentDirections.actionLevelSelectionFragmentToGameFragment(
                        it
                    )
                )
                viewModel.onNavigationComplete()
            }
        }

        return binding.root
    }

}