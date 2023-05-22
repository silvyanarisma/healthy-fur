package org.d3if0074.healthyfur.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0074.healthyfur.R
import org.d3if0074.healthyfur.databinding.FragmentMainBinding
import org.d3if0074.healthyfur.db.HealthyFurDb
import org.d3if0074.healthyfur.model.HasilGrooming

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        val db = HealthyFurDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private var durasi: Int = 0
    private var biaya: Int = 0
    private var ras: String = ""
    private var jenisLayanan: String = ""
    private var jenisHewanRg: String = "Anjing"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getHasilGrooming().observe(requireActivity()) {
            actionBtnTambah(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val rasAnjingSpinner: Spinner = binding.spinnerRasAnjing
        ArrayAdapter.createFromResource(
            this.requireContext(), R.array.array_ras_anjing, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            rasAnjingSpinner.adapter = adapter
        }

        val rasKucingSpinner: Spinner = binding.spinnerRasKucing
        ArrayAdapter.createFromResource(
            this.requireContext(), R.array.array_ras_kucing, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            rasKucingSpinner.adapter = adapter
        }

        val jenisLayananSpinner: Spinner = binding.spinnerJenisLayanan
        ArrayAdapter.createFromResource(
            this.requireContext(), R.array.array_jenis_layanan, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            jenisLayananSpinner.adapter = adapter
        }

        binding.btnTambah.setOnClickListener { tambahTransaksi() }
        binding.btnBatal.setOnClickListener { actionBtnBatal() }

        binding.radioGroupJenis.setOnCheckedChangeListener { _, _ ->
            if(binding.rbAnjing.isChecked() == true){
                binding.spinnerRasAnjing.setVisibility(View.VISIBLE)
                binding.spinnerRasKucing.setVisibility(View.INVISIBLE)
                jenisHewanRg = "Anjing"
            }else if(binding.rbKucing.isChecked() == true){
                binding.spinnerRasKucing.setVisibility(View.VISIBLE)
                binding.spinnerRasAnjing.setVisibility(View.INVISIBLE)
                jenisHewanRg = "Kucing"
            }
            setHasil()
        }

        rasKucingSpinner.selectedAnjing {  }
        rasAnjingSpinner.selectedKucing {  }
        jenisLayananSpinner.selectedLayanan {  }
        return binding.root
    }

    private fun tambahTransaksi() {
        val namaPelanggan = binding.namaPelangganInp.text.toString()
        if (TextUtils.isEmpty(namaPelanggan)) {
            Toast.makeText(context, R.string.nama_pelanggan_invalid, Toast.LENGTH_LONG).show()
            return
        }


        val nomorTelepon = binding.nomorTeleponInp.text.toString()
        if (TextUtils.isEmpty(nomorTelepon)) {
            Toast.makeText(context, R.string.nomor_telepon_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val namaHewan = binding.namaHewanInp.text.toString()
        if (TextUtils.isEmpty(namaHewan)) {
            Toast.makeText(context, R.string.nama_hewan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val jenisHewan = binding.radioGroupJenis.checkedRadioButtonId
        if (jenisHewan == -1) {
            Toast.makeText(context, R.string.jenis_hewan_invalid, Toast.LENGTH_LONG).show()
            return
        }


        if (TextUtils.isEmpty(ras)) {
            Toast.makeText(this.requireContext(), R.string.ras_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val warnaHewan = binding.warnaHewanInp.text.toString()
        if (TextUtils.isEmpty(warnaHewan)) {
            Toast.makeText(context, R.string.warna_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val beratHewan = binding.beratHewanInp.text.toString()
        if (TextUtils.isEmpty(beratHewan)) {
            Toast.makeText(context, R.string.berat_hewan_invalid, Toast.LENGTH_LONG).show()
            return
        }


        if (TextUtils.isEmpty(jenisLayanan)) {
            Toast.makeText(context, R.string.jenis_layanan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.setHasilGrooming(namaPelanggan, namaHewan, jenisHewanRg, beratHewan, jenisLayanan, durasi, biaya, ras)
    }

    private fun Spinner.selectedAnjing(action: (position:Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action(position)
                val getRasAnjing = parent?.getItemAtPosition(position).toString()
                ras = getRasAnjing
            }
        }
    }

    private fun Spinner.selectedKucing(action: (position:Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action(position)
                val getRasKucing = parent?.getItemAtPosition(position).toString()
                ras = getRasKucing
            }
        }
    }

    private fun Spinner.selectedLayanan(action: (position:Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action(position)
                val getLayanan = parent?.getItemAtPosition(position).toString()
                jenisLayanan = getLayanan
                setHasil()
            }
        }
    }

    private fun setHasil(){
        val selectedId = binding.radioGroupJenis.checkedRadioButtonId
        val isDog = selectedId == R.id.rbAnjing

        val getJenisLayanan = viewModel.getJenisLayanan(jenisLayanan, isDog)
        durasi = getJenisLayanan.durasi
        biaya = getJenisLayanan.biaya
    }

    private fun actionBtnTambah(result: HasilGrooming?) {
        if (result == null) return
        binding.tvResNamaPelanggan.text = getString(R.string.nama_pelanggan_x, result.namaPelanggan)
        binding.tvResNamaHewan.text = getString(R.string.nama_hewan_x, result.namaHewan)
        binding.tvResJenisHewan.text = getString(R.string.jenis_hewan_x, result.jenisHewan)
        binding.tvResBeratHewan.text = getString(R.string.berat_hewan_x, result.beratHewan)
        binding.tvResLayanan.text = getString(R.string.jenis_layanan_x, result.jenisLayanan)
        binding.tvBiaya.text = getString(R.string.biaya_x, result.biaya.toString())
        binding.tvDurasi.text = getString(R.string.durasi_x, result.durasi.toString())

        binding.tvResNamaPelanggan.setVisibility(View.VISIBLE)
        binding.tvResNamaHewan.setVisibility(View.VISIBLE)
        binding.tvResJenisHewan.setVisibility(View.VISIBLE)
        binding.tvResBeratHewan.setVisibility(View.VISIBLE)
        binding.tvResLayanan.setVisibility(View.VISIBLE)
        binding.tvDurasi.setVisibility(View.VISIBLE)
        binding.tvBiaya.setVisibility(View.VISIBLE)
    }

    private fun actionBtnBatal() {
        binding.tvResNamaPelanggan.setVisibility(View.INVISIBLE)
        binding.tvResNamaHewan.setVisibility(View.INVISIBLE)
        binding.tvResJenisHewan.setVisibility(View.INVISIBLE)
        binding.tvResBeratHewan.setVisibility(View.INVISIBLE)
        binding.tvResLayanan.setVisibility(View.INVISIBLE)
        binding.tvDurasi.setVisibility(View.INVISIBLE)
        binding.tvBiaya.setVisibility(View.INVISIBLE)

        binding.namaPelangganInp.setText("")
        binding.nomorTeleponInp.setText("")
        binding.namaHewanInp.setText("")
        binding.warnaHewanInp.setText("")
        binding.beratHewanInp.setText("")
        viewModel.clearHasilGrooming()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_mainFragment_to_historiFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}