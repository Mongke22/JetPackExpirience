package com.example.jetpackexpirience.domain.usecases

import com.example.jetpackexpirience.domain.entity.GameSettings
import com.example.jetpackexpirience.domain.entity.Level
import com.example.jetpackexpirience.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}