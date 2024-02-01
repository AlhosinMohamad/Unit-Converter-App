package com.example.unitconverterjetpackcomposesec24.presentation.Composables.TopScreen_Composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.unitconverterjetpackcomposesec24.data.Util.Converstion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverstionMenu(
	isLandScape: Boolean,
	conversionList:List<Converstion>,
	modifier : Modifier=Modifier,
	convert:(Converstion)->Unit//this function to pass the current seleted unit from dropdownmenu to the Input Block To calculate
){

	//1- States in this Screen, *remember: to save the last value of the state to not reassign the initial value when recomposion.
	// 2- we use displayingText by rememberSaveable to save the value when ConfigurationChanges happen
	// and beacause displayingText it is not realted to anthor Composables Logic or UI as in TopScreen States
	var displayingText by rememberSaveable { mutableStateOf("Select The Conversion Type") }//first text Shown on the dropdown menu
	var textFieldSize by remember {mutableStateOf(Size.Zero)}//To assign the dropdown the same width as textField
	var expanded by remember {mutableStateOf(false)}
	
	val icon=if (expanded)
		           Icons.Filled.KeyboardArrowUp
	           else
		          Icons.Filled.KeyboardArrowDown
	
	
	//2-
	Column {
	if (isLandScape){// in landscape mode we delete all fillmaxwidth() properties
		OutlinedTextField(
			value = displayingText,
			onValueChange = {displayingText = it},
			textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
			modifier= modifier
				.onGloballyPositioned { cordinates ->
					textFieldSize = cordinates.size.toSize()
				},
			label = {Text(text = "Convertion Type")},
			trailingIcon = {
				Icon(icon, contentDescription = "icon",
					modifier.clickable {
						expanded =! expanded
					})
			},
			readOnly = true
		)
	}else{
		OutlinedTextField(
			value = displayingText,
			onValueChange = {displayingText = it},
			textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
			modifier= modifier
				.fillMaxWidth()// here is the differnece when Landscape is false
				.onGloballyPositioned { cordinates ->
					textFieldSize = cordinates.size.toSize()
				},
			label = {Text(text = "Convertion Type")},
			trailingIcon = {
				Icon(icon, contentDescription = "icon",
					modifier.clickable {
						expanded =! expanded
					})
			},
			readOnly = true
		)
	}
	//2.1
	
	
	//2.2 The Dropdown Menu with its Items
	DropdownMenu(
		expanded = expanded,
		onDismissRequest = { expanded= false },
		modifier= modifier.width(
			with(LocalDensity.current){
				textFieldSize.width.toDp()
			}
		)
	) {
		conversionList.forEach {conversion->
			DropdownMenuItem(
				text = { Text(text = conversion.description, fontSize = 24.sp, fontWeight = FontWeight.Bold) },
				onClick = {
					displayingText=conversion.description
					expanded=false
					convert(conversion)//this to pass the current seleted unit to the Input Block To calculate
				})
		}
	}
	}
	
}