package com.example.unitconverterjetpackcomposesec24.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table")
data class ConversionResult(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "result_id")
	val id: Int,
	@ColumnInfo(name = "result_messag1")
	val messag1:String,
	@ColumnInfo(name = "result_messag2")
	val message2: String
)
