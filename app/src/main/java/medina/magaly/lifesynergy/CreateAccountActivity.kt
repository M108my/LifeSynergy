package medina.magaly.lifesynergy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import medina.magaly.lifesynergy.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Database(this)

        binding.btnCreateAccount.setOnClickListener{
            val createAccountEmail = binding.createaccountEmail.text.toString()
            val createAccountUsername = binding.createaccountUsername.text.toString()
            val createAccountPassword = binding.createaccountPassword.text.toString()
            createaccountDatabase(createAccountEmail, createAccountUsername, createAccountPassword)
        }

        binding.btnBackSignIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createaccountDatabase(email: String, username: String, password: String){
        val insertedRowId = database.insertUser(email, username, password)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Cuenta Creada Correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show()
        }
    }
}