package com.hakankaraotcu.focusquest.di

import android.app.Application
import androidx.room.Room
import com.hakankaraotcu.focusquest.core.data.local.AppDatabase
import com.hakankaraotcu.focusquest.feature_profile.data.repository.ProfileRepositoryImpl
import com.hakankaraotcu.focusquest.feature_quest.data.repository.QuestRepositoryImpl
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.AddXpToProfile
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.CalculateLevelFromXp
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.GetProfile
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.UpsertQuest
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.GetQuest
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.GetQuests
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.ProfileUseCases
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.UpdateProfileLevel
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.UpdateProfileWithQuest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuestRepository(db: AppDatabase): QuestRepository {
        return QuestRepositoryImpl(db.questDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(db: AppDatabase): ProfileRepository {
        return ProfileRepositoryImpl(db.profileDao)
    }

    @Provides
    @Singleton
    fun provideQuestUseCases(repository: QuestRepository): QuestUseCases {
        return QuestUseCases(
            getQuests = GetQuests(repository),
            getQuest = GetQuest(repository),
            upsertQuest = UpsertQuest(repository),
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        val calculateLevelFromXp = CalculateLevelFromXp()
        val addXpToProfile = AddXpToProfile(repository)
        val updateProfileLevel = UpdateProfileLevel(repository, calculateLevelFromXp)
        val updateProfileWithQuest = UpdateProfileWithQuest(addXpToProfile, updateProfileLevel)

        return ProfileUseCases(
            getProfile = GetProfile(repository),
            addXpToProfile = addXpToProfile,
            calculateLevelFromXp = calculateLevelFromXp,
            updateProfileLevel = updateProfileLevel,
            updateProfileWithQuest = updateProfileWithQuest
        )
    }
}