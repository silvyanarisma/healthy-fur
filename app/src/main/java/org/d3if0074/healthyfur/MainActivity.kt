package org.d3if0074.healthyfur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import org.d3if0074.healthyfur.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var durasi: Int = 0
    private var biaya: Int = 0
    private var ras: String = ""
    private var jenisLayanan: String = ""
    private var jenisHewanRg: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rasAnjingSpinner: Spinner = binding.spinnerRasAnjing
        ArrayAdapter.createFromResource(
            this, R.array.array_ras_anjing, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            rasAnjingSpinner.adapter = adapter
        }

        val rasKucingSpinner: Spinner = binding.spinnerRasKucing
        ArrayAdapter.createFromResource(
            this, R.array.array_ras_kucing, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            rasKucingSpinner.adapter = adapter
        }

        val jenisLayananSpinner: Spinner = binding.spinnerJenisLayanan
        ArrayAdapter.createFromResource(
            this, R.array.array_jenis_layanan, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            jenisLayananSpinner.adapter = adapter
        }

        binding.btnTambah.setOnClickListener { tambahTransaksi() }
        binding.btnBatal.setOnClickListener { actionBtnBatal() }

        rasKucingSpinner.selectedAnjing {  }
        rasAnjingSpinner.selectedKucing {  }
        jenisLayananSpinner.selectedLayanan {  }

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
        }

    }

    private fun tambahTransaksi() {
        val namaPelanggan = binding.namaPelangganInp.text.toString()
        if (TextUtils.isEmpty(namaPelanggan)) {
            Toast.makeText(this, R.string.nama_pelanggan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        binding.tvResNamaPelanggan.text = getString(R.string.nama_pelanggan_x, namaPelanggan)

        val nomorTelepon = binding.nomorTeleponInp.text.toString()
        if (TextUtils.isEmpty(nomorTelepon)) {
            Toast.makeText(this, R.string.nomor_telepon_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val namaHewan = binding.namaHewanInp.text.toString()
        if (TextUtils.isEmpty(namaHewan)) {
            Toast.makeText(this, R.string.nama_hewan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        binding.tvResNamaHewan.text = getString(R.string.nama_hewan_x, namaHewan)

        val jenisHewan = binding.radioGroupJenis.checkedRadioButtonId
        if (jenisHewan == -1) {
            Toast.makeText(this, R.string.jenis_hewan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        binding.tvResJenisHewan.text = getString(R.string.jenis_hewan_x, jenisHewanRg)

        if (TextUtils.isEmpty(ras)) {
            Toast.makeText(this, R.string.ras_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val warnaHewan = binding.warnaHewanInp.text.toString()
        if (TextUtils.isEmpty(warnaHewan)) {
            Toast.makeText(this, R.string.warna_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val beratHewan = binding.beratHewanInp.text.toString()
        if (TextUtils.isEmpty(beratHewan)) {
            Toast.makeText(this, R.string.berat_hewan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        binding.tvResBeratHewan.text = getString(R.string.berat_hewan_x, beratHewan)

        if (TextUtils.isEmpty(jenisLayanan)) {
            Toast.makeText(this, R.string.jenis_layanan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        binding.tvResLayanan.text = getString(R.string.jenis_hewan_x, jenisLayanan)

        binding.tvBiaya.text = getString(R.string.biaya_x, biaya.toString())
        binding.tvDurasi.text = getString(R.string.durasi_x, durasi.toString())

        actionBtnTambah()
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
                if(getLayanan == "Grooming sehat"){
                    durasi = 30
                    biaya = 35000
                }else if(getLayanan == "Grooming kutu"){
                    durasi = 40
                    biaya = 50000
                }else if(getLayanan == "Grooming jamur"){
                    durasi = 40
                    biaya = 50000
                }else if(getLayanan == "Grooming lengkap") {
                    durasi = 60
                    biaya = 70000
                }
            }
        }
    }

    private fun actionBtnTambah() {
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
    }
}