package pt.andre.googlepaylauncher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val GOOGLE_PAY_PACKAGE = BuildConfig.GOOGLE_PAY_PACKAGE_ID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        packageManager.getLaunchIntentForPackage(GOOGLE_PAY_PACKAGE)?.let {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(it)
        }
    }
}