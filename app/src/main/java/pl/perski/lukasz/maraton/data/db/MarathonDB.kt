package pl.perski.lukasz.maraton.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.perski.lukasz.maraton.data.model.ConsonantsCombinationData
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.RhymesData
import pl.perski.lukasz.maraton.data.model.TwoVowelsWordsData

@Database(entities = [
    ExerciseData::class,
    RhymesData::class,
    ConsonantsCombinationData::class,
    TwoVowelsWordsData::class],
        version = 2,
        exportSchema = false)
abstract class MarathonDB : RoomDatabase() {

    abstract fun marathonDataDao(): ExercisesDataDao

    companion object {
        private var INSTANCE: MarathonDB? = null

        fun getInstance(context: Context): MarathonDB? {
            if (INSTANCE == null) {
                synchronized(MarathonDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MarathonDB::class.java, "marathon.db").allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}