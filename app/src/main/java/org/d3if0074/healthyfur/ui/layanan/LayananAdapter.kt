package org.d3if0074.healthyfur.ui.layanan

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0074.healthyfur.R
import org.d3if0074.healthyfur.databinding.ItemLayananBinding
import org.d3if0074.healthyfur.model.InfoLayanan
import org.d3if0074.healthyfur.network.InfoLayananApi

class LayananAdapter : RecyclerView.Adapter<LayananAdapter.ViewHolder>() {

    private val data = mutableListOf<InfoLayanan>()

    fun updateData(newData: List<InfoLayanan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemLayananBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind (infoLayanan: InfoLayanan) = with(binding) {
                layananTv.text = infoLayanan.nama
                deskripsiTv.text = infoLayanan.deskripsi

                Glide.with(layananImageView.context)
                    .load(InfoLayananApi.getInfoLayananURL(infoLayanan.gambar))
                    .error(R.drawable.baseline_broken_image_24)
                    .into(layananImageView)

                root.setOnClickListener {
                    val message = root.context.getString(R.string.message, infoLayanan.nama)
                    Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayananBinding.inflate(inflater, parent, false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}