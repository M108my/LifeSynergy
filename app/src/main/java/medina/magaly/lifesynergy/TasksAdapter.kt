package medina.magaly.lifesynergy

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.time.format.DateTimeFormatter

class TasksAdapter(private var tasks: List<Task>, context: Context) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val db: TaskDataBaseHelper = TaskDataBaseHelper(context)

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTV: TextView = itemView.findViewById(R.id.titleTV)
        val dateTV: TextView = itemView.findViewById(R.id.dateTV)
        val timeTV: TextView = itemView.findViewById(R.id.timeTV)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTV.text = task.title
        holder.dateTV.text = task.date
        holder.timeTV.text = task.time

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateTaskActivity::class.java).apply {
                putExtra("task_id", task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteTask(task.id)
            refeshData(db.getAllTasks())
            Toast.makeText(holder.itemView.context, "Tarea eliminada", Toast.LENGTH_SHORT).show()
        }
    }

    fun refeshData(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }
}