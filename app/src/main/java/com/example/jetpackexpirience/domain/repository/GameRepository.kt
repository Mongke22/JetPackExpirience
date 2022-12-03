package com.example.jetpackexpirience.domain.repository

import com.example.jetpackexpirience.domain.entity.GameSettings
import com.example.jetpackexpirience.domain.entity.Level
import com.example.jetpackexpirience.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level) : GameSettings
}