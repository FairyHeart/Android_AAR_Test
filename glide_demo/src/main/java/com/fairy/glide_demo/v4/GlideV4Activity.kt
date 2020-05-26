package com.fairy.glide_demo.v4

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.fairy.glide_demo.R
import kotlinx.android.synthetic.main.activity_glide_v4.*

class GlideV4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_v4)

        load_btn.setOnClickListener {
            val sharedOptions = RequestOptions()
                .placeholder(R.mipmap.ic_place_holder)
                .error(R.mipmap.ic_error)
                .fallback(R.mipmap.ic_launcher)
                .skipMemoryCache(true)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()//显示图片而没有任何淡入淡出效果
                .priority(Priority.HIGH)//图片显示优先级

            Glide.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .apply(sharedOptions)
                .thumbnail(0.1f)//缩略图,0.1f将会显示原始图像的10%的大小
                .into(iv_icon)
        }

        target_btn.setOnClickListener {
            //自定义target
            GlideApp.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .noCache()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .into(object : CustomTarget<Drawable>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        iv_icon.setImageDrawable(placeholder)
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        iv_icon.setImageDrawable(resource)
                    }
                })
        }

        view_target_btn.setOnClickListener {
            //自定义ViewTarget
            GlideApp.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jp")
                .noCache()
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
        }

        preload_btn.setOnClickListener {
            //预加载图片
            GlideApp.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .noCache()
                .preload()
        }

        submit_btn.setOnClickListener {
            //submit获取获取文件下载路径
            Thread(Runnable {
                val target = Glide.with(this@GlideV4Activity)
                    .asFile()
                    .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                    .submit()

                val file = target.get()
                runOnUiThread {
                    Toast.makeText(this@GlideV4Activity, file.path, Toast.LENGTH_LONG).show()
                }
            }).start()

        }

        listener_btn.setOnClickListener {
            //listener
            GlideApp.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg")
                .noCache()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,//具体失败的原因
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean { //图片加载失败调用
                        Toast.makeText(this@GlideV4Activity, "图片加载失败", Toast.LENGTH_LONG).show()
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {//图片加载完成调用
                        Toast.makeText(this@GlideV4Activity, "图片加载成功", Toast.LENGTH_LONG).show()
                        return false //false表示会继续向下传递 true表示事件被处理，不会向下传递
                    }

                }).into(iv_icon)
        }

        trans_btn.setOnClickListener {
            //图片转换
            GlideApp.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .noCache()
                .circleCrop()
                .into(iv_icon)
        }

    }
}
