package com.fairy.glide_demo.v4

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.fairy.glide_demo.R
import kotlinx.android.synthetic.main.activity_glide_v4.*

class GlideV4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_v4)

        val sharedOptions = RequestOptions()
            .placeholder(R.mipmap.ic_place_holder)
            .error(R.mipmap.ic_error)
            .fallback(R.mipmap.ic_launcher)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .fitCenter()

//        Glide.with(this)
//            .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
//            .apply(sharedOptions)
//            .into(iv_icon)

//        GlideApp.with(this)
//            .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
//            .initRe()
//            .circleCrop()
//            .into(iv_icon)

        //自定义target
        /* Glide.with(this)
             .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
             .apply(sharedOptions)
             .into(object : CustomTarget<Drawable>() {
                 override fun onLoadCleared(placeholder: Drawable?) {
                     iv_icon2.setImageDrawable(placeholder)
                 }

                 override fun onResourceReady(
                     resource: Drawable,
                     transition: Transition<in Drawable>?
                 ) {
                     iv_icon.setImageDrawable(resource)
                 }
             })

         Glide.with(this)
             .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
             .apply(sharedOptions)
             .into(object : CustomViewTarget<Button, Drawable>(btn) {
                 override fun onLoadFailed(errorDrawable: Drawable?) {
                     btn.setBackgroundDrawable(errorDrawable)
                 }

                 override fun onResourceCleared(placeholder: Drawable?) {
                     btn.setBackgroundDrawable(placeholder)
                 }

                 override fun onResourceReady(
                     resource: Drawable,
                     transition: Transition<in Drawable>?
                 ) {
                     btn.setBackgroundDrawable(resource)
                 }

             })
 */

        //预加载图片
//        Glide.with(this)
//            .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
//            .apply(sharedOptions)
//            .preload()

        //submit获取获取文件下载路径
        /*Thread(Runnable {
            val target = Glide.with(this@GlideV4Activity)
                .asFile()
                .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .submit()

            val file = target.get()
            runOnUiThread {
                Toast.makeText(this@GlideV4Activity, file.path, Toast.LENGTH_LONG).show()
            }
        }).start()*/


    }
}
