package com.gb.android.lesson2d.model

import com.google.gson.annotations.SerializedName

class Meanings(
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("imageUrl") val imageUrl: String?
)
