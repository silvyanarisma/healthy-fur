package org.d3if0074.healthyfur.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0074.healthyfur.R
import org.d3if0074.healthyfur.databinding.ItemHistoriBinding
import org.d3if0074.healthyfur.db.HistoriEntity
import org.d3if0074.healthyfur.model.hasilGrooming
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<HistoriEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<HistoriEntity>() {
                override fun areItemsTheSame(oldData: HistoriEntity, newData: HistoriEntity): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(oldData: HistoriEntity, newData: HistoriEntity): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: HistoriEntity) = with(binding) {
            val hasilTransaksi = item.hasilGrooming()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            namaHewanTextView.text = hasilTransaksi.namaHewan
            layananTextView.text = hasilTransaksi.jenisLayanan
            biayaTextView.text = hasilTransaksi.biaya.toString()

            val colorRes = when(hasilTransaksi.jenisHewan) {
                "Anjing" -> {
                    jenisHewanTextView.text = "A"
                    R.color.dog
                }
                else -> {
                    jenisHewanTextView.text = "K"
                    R.color.cat
                }
            }
            val circleBg = jenisHewanTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
        }
    }
}