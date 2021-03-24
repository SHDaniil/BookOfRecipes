package com.daniil.bookofrecipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_table")
data class Recipe(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val images: ArrayList<String>,
    val lastUpdated: Long,
    val description: String? = "",
    val instructions: String,
    val difficulty: Int
)
