package com.example.semestralnapraca_vamz

data class Spell(
    val drawableResId: Int,
    val cooldown: Long,
    val spellSlot: String,
    var cooldownLeft: Long = 0
)

