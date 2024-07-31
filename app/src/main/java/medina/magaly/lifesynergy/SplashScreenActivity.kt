package medina.magaly.lifesynergy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        btnStart = findViewById(R.id.btn_get_started)
        btnStart.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}