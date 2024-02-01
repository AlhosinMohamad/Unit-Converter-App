package com.example.unitconverterjetpackcomposesec24.di

import android.app.Application
import androidx.room.Room
import com.example.unitconverterjetpackcomposesec24.data.db.ConversionDB
import com.example.unitconverterjetpackcomposesec24.data.repository.ConversionRepository
import com.example.unitconverterjetpackcomposesec24.data.repository.ConversionRepositoryIMPL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
	@Singleton
	fun provideConversioDB(app:Application) : ConversionDB{
		return Room.databaseBuilder(
			app,
			ConversionDB::class.java,
			"conversion_data_base"
		).build()
	}
	
	@Provides
	@Singleton
	fun provideConverterRepository(db: ConversionDB) : ConversionRepository{
		return ConversionRepositoryIMPL(db.conversionDAO)
	}
	
}