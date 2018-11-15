package pl.perski.lukasz.maraton.ui.fragments.exerciseFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.ui.fragments.voiceRecorder.VoiceRecFragment

class ExerciseFragmentTypeTwo : ExerciseBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.fragment_exercise_type_two,container,false)
        getDataFromArg(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val childFragment = VoiceRecFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.child_fragment_container, childFragment).commit()
    }
}