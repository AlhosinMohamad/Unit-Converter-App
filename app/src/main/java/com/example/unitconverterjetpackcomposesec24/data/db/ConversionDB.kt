package com.example.unitconverterjetpackcomposesec24.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConversionResult::class], version = 1)
abstract class ConversionDB :RoomDatabase(){
	
	abstract val conversionDAO:ConversionDAO
	
	// we do not need this code anymore because we used it in Hilt DI
	/*companion object{
		@Volatile
		private var Instance:ConversionDB?=null
		fun getInstance(context : Context):ConversionDB{
			
			synchronized(this){
				var instance= Instance
				if(instance == null){
					instance= Room.databaseBuilder(
						context.applicationContext,
						ConversionDB::class.java,
						"conversion_data_base"
					).build()
				}
				return instance
			}
			
			
		}
	}*/
}