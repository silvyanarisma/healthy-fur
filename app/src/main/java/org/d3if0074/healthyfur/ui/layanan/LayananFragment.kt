package org.d3if0074.healthyfur.ui.layanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0074.healthyfur.databinding.FragmentLayananBinding

class LayananFragment : Fragment() {
    private val viewModel: LayananViewModel by lazy {
        ViewModelProvider(this)[LayananViewModel::class.java]
    }

    private lateinit var binding: FragmentLayananBinding

    private lateinit var myAdapter: LayananAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayananBinding.inflate(layoutInflater, container, false)
        myAdapter = LayananAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
    }
}