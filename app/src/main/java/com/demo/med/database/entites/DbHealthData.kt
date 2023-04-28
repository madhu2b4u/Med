package com.demo.med.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_healthdata")
data class DbHealthData(
    @PrimaryKey val id: Int,
    val news: String
)