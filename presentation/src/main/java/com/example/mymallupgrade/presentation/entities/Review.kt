package com.example.mymallupgrade.presentation.entities

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */

data class Review(
    var id: String,
    var author: String,
    var content: String? = null
)