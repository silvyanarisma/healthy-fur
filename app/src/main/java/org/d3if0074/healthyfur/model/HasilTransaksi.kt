package org.d3if0074.healthyfur.model

import org.d3if0074.healthyfur.db.HistoriEntity

fun HistoriEntity.hasilGrooming(): HasilGrooming {
    return HasilGrooming(
        namaPelanggan,
        namaHewan,
        jenisHewan,
        beratHewan,
        jenisLayanan,
        durasi,
        biaya,
        ras
    )
}