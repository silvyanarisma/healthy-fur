package org.d3if0074.healthyfur.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import org.d3if0074.healthyfur.R
import org.d3if0074.healthyfur.databinding.FragmentDetailBinding
import org.d3if0074.healthyfur.db.HealthyFurDb
import org.d3if0074.healthyfur.db.HistoriEntity
import org.d3if0074.healthyfur.model.hasilGrooming
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment: Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by lazy {
        val db = HealthyFurDb.getInstance(requireContext())
        val factory = DetailViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private var historiEntity: HistoriEntity? = null
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getDataById(args.idHistori).observe(viewLifecycleOwner) {
            detail(it)
            historiEntity = it
        }
        binding.bagikanBtn.setOnClickListener {
            shareData()
        }
    }

    fun detail(histori: HistoriEntity?) {
        if (histori == null) {
            return
        }
        val hasilGrooming = histori.hasilGrooming()
        val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        binding.idDetailTvX.text = histori.id.toString()
        binding.tanggalDetailTvX.text = dateFormatter.format(Date(histori.tanggal))
        binding.namaPelangganDetailTvX.text = hasilGrooming.namaPelanggan
        binding.namaHewanDetailTvX.text = hasilGrooming.namaHewan
        binding.jenisHewanDetailTvX.text = hasilGrooming.jenisHewan
        binding.rasDetailTvX.text = hasilGrooming.ras
        binding.beratDetailTvX.text = hasilGrooming.beratHewan + " Kg"
        binding.jenisLayananDetailTvX.text = hasilGrooming.jenisLayanan
        binding.durasiDetailTvX.text = hasilGrooming.durasi.toString() + " menit"
        binding.biayaDetailTvX.text = "Rp " + hasilGrooming.biaya.toString()
    }

    private fun shareData() {
        val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        if (historiEntity == null) {
            return
        }
        val id = historiEntity?.id.toString()
        val tanggalEntity: Long = historiEntity?.tanggal ?: 0
        val tanggal = dateFormatter.format(Date(tanggalEntity))
        val namaPelanggan = historiEntity?.namaPelanggan
        val namaHewan = historiEntity?.namaHewan
        val jenisHewan = historiEntity?.jenisHewan
        val ras = historiEntity?.ras
        val berat = historiEntity?.beratHewan
        val jenisLayanan = historiEntity?.jenisLayanan
        val durasi = historiEntity?.durasi.toString()
        val biaya = historiEntity?.biaya.toString()
        val message = getString(
            R.string.bagikan_template,
            id,
            tanggal,
            namaPelanggan,
            namaHewan,
            jenisHewan,
            ras,
            berat,
            jenisLayanan,
            durasi,
            biaya
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}