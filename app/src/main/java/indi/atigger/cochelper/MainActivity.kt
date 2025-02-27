package indi.atigger.cochelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


/**
 * MainActivity.kt
 *
 * @author atigger
 *
 * create on 2022/12/9
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button? = findViewById(R.id.button)
        val button1: Button? = findViewById(R.id.button2)
        val editText: EditText? = findViewById(R.id.editTextTextPersonName)
        val textView2: TextView? = findViewById(R.id.textView2)
        val textView: TextView? = findViewById(R.id.textView)
        val toast: Toast =
            Toast.makeText(applicationContext, "暂不支持此链接\n国服和国际服数据不互通", Toast.LENGTH_SHORT)


        //region 添加图片
        /*val imageSpan: ImageSpan = ImageSpan(this, R.drawable.github)
        val spannableString: SpannableString = SpannableString("github"+textView?.getText())
        spannableString.setSpan(imageSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView?.setText(spannableString)*/

        //图片资源转drawable,
        val img = ContextCompat.getDrawable(this, R.drawable.github)
        //设置图片大小
        val textSize = textView?.textSize?.toInt() ?: 40
        img?.setBounds(0, 0, textSize, textSize)
        //生成ImageSapn
        val span = img?.let { CenterImageSpan(it) }
        val spanStr = SpannableString("github")
        //把图片设置进Span中
        spanStr.setSpan(span, 0, spanStr.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        val textTemp = textView?.text
        textView?.text = spanStr
        textView?.append(" $textTemp")
        //endregion


        /**
         * 使用阵型点击事件，替换链接关键字之后通过intent打开应用
         */
        button?.setOnClickListener {
            val urlText: String = editText?.text.toString()
            Utils.activityStart(urlText, toast, this)
        }

        /**
         * 重置按钮
         */
        button1?.setOnClickListener {
            editText?.text = null;
        }

        /**
         * 打开链接
         */
        fun openLinkByClick(view: View?, uri: String) {
            view?.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(uri)
                }
                startActivity(intent)
            }
        }

        // 开源地址
        openLinkByClick(textView, "https://github.com/atigger/COC-Helper")
        // 教程按钮
        openLinkByClick(textView2, "https://b23.tv/CfibBOh")
    }
}