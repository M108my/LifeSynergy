package medina.magaly.lifesynergy

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import medina.magaly.lifesynergy.databinding.ActivityAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TaskDataBaseHelper

    private lateinit var dateTV: TextView
    private lateinit var timeTV: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDataBaseHelper(this)

        binding.saveButton.setOnClickListener{
            val title = binding.addTaskET.text.toString()
            val date = binding.dateTV.text.toString()
            val time = binding.timeTV.text.toString()

            val task = Task(0,title,date,time)
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Tarea guardada :)", Toast.LENGTH_SHORT).show()
        }

        binding.backButton.setOnClickListener{
            finish()
        }

        dateTV = binding.dateTV
        timeTV = binding.timeTV

        val calendarBox = Calendar.getInstance()
        val dateBox = DatePickerDialog.OnDateSetListener{ datePicker, year, month, day ->
            calendarBox.set(Calendar.YEAR, year)
            calendarBox.set(Calendar.MONTH, month)
            calendarBox.set(Calendar.DAY_OF_MONTH, day)

            updateDateText(calendarBox)
        }

        binding.dateButton.setOnClickListener{
            DatePickerDialog(this, dateBox, calendarBox.get(Calendar.YEAR), calendarBox.get(Calendar.MONTH), calendarBox.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.timeButton.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val starHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{ view, hourOfDay, minute ->
                timeTV.setText("$hourOfDay:$minute")
            }, starHour, startMinute, false).show()
        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateText(calendar: Calendar){
        val dateFormat = "dd-MM-yyyy"
        val simple = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        dateTV.setText(simple.format(calendar.time))
    }
}