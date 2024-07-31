package medina.magaly.lifesynergy

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import medina.magaly.lifesynergy.databinding.FragmentToDoBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ToDoFragment : Fragment() {

    private lateinit var db: TaskDataBaseHelper
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var binding: FragmentToDoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = TaskDataBaseHelper(requireContext())
        tasksAdapter = TasksAdapter(db.getAllTasks(), requireContext())

        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.tasksRecyclerView.adapter = tasksAdapter
    }

    override fun onResume() {
        super.onResume()
        tasksAdapter.refeshData(db.getAllTasks())
    }
}