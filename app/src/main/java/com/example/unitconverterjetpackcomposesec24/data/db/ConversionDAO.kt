package com.example.unitconverterjetpackcomposesec24.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionDAO {
	
	@Query("Select * From result_table")
	fun getAllResults(): Flow<List<ConversionResult>>//room automatically execute this fun in a separate coroutine without suspend keyWord
	
	@Insert
	suspend fun insertConversionResult(result : ConversionResult)
	
	@Delete
	suspend fun deleteConversionResult(result : ConversionResult)
	
	@Query("Delete From result_table")
	suspend fun deleteAllResults()
}