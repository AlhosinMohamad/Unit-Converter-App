package com.example.unitconverterjetpackcomposesec24.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unitconverterjetpackcomposesec24.data.repository.ConversionRepository

class ConverterViewModelFactory(
	private val repository : ConversionRepository
) : ViewModelProvider.NewInstanceFactory(){
	override fun <T : ViewModel> create(modelClass : Class<T>) : T {
		return ConverterViewModel(repository) as T
	}
}