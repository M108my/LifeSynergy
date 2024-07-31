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
import medina.magaly.lifesynergy.databinding.ActivityUpdateTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDataBaseHelper
    private var taskId: Int = -1

    private lateinit var updateDateTV: TextView
    private lateinit var updateTimeTV: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDataBaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)

        binding.updateTitleET.setText(task.title)
        binding.updateDateTV.setText(task.date)
        binding.updateTimeTV.setText(task.time)

        updateDateTV = binding.updateDateTV
        updateTimeTV = binding.updateTimeTV

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleET.text.toString()
            val newDate = binding.updateDateTV.text.toString()
            val newTime = binding.updateTimeTV.text.toString()

            val updateTask = Task(taskId, newTitle,newDate, newTime)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show()
        }

        binding.updateBackButton.setOnClickListener{
            finish()
        }

        val calendarBox = Calendar.getInstance()
        val dateBox = DatePickerDialog.OnDateSetListener{ _, year, month, day ->
            calendarBox.set(Calendar.YEAR, year)
            calendarBox.set(Calendar.MONTH, month)
            calendarBox.set(Calendar.DAY_OF_MONTH, day)

            updateDateText(calendarBox)
        }

        binding.updateDateButton.setOnClickListener{
            DatePickerDialog(this, dateBox, calendarBox.get(Calendar.YEAR), calendarBox.get(Calendar.MONTH), calendarBox.get(
                Calendar.DAY_OF_MONTH)).show()
        }

        binding.updateTimeButton.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val starHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{ _, hourOfDay, minute ->
                updateTimeTV.setText(String.format("%02d:%02d", hourOfDay, minute))
            }, starHour, startMinute, false).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateText(calendar: Calendar){
        val dateFormat = "dd-MM-yyyy"
        val simple = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        updateDateTV.setText(simple.format(calendar.time))
    }
}
