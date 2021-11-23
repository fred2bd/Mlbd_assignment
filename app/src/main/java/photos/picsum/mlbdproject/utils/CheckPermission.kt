package photos.picsum.mlbdproject.utils

import android.Manifest
import android.app.Activity
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
            ActivityCompat.requestPermissions(
                context, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                Constants.MY_PERMISSIONS_REQUEST_STORAGE
            )
            return false
        }
        return true
    }

}