package app.aes_encryption

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESCryptUtil {

    private const val AES_MODE = "AES/CBC/PKCS7Padding"
    private const val CHARSET_NAME = "UTF-8"

    private const val SECRET_KEY = "0123456789abcdef0123456789abcdef" // 256 key (32 bytes)
    private const val INITIALIZATION_VECTOR = "0123456789abcdef" // 16-byte IV

    fun encrypt(textToEncrypt: String): String {
        return try {
            val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(), "AES")
            val ivSpec = IvParameterSpec(INITIALIZATION_VECTOR.toByteArray())

            val cipher = Cipher.getInstance(AES_MODE)
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

            val encryptedBytes = cipher.doFinal(textToEncrypt.toByteArray())
            Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun decrypt(encryptedText: String): String {
        return try {
            val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(), "AES")
            val ivSpec = IvParameterSpec(INITIALIZATION_VECTOR.toByteArray())

            val cipher = Cipher.getInstance(AES_MODE)
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)

            val encryptedBytes = Base64.decode(encryptedText, Base64.DEFAULT)
            val decryptedBytes = cipher.doFinal(encryptedBytes)
            String(decryptedBytes, charset(CHARSET_NAME))
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

}