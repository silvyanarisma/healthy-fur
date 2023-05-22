package org.d3if0074.healthyfur.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.d3if0074.healthyfur.db.HistoriDao
import org.d3if0074.healthyfur.db.HistoriEntity

class DetailViewModel(
    private val db: HistoriDao
): ViewModel()  {
    fun getDataById(id: Long): LiveData<HistoriEntity> {
        return db.getHistoriById(id)
    }
}