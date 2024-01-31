package com.example.unitconverterjetpackcomposesec24.presentation.Composables.HistoryScreen_Composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryItem(
	messag1:String,
	messag2:String,
	modifier : Modifier = Modifier,
	onClose: ()->Unit// just an empty fun to close the history lazyColumn
){
	Row(
		modifier= modifier
			.fillMaxWidth()
			.border(border = BorderStroke(0.5.dp, Color.Gray)),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	){
		Column {
			Text(text = messag1, fontSize = 20.sp)
			
			Text(text = messag2, fontSize = 20.sp, color = Color.Blue, fontWeight = FontWeight.Bold)
		}
		
		IconButton(onClick = {
			onClose ()
		}) {
			Icon(Icons.Filled.Close, contentDescription = "close")
		}
	}
}