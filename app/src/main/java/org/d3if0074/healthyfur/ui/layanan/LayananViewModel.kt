package org.d3if0074.healthyfur.ui.layanan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0074.healthyfur.model.InfoLayanan
import org.d3if0074.healthyfur.network.ApiStatus
import org.d3if0074.healthyfur.network.InfoLayananApi
import org.d3if0074.healthyfur.network.UpdateWorker
import java.util.concurrent.TimeUnit

class LayananViewModel: ViewModel() {

    private val data = MutableLiveData<List<InfoLayanan>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO){
            status.postValue(ApiStatus.LOADING)
            try {
                val layanan = InfoLayananApi.service.getInfoLayanan()
                data.postValue(layanan)
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: java.lang.Exception){
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<InfoLayanan>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}