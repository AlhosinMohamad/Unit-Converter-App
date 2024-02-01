package com.example.unitconverterjetpackcomposesec24

import BaseScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverterjetpackcomposesec24.data.db.ConversionDB
import com.example.unitconverterjetpackcomposesec24.data.repository.ConversionRepositoryIMPL
import com.example.unitconverterjetpackcomposesec24.presentation.ViewModel.ConverterViewModelFactory
import com.example.unitconverterjetpackcomposesec24.ui.theme.UnitConverterJetpackComposeSec24Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	//1- Hilt Injection for factory Class
	@Inject
	lateinit var factory : ConverterViewModelFactory
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		
		//1- this are the main dependencies int the app, factory-> ConversionRespository -> dao from Database, so better to use Hilt
		
		//2- factory is a class not an interface or a service so we can Inject it directly by using @Inject constructor
		
		//3- Conversion Respository is an interface so we should make a provide function for it inside ConverstionApp
		
		//4- ConversionDatabase is an abstract class so we make a provide function for it in ConversionApp
		
		//5- we get the dao object from the Injected ConversionDB
		
		//val dao = ConversionDB.getInstance(application).conversionDAO
		//val repositoryIMPL= ConversionRepositoryIMPL(dao)
		//val factory = ConverterViewModelFactory(repositoryIMPL)
		
		setContent {
			UnitConverterJetpackComposeSec24Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					BaseScreen(factory=factory)
				}
			}
		}
	}
}

