package photos.picsum.mlbdproject.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object CheckPermission {

    fun verifyPermissions(context: Activity): Boolean {

        val permissionExternalMemory =
            ActivityCompat.checkSelfPermission(
              context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            val STORAGE_PERMISSIONS = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(context, STORAGE_PERMISSIONS, 1)
            return false
        }
        return true
    }

}