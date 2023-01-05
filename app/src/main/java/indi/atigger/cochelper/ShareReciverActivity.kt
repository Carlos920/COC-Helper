package indi.atigger.cochelper

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class ShareReceiverActivity : AppCompatActivity() {

    private val receivingType = "text/plain"

    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast = Toast.makeText(applicationContext, R.string.app_toast, 3 * 1000);
        initIntent()
    }


    private fun initIntent() {
        when {
            intent.action == Intent.ACTION_SEND && intent.type == receivingType -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    dispose(it)
                }
            }
        }
    }

    private fun dispose(text: String) {
        if (text.isBlank()) {
            return
        }
        val urls = text.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = StringBuilder()
        for (url in urls) {
            if (url.matches("http.+".toRegex()))
                result.append("\n").append(url.trim { it <= ' ' })
        }
        openSchemeUrl(text)
    }

    private fun openSchemeUrl(urlStr: String) {
        var urlText = urlStr
        if (urlText.indexOf("=tencent") != -1 || urlText.indexOf("=IOS") != -1) {
            val startNum: Int = urlText.indexOf("?")
            urlText = urlText.substring(startNum + 1)
            urlText = "clashofclans://$urlText"
            val componentName = ComponentName(
                "com.tencent.tmgp.supercell.clashofclans",
                "com.supercell.titan.tencent.GameAppTencent"
            )
            intent.component = componentName
            intent.data = urlText.toUri()
            startActivity(intent)
        } else {
            toast.show()
        }
        finish()
    }
}