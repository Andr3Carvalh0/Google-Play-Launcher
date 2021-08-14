package pt.andre.googlepaylauncher.main.apps

import android.content.Intent
import android.content.pm.PackageManager

internal interface ApplicationManager {
    fun getApplicationStartIntent(id: String): Intent?
}

internal class ApplicationManagerImpl(
    private val packageManager: PackageManager
): ApplicationManager {
    override fun getApplicationStartIntent(id: String): Intent? {
        return packageManager.getLaunchIntentForPackage(id)
    }
}
