package com.example.unitconverterjetpackcomposesec24.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverterjetpackcomposesec24.data.Util.Converstion
import com.example.unitconverterjetpackcomposesec24.data.db.ConversionResult
import com.example.unitconverterjetpackcomposesec24.data.repository.ConversionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(
	private val converterRepository : ConversionRepository
) : ViewModel() {
	
	fun getConverstions()= listOf(
		Converstion(1,"Pounds to Kilograms","lbs","kg",0.453592),
		Converstion(2,"Kilograms to Pounds","kg","lbs",2.20462),
		Converstion(3,"Yards to Meters","yd","m",0.9144),
		Converstion(4,"Meters to Yards","m","yd",1.09361),
		Converstion(5,"Miles to Kilometers","mi","km",1.60934),
		Converstion(6,"Kilometers to Miles","km","mi",0.621371)
	)
	
	fun addResult(message1 : String, message2 : String){
		viewModelScope.launch(Dispatchers.IO) {
			converterRepository.insertResult(ConversionResult(0,message1,message2))
		}
	}
	
	val resultList= converterRepository.getSavedResults()
	
	
	
}