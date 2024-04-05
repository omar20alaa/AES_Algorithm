package app.aes_encryption

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.aes_encryption.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var encryptedText : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnEncrypt.setOnClickListener {
            val textToEncrypt = binding.etValue.text.toString()
            encryptedText = AESCryptUtil.encrypt(textToEncrypt)
            binding.tvValue.text = encryptedText
            println("Encrypted Text: $encryptedText")
        }

        binding.btnDecrypt.setOnClickListener {
            val decryptedText = AESCryptUtil.decrypt(encryptedText)
            binding.tvValue.text = decryptedText
            println("Decrypted Text: $decryptedText")
        }
    }

}