package org.d3if0074.healthyfur.ui.histori

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0074.healthyfur.R
import org.d3if0074.healthyfur.databinding.ItemHistoriBinding
import org.d3if0074.healthyfur.db.HealthyFurDb
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
        holder.bind(getItem(position), holder.itemView)
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: HistoriEntity, view: View) = with(binding) {
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
            binding.deleteButton.setOnClickListener { deleteData(item.id, item.namaHewan, view.context) }
        }

        private fun deleteData(id: Long, namaHewan: String, context: Context) {
            val db = HealthyFurDb.getInstance(context)
            val historiDao = db.dao
            MaterialAlertDialogBuilder(context)
                .setMessage(context.getString(R.string.konfirmasi_hapus_item, namaHewan))
                .setPositiveButton(context.getString(R.string.hapus)) { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO) {
                            historiDao.clearData(id)
                        }
                    }
                }
                .setNegativeButton(context.getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }
}