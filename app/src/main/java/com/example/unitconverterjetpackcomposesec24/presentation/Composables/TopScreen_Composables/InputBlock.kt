package com.example.unitconverterjetpackcomposesec24.presentation.Composables.TopScreen_Composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterjetpackcomposesec24.data.Util.Converstion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBlock(
	isLandScape: Boolean,
	converstion : Converstion,
	inputText: MutableState<String>,
	modifier : Modifier= Modifier,
	context: Context = LocalContext.current,//to show the Toast
	calculate: (String)->Unit//this function to pass the inputed value to calculate out of the inputField to the Upper Composable.
){
	
	if(isLandScape){// in landscape mode we delete all fillmaxwidth() properties
		Column(modifier=modifier.padding(0.dp,20.dp,0.dp,0.dp)){
			Row(){
				TextField(
					value = inputText.value,
					onValueChange = {
						inputText.value = it
					},
					
					keyboardOptions = KeyboardOptions(
						capitalization = KeyboardCapitalization.None,
						autoCorrect = true,
						keyboardType = KeyboardType.Number
					),
					textStyle = TextStyle(color = Color.Red,fontSize=30.sp)
				)
				
				Text(
					text = converstion.convertFrom,
					fontSize = 24.sp,
					modifier= modifier
						.padding(10.dp, 30.dp, 0.dp, 0.dp)
						
				)
			}
			
			Spacer(modifier = modifier.height(20.dp))
			
			OutlinedButton(
				onClick = {
					if(inputText.value !=""){
						calculate(inputText.value)//we passed the inputed value outside the TextField to the TopScreen to give it th another Composable.
					}
					else{
						Toast.makeText(context,"Enter a Value",Toast.LENGTH_LONG).show()
					}
				},
			) {
				Text(text = "Convert",
					fontSize = 36.sp,
					fontWeight = FontWeight.Bold,
					color = Color.Blue)
			}
		}
	}
	else{
		Column(modifier=modifier.padding(0.dp,20.dp,0.dp,0.dp)){
			Row(modifier=modifier.fillMaxWidth()){
				TextField(
					value = inputText.value,
					onValueChange = {
						inputText.value = it
					},
					modifier=modifier.fillMaxWidth(0.65F),
					keyboardOptions = KeyboardOptions(
						capitalization = KeyboardCapitalization.None,
						autoCorrect = true,
						keyboardType = KeyboardType.Number
					),
					textStyle = TextStyle(color = Color.Red,fontSize=30.sp)
				)
				
				Text(
					text = converstion.convertFrom,
					fontSize = 24.sp,
					modifier= modifier
						.padding(10.dp, 30.dp, 0.dp, 0.dp)
						.fillMaxWidth(0.35F)
				)
			}
			
			Spacer(modifier = modifier.height(20.dp))
			
			OutlinedButton(
				onClick = {
					if(inputText.value !=""){
						calculate(inputText.value)//we passed the inputed value outside the TextField to the TopScreen to give it th another Composable.
					}
					else{
						Toast.makeText(context,"Enter a Value",Toast.LENGTH_LONG).show()
					}
				},
				modifier= modifier.fillMaxWidth(1F)
			) {
				Text(text = "Convert",
					fontSize = 36.sp,
					fontWeight = FontWeight.Bold,
					color = Color.Blue)
			}
		}
	}
	

}