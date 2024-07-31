package medina.magaly.lifesynergy

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime
import java.util.ArrayList

data class Task(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
)
