package com.hakankaraotcu.focusquest.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteConnection
import com.hakankaraotcu.focusquest.feature_quest.data.data_source.QuestDatabase
import com.hakankaraotcu.focusquest.feature_quest.data.repository.ProfileRepositoryImpl
import com.hakankaraotcu.focusquest.feature_quest.data.repository.QuestRepositoryImpl
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.ProfileRepository
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.GetProfile
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.UpsertQuest
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.GetQuest
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.GetQuests
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.ProfileUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.UpdateProfileWithQuest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuestDatabase(app: Application): QuestDatabase {
        return Room.databaseBuilder(
            app,
            QuestDatabase::class.java,
            QuestDatabase.DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(connection: SQLiteConnection) {
                    super.onCreate(connection)
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = provideQuestDatabase(app).profileDao
                        dao.insertProfile(Profile())
                    }
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideQuestRepository(db: QuestDatabase): QuestRepository {
        return QuestRepositoryImpl(db.questDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(db: QuestDatabase): ProfileRepository {
        return ProfileRepositoryImpl(db.profileDao)
    }

    @Provides
    @Singleton
    fun provideQuestUseCases(repository: QuestRepository): QuestUseCases {
        return QuestUseCases(
            getQuests = GetQuests(repository),
            getQuest = GetQuest(repository),
            upsertQuest = UpsertQuest(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            updateProfileWithQuest = UpdateProfileWithQuest(repository),
            getProfile = GetProfile(repository)
        )
    }
}