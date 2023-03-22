package indi.atigger.cochelper

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShareReceiverActivity : AppCompatActivity() {

    private val receivingType = "text/plain"

    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast = Toast.makeText(applicationContext, R.string.app_toast, 3 * 1000);
        initIntent()
        openByClickWebButton()
    }


    private fun initIntent() {
        when {
            intent.action == Intent.ACTION_SEND && intent.type == receivingType -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    if (it.isNotBlank()) openSchemeUrl(it)
                }
            }
        }
    }

    private fun openSchemeUrl(urlStr: String) {
        Utils.activityStart(urlStr, toast, this)
        finish()
    }

    /**
     * 接收intent请求
     */
    private fun openByClickWebButton(){
        val intent = intent
        val scheme = intent.scheme
        val uri = intent.data
        if (uri != null) {
            val urlText: String = uri.toString()
            Utils.activityStart(urlText, uri, toast, this)
            finish()
        }
    }
}