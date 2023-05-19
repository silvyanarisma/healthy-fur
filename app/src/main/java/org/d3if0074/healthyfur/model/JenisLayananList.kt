package org.d3if0074.healthyfur.model

class JenisLayananList {
    fun getDataAnjing(): List<JenisLayanan> {
        return listOf(
            JenisLayanan("Grooming sehat", "Anjing", 45, 50000),
            JenisLayanan("Grooming kutu", "Anjing", 55, 70000),
            JenisLayanan("Grooming jamur", "Anjing", 45, 60000),
            JenisLayanan("Grooming lengkap", "Anjing", 70, 100000)
        )
    }

    fun getDataKucing(): List<JenisLayanan> {
        return listOf(
            JenisLayanan("Grooming sehat", "Kucing", 30, 35000),
            JenisLayanan("Grooming kutu", "Kucing", 40, 50000),
            JenisLayanan("Grooming jamur", "Kucing", 40, 55000),
            JenisLayanan("Grooming lengkap", "Kucing", 60, 70000)
        )
    }

    fun getDataAll(): List<JenisLayanan> {
        return listOf(
            JenisLayanan("Grooming sehat", "Anjing", 45, 50000),
            JenisLayanan("Grooming kutu", "Anjing", 55, 70000),
            JenisLayanan("Grooming jamur", "Anjing", 45, 60000),
            JenisLayanan("Grooming lengkap", "Anjing", 70, 100000),

            JenisLayanan("Grooming sehat", "Kucing", 30, 35000),
            JenisLayanan("Grooming kutu", "Kucing", 40, 50000),
            JenisLayanan("Grooming jamur", "Kucing", 40, 55000),
            JenisLayanan("Grooming lengkap", "Kucing", 60, 70000)
        )
    }
}