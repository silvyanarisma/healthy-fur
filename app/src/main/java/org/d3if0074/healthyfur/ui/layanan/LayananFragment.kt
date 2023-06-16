package org.d3if0074.healthyfur.ui.layanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if0074.healthyfur.databinding.FragmentLayananBinding

class LayananFragment : Fragment() {

    private lateinit var binding: FragmentLayananBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayananBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}