package org.d3if0074.healthyfur.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0074.healthyfur.model.HasilGrooming
import org.d3if0074.healthyfur.model.JenisLayanan
import org.d3if0074.healthyfur.model.JenisLayananList

class MainViewModel: ViewModel() {
    private  val hasilGrooming = MutableLiveData<HasilGrooming?>()

    fun getHasilGrooming(): LiveData<HasilGrooming?> = hasilGrooming

    fun setHasilGrooming(namaPelanggan: String, namaHewan: String, jenisHewan: String, beratHewan: String, jenisLayanan: String, durasi: Int, biaya: Int, ras: String){
        hasilGrooming.value = HasilGrooming(namaPelanggan, namaHewan, jenisHewan, beratHewan, jenisLayanan, durasi, biaya, ras)
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