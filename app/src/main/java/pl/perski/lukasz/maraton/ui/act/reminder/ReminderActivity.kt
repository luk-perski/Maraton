package pl.perski.lukasz.maraton.ui.act.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_reminder.*
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.R.string.actually_alarm_info
import spencerstudios.com.fab_toast.FabToast
import java.util.*

class ReminderActivity : AppCompatActivity(), ReminderActivityMVP.View {

    private val buttonClick = AlphaAnimation(1f, 0.8f)
    lateinit var alarmManager: AlarmManager
    lateinit var calendar: Calendar
    lateinit var pi: PendingIntent
    var presenter = ReminderActivityPresenter()
    private var hour = 0
    private var min = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        calendar = Calendar.getInstance()
        presenter.setView(this)
        setControls()

        btnSetReminder.setOnClickListener {
            btnSetReminder.startAnimation(buttonClick)
            presenter.setReminder()
            FabToast.makeText(this, resources.getString(R.string.reminder_set), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show()
        }

        btnDeleteReminder.setOnClickListener {
            btnDeleteReminder.startAnimation(buttonClick)
            presenter.cancelReminder()
        }

    }
    fun setControls()
    {
        tpReminder.setIs24HourView(true)
        tpReminder.currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        tpReminder.currentMinute = calendar.get(Calendar.MINUTE)
        val date = presenter.getReminderInfo()
        setReminderInfo(date)
    }

    override fun getContext(): Context {
        return this
    }

    override fun setReminderInfo(date : String?) {
        tvActuallyAlarm.text = if (date != null)
            "${resources.getString(actually_alarm_info)}\n$date"
        else  resources.getString(R.string.no_reminder)
    }


    override fun getMinute(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return tpReminder.minute
        } else {
            return tpReminder.minute
        }
    }

    override fun getHour(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return tpReminder.hour
        } else {
            return tpReminder.currentHour
        }
    }
}
