package com.fxf.smartscrollview

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "111"
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT)
        }
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        )
        setContentView(R.layout.activity_main)

//        setNotchParams(window)

        scroll_container.setTitleBarView(linearlayout)
    }

    @TargetApi(28)
    private fun setNotchParams(window: Window?) {
        if (window == null) {
            return
        }
        val decorView = window.decorView ?: return
        decorView.post {
            try {
                val displayCutout =
                    decorView.rootWindowInsets.displayCutout
                if (displayCutout != null) {
                    val rects =
                        displayCutout.boundingRects
                    if (rects == null || rects.isEmpty()) {
                    } else {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        window.statusBarColor = Color.TRANSPARENT
                        //设置页面全屏显示
                        val lp = window.attributes
                        lp.layoutInDisplayCutoutMode =
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                        //设置页面延伸到刘海区显示
                        window.attributes = lp
                    }
                }
            } catch (ex: Throwable) {
            }
        }
    }

}
