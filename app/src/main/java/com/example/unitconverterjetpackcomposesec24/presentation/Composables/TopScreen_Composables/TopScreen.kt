package com.example.unitconverterjetpackcomposesec24.presentation.Composables.TopScreen_Composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.unitconverterjetpackcomposesec24.data.Util.Converstion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
	isLandScape:Boolean,
	list : List<Converstion>,
	selectedConversionfromMenu: MutableState<Converstion?>,
	inputText: MutableState<String>,
	typedValue_fromTextField: MutableState<String>,
	save: (String,String)->Unit//this to allow us to pass out tow value which are m1 ,m2 tup to BaseScreen to ViewModel to save them to DB
) {
	
	//1- we do not need to use local state any more in Order of ConfigurationChanges happen, so we save the in view model and pass the as parameter.
	//this state to catch the current seleted unit from dropdownmenu and save it to pass it to the Input Block To calculate
	//val selectedConversionfromMenu: MutableState<Converstion?> = remember { mutableStateOf(null) }
	//val inputText: MutableState<String> = rememberremember { mutableStateOf("") }
	//val typedValue_fromTextField: MutableState<String> = remember { mutableStateOf("0.0") }
	
	var toSave by remember {
		mutableStateOf(false)
	}//to prevent more than one save to the result in DB when Configuration Changes happen
	
	Column (
		modifier = Modifier.verticalScroll(rememberScrollState())
	){
		ConverstionMenu(
			isLandScape = isLandScape,
			conversionList = list){
			selectedConversionfromMenu.value=it//here we give the selected state up to the TopScreen then pass it to the InputBlock
			
			typedValue_fromTextField.value="0.0"//to avoid Duplicate values, with new Conversion we make the TextField value zero
		}
		
		selectedConversionfromMenu.value?.let {
			InputBlock(
				isLandScape = isLandScape,
				converstion = it,
				inputText = inputText){
					
					inputedTextToCalculate_FromInputBlockComposable ->
				typedValue_fromTextField.value = inputedTextToCalculate_FromInputBlockComposable
				toSave = true
				//1- we give the inputed number from the InputBlock up to the TopScreen  to calculate it.
				//2-  toSave= true to mark this result as new and save it in DB
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
			
			if(toSave == true){
				save(message1,message2)//pass the values up to baseScreen to ViewModel To save Them in DB
				toSave = false
			}
			
			
			ResultBlock(messag1 = message1, messag2 = message2)
		}
	}
	
	
	
}