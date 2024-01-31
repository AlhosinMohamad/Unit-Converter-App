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

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		val dao = ConversionDB.getInstance(application).conversionDAO
		val repositoryIMPL= ConversionRepositoryIMPL(dao)
		val factory = ConverterViewModelFactory(repositoryIMPL)
		
		
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

