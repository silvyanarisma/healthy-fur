package org.d3if0074.healthyfur.ui.layanan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0074.healthyfur.model.InfoLayanan
import org.d3if0074.healthyfur.network.InfoLayananApi

class LayananViewModel: ViewModel() {
    private val data =MutableLiveData<List<InfoLayanan>>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val layanan = InfoLayananApi.service.getInfoLayanan()
                data.postValue(layanan)
                Log.d("InfoLayananViewModel", "success: ${layanan}")
            } catch (e: java.lang.Exception){
                Log.d("InfoLayananViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<InfoLayanan>> = data
}