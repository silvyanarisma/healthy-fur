package org.d3if0074.healthyfur.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0074.healthyfur.db.HistoriDao
import org.d3if0074.healthyfur.db.HistoriEntity
import org.d3if0074.healthyfur.model.HasilGrooming
import org.d3if0074.healthyfur.model.JenisLayanan
import org.d3if0074.healthyfur.model.JenisLayananList

class MainViewModel(
    private val db: HistoriDao
): ViewModel() {
    private  val hasilGrooming = MutableLiveData<HasilGrooming?>()

    fun getHasilGrooming(): LiveData<HasilGrooming?> = hasilGrooming

    fun setHasilGrooming(namaPelanggan: String, namaHewan: String, jenisHewan: String, beratHewan: String, jenisLayanan: String, durasi: Int, biaya: Int, ras: String){
        val historiData = HistoriEntity(
            namaPelanggan = namaPelanggan,
            namaHewan = namaHewan,
            jenisHewan = jenisHewan,
            beratHewan = beratHewan,
            jenisLayanan = jenisLayanan,
            durasi = durasi,
            biaya = biaya,
            ras = ras
        )
        hasilGrooming.value = HasilGrooming(namaPelanggan, namaHewan, jenisHewan, beratHewan, jenisLayanan, durasi, biaya, ras)
        viewModelScope.launch { withContext(Dispatchers.IO) {
            db.insertHistori(historiData)
        } }
    }

    fun clearHasilGrooming() {
        hasilGrooming.value = null
    }

    fun getJenisLayanan(layanan: String, isDog: Boolean): JenisLayanan {
        var jenisLayanan: JenisLayanan = JenisLayanan("", "", 0, 0)
        if (isDog == true) {
            val getLayanan = JenisLayananList().getDataAnjing()
            for (data in getLayanan) {
                if (data.namaLayanan == layanan) {
                    jenisLayanan = data
                    break
                }
            }
        } else {
            val getLayanan = JenisLayananList().getDataKucing()
            for (data in getLayanan) {
                if (data.namaLayanan == layanan) {
                    jenisLayanan = data
                    break
                }
            }
        }
        return jenisLayanan
    }
}