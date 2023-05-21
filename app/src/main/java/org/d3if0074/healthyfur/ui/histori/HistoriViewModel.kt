package org.d3if0074.healthyfur.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0074.healthyfur.db.HistoriDao

class HistoriViewModel(private val db: HistoriDao) : ViewModel() {
    val data = db.getHistori()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearAllData()
        }
    }
}