package org.d3if0074.healthyfur.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if0074.healthyfur.db.HistoriDao

class HistoriViewModel(private val db: HistoriDao) : ViewModel() {
    val data = db.getHistori()
}