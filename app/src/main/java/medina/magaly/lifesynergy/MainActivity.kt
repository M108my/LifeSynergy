package medina.magaly.lifesynergy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import medina.magaly.lifesynergy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binging: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_to_do_list -> {
                    replaceFragment(ToDoFragment())
                    true
                }
                R.id.bottom_add -> {
                    val intent = Intent(this, AddTaskActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        replaceFragment(ToDoFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}