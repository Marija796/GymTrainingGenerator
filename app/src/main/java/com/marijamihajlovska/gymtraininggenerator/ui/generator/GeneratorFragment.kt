package com.marijamihajlovska.gymtraininggenerator.ui.generator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentGeneratorBinding

class GeneratorFragment : Fragment() {
    private var _binding: FragmentGeneratorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGeneratorBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}