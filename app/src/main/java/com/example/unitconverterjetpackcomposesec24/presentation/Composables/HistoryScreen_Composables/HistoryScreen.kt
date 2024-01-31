package com.example.unitconverterjetpackcomposesec24.presentation.Composables.HistoryScreen_Composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.unitconverterjetpackcomposesec24.data.db.ConversionResult

@Composable
fun HistoryScreen(
	historyList : State<List<ConversionResult>>,
	modifier : Modifier = Modifier,
	onClosTask: (ConversionResult)-> Unit
) {
	HistoryList(historyList = historyList, onCloseTask = {
		item->
		onClosTask(item)
	})
}