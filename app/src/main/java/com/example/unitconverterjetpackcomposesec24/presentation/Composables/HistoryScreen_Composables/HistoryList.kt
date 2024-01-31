package com.example.unitconverterjetpackcomposesec24.presentation.Composables.HistoryScreen_Composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.unitconverterjetpackcomposesec24.data.db.ConversionResult

@Composable
fun HistoryList(
	historyList : State<List<ConversionResult>>,
	onCloseTask: (ConversionResult) -> Unit,
	modifier : Modifier = Modifier
){
	
		LazyColumn {
			items(
				items = historyList.value,
				key = {item ->  item.id}
			){
				item ->
				HistoryItem(messag1 = item.messag1, messag2 = item.message2) {
					onCloseTask(item)
				}
			}
		}
	
}