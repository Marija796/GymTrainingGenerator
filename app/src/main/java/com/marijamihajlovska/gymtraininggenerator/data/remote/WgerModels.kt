package com.marijamihajlovska.gymtraininggenerator.data.remote

import com.google.gson.annotations.SerializedName

data class WgerPagedResponse(
    val count: Int,
    val next: String?,
    val results: List<WgerExerciseInfo>
)

data class WgerExerciseInfo(
    val id: Int,
    val category: WgerCategory,
    val muscles: List<WgerMuscle>,
    val equipment: List<WgerEquipment>,
    val translations: List<WgerTranslation>
)

data class WgerCategory(
    val id: Int,
    val name: String
)

data class WgerMuscle(
    val id: Int,
    @SerializedName("name_en") val nameEn: String
)

data class WgerEquipment(
    val id: Int,
    val name: String
)

data class WgerTranslation(
    val language: Int,
    val name: String,
    val description: String
)
