import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverterjetpackcomposesec24.presentation.Composables.HistoryScreen_Composables.HistoryScreen
import com.example.unitconverterjetpackcomposesec24.presentation.Composables.TopScreen_Composables.TopScreen
import com.example.unitconverterjetpackcomposesec24.presentation.ViewModel.ConverterViewModel
import com.example.unitconverterjetpackcomposesec24.presentation.ViewModel.ConverterViewModelFactory

@Composable
fun BaseScreen(
	modifier : Modifier=Modifier,//Recommanded best practice, as it increases Reusability
	factory:ConverterViewModelFactory,
	converterViewModel : ConverterViewModel = viewModel(factory= factory)//you should pass and initiate viewModel Object for the Basescreen Always,
                                                         // so we added the first dependenca to use lifecycle and the we call this viewModel() function to initiate it.
){
	//1- Get the Convertion List from the View Model,. it will not change so no need to make it as a state.
	val convertionList=converterViewModel.getConverstions()
	
	val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())//collectAsState to convert the Flow to a State
	
	
	//in case Configuration changes
	val configuration = LocalConfiguration.current
	var isLandScape by remember {
		mutableStateOf(false)
	}
	when(configuration.orientation){
		Configuration.ORIENTATION_LANDSCAPE ->{
			isLandScape = true
			//2- Get Top- and HistoryScreens into the BaseScreen
			Row(
				modifier = modifier
					.padding(30.dp)
					.fillMaxSize(),
				horizontalArrangement = Arrangement.SpaceAround
			){
				
				TopScreen(
					isLandScape=isLandScape,
					list = convertionList,
					converterViewModel.selectedConversionfromMenu,
					converterViewModel.inputText,
					converterViewModel.typedValue_fromTextField
				){
						messag1,messag2->
					converterViewModel.addResult(messag1,messag2)
					//we have taken those values out of TopScreen up to the ViewModel to save Them in DB
				}
				
				Spacer(modifier = modifier.height(10.dp))
				
				HistoryScreen(historyList, onClosTask =
				{
						item->
					converterViewModel.removeResult(item)
				}, onClearAllTask = {
					converterViewModel.clearAll()
				}
				)
			}
		}
		else ->{
			isLandScape = false
			//2- Get Top- and HistoryScreens into the BaseScreen
			Column(
				modifier = modifier.padding(30.dp)
			){
				
				TopScreen(
					isLandScape=isLandScape,
					list = convertionList,
					converterViewModel.selectedConversionfromMenu,
					converterViewModel.inputText,
					converterViewModel.typedValue_fromTextField
				){
						messag1,messag2->
					converterViewModel.addResult(messag1,messag2)
					//we have taken those values out of TopScreen up to the ViewModel to save Them in DB
				}
				
				Spacer(modifier = modifier.height(20.dp))
				
				HistoryScreen(historyList, onClosTask =
				{
						item->
					converterViewModel.removeResult(item)
				}, onClearAllTask = {
					converterViewModel.clearAll()
				}
				)
			}
		}
	}
	
	
}