package indi.atigger.cochelper

import android.app.Activity
import android.content.ComponentName
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri

object Utils {

    val componentName = ComponentName(
        "com.tencent.tmgp.supercell.clashofclans",
        "com.supercell.titan.tencent.GameAppTencent"
    )


    fun activityStart(urlText: String, uri: Uri, toast: Toast, activity: Activity) {
        if (urlText.indexOf("=tencent", ignoreCase = true) != -1 || urlText.indexOf("=IOS", ignoreCase = true) != -1) {
            start(activity, uri)
        } else {
            toast.show()
        }
    }

    fun activityStart(urlStr: String, toast: Toast, activity: Activity) {
        var urlText = urlStr
        if (urlText.indexOf("=tencent", ignoreCase = true) != -1 || urlText.indexOf("=IOS", ignoreCase = true) != -1) {
            urlText = urlText.substringAfter("?")
            urlText = "clashofclans://$urlText"
            start(activity, urlText.toUri())
        } else {
            toast.show()
        }
    }

    private fun start(activity: Activity, uri: Uri) {
        activity.intent.component = componentName
        activity.intent.data = uri
        activity.startActivity(activity.intent)
    }
}