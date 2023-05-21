package org.d3if0074.healthyfur.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0074.healthyfur.db.HistoriDao
import org.d3if0074.healthyfur.ui.histori.HistoriViewModel

class MainViewModelFactory(
    private val db: HistoriDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}