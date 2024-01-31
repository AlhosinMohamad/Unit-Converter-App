package com.example.unitconverterjetpackcomposesec24.data.repository

import com.example.unitconverterjetpackcomposesec24.data.db.ConversionResult
import kotlinx.coroutines.flow.Flow

interface ConversionRepository  {
	
	fun getSavedResults():Flow<List<ConversionResult>>
	
	suspend fun insertResult(result : ConversionResult)
	
	suspend fun deleteResult(result : ConversionResult)
	
	suspend fun deleteAllResults()
}