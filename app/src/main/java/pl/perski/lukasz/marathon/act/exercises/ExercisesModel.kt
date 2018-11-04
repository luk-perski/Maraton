package pl.perski.lukasz.marathon.act.exercises

import android.content.Context
import pl.perski.lukasz.marathon.data.model.ExerciseData
import pl.perski.lukasz.marathon.data.repositories.ExercisesRepository

class ExercisesModel   : ExercisesActivityMVP.Model {

    lateinit var repository : ExercisesRepository

    override fun getExercisesFromDB(context : Context): List<ExerciseData> {
         repository = ExercisesRepository(context)
        return repository.getExercises()
    }


}