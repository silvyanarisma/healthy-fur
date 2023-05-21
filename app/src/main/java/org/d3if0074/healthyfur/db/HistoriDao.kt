package org.d3if0074.healthyfur.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoriDao {

    @Insert
    fun insertHistori(histori: HistoriEntity)

    @Query("SELECT * FROM histori ORDER BY id DESC LIMIT 1")
    fun getLastHistori(): LiveData<HistoriEntity?>

    @Query("SELECT * FROM histori ORDER BY id DESC")
    fun getHistori(): LiveData<List<HistoriEntity?>>

    @Query("DELETE FROM histori")
    fun clearAllData()

    @Query("DELETE FROM histori WHERE id=:id")
    fun clearData(id:Long)
}