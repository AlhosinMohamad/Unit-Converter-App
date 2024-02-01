package com.example.unitconverterjetpackcomposesec24.presentation.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
	
	//this state to catch the current seleted unit from dropdownmenu and save it to pass it to the Input Block To calculate
	val selectedConversionfromMenu: MutableState<Converstion?> = mutableStateOf(null)
	
	//this state to catch the Inputed value
	val inputText: MutableState<String> = mutableStateOf("")
	
	//this state to save the Inputed value to use in Calculation
	val typedValue_fromTextField: MutableState<String> = mutableStateOf("0.0")
	
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
	
	fun removeResult(result: ConversionResult){
		viewModelScope.launch(Dispatchers.IO) {
			converterRepository.deleteResult(result)
		}
	}
	
	fun clearAll(){
		viewModelScope.launch (Dispatchers.IO){
			converterRepository.deleteAllResults()
		}
	}
	
}