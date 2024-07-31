package medina.magaly.lifesynergy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import medina.magaly.lifesynergy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Database(this)

        binding.btnLogin.setOnClickListener{
            val loginEmail = binding.loginEmail.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginEmail, loginPassword)
        }
        binding.btnLoginCreateAccount.setOnClickListener{
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(email: String, password: String){
        val userExists = database.readUser(email, password)
        if(userExists){
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            val username = database.getUsernameByEmail(email)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_USERNAME", username )
            }
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Error de inicio de sesión", Toast.LENGTH_SHORT).show()
        }
    }
}

