package org.d3if0074.healthyfur.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "histori")
data class HistoriEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var namaPelanggan: String,
    var namaHewan: String,
    var jenisHewan: String,
    var beratHewan: String,
    var jenisLayanan: String,
    var durasi: Int,
    var biaya: Int,
    var ras: String
)
