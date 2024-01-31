package com.example.unitconverterjetpackcomposesec24.data.repository

import com.example.unitconverterjetpackcomposesec24.data.db.ConversionDAO
import com.example.unitconverterjetpackcomposesec24.data.db.ConversionResult
import kotlinx.coroutines.flow.Flow

class ConversionRepositoryIMPL(
	private val conversionDAO : ConversionDAO
):ConversionRepository {
	override fun getSavedResults() : Flow<List<ConversionResult>> {
		return conversionDAO.getAllResults()
	}
	
	override suspend fun insertResult(result : ConversionResult) {
		conversionDAO.insertConversionResult(result)
	}
	
	override suspend fun deleteResult(result : ConversionResult) {
		conversionDAO.deleteConversionResult(result)
	}
	
	override suspend fun deleteAllResults() {
		conversionDAO.deleteAllResults()
	}
}