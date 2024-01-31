package com.example.unitconverterjetpackcomposesec24.presentation.Composables.TopScreen_Composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.unitconverterjetpackcomposesec24.data.Util.Converstion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(list : List<Converstion>) {
	
	//this state to catch the current seleted unit from dropdownmenu and save it to pass it to the Input Block To calculate
	val selectedConversionfromMenu: MutableState<Converstion?> = remember {
		mutableStateOf(null)
	}
	val inputText: MutableState<String> = remember {
		mutableStateOf("")
	}
	
	val typedValue_fromTextField: MutableState<String> = remember {
		mutableStateOf("0.0")
	}
	
	
	
	ConverstionMenu(conversionList = list){
		selectedConversionfromMenu.value=it//here we give the selected state up to the TopScreen then pass it to the InputBlock
	}
	
	selectedConversionfromMenu.value?.let { 
		InputBlock(converstion = it, inputText = inputText){
			inputedTextToCalculate_FromInputBlockComposable ->
			typedValue_fromTextField.value = inputedTextToCalculate_FromInputBlockComposable
			//we give the inputed number from the InputBlock up to the TopScreen  to calculate it.
			//Log.i("MYTAG","User typed $inputedTextToCalculate_FromInputBlockComposable")
		}
	}
	
	if (typedValue_fromTextField.value != "0.0"){
		val input= typedValue_fromTextField.value.toDouble()
		val multiply_By = selectedConversionfromMenu.value!!.multiply
		val result = input * multiply_By
		
		//rounding off the result to 4 decimal places
		val df = DecimalFormat("#.####")
		df.roundingMode = RoundingMode.DOWN
		val roundResult = df.format(result)
		
		val message1= "${typedValue_fromTextField.value}  ${selectedConversionfromMenu.value!!.convertFrom} is equal to: "
		val message2= "$roundResult ${selectedConversionfromMenu.value!!.convertTo}"
		ResulrBlock(messag1 = message1, messag2 = message2)
	}
}