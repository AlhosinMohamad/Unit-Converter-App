import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverterjetpackcomposesec24.presentation.Composables.HistoryScreen_Composables.HistoryScreen
import com.example.unitconverterjetpackcomposesec24.presentation.Composables.TopScreen_Composables.TopScreen
import com.example.unitconverterjetpackcomposesec24.presentation.ViewModel.ConverterViewModel

@Composable
fun BaseScreen(
	modifier : Modifier=Modifier,//Recommanded best practice, as it increases Reusability
	converterViewModel : ConverterViewModel = viewModel()//you should pass and initiate viewModel Object for the Basescreen Always,
                                                         // so we added the first dependenca to use lifecycle and the we call this viewModel() function to initiate it.
){
	//1- Get the Convertion List from the View Model,. it will not change so no need to make it as a state.
	val convertionList=converterViewModel.getConverstions()
	
	
	//2- Get Top- and HistoryScreens into the BaseScreen
	Column(
		modifier = modifier.padding(30.dp)
	){
	
		TopScreen(list = convertionList)
		
		Spacer(modifier = modifier.height(20.dp))
		
		HistoryScreen()
	}
}