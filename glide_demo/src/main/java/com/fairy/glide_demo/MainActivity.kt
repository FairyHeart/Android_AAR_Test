package com.fairy.glide_demo

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fairy.glide_demo.v4.GlideV4Activity
import com.lib.permission.Permission
import com.lib.permission.PermissionManager
import com.lib.permission.listener.OnPermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        load_image_btn.setOnClickListener {
            //三步走：先with()，再load()，最后into()
            Glide.with(this@MainActivity)
                .load("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .placeholder(R.mipmap.ic_place_holder)//图片正在加载中的时候显示占位图
                .error(R.mipmap.ic_error)//加载失败占位图
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image_iv)
        }

        load_gif_btn.setOnClickListener {
            //glide支持加载gif图片，会自动根据格式显示加料哪种类型的图片
            Glide.with(this)
//                .asGif()//只允许加载gif图片，如果不是gif图片则加载失败
//                .asBitmap()//只允许加载静态图片，如果是gif图片，默认加载第一帧的图片
                .load("http://p1.pstatp.com/large/166200019850062839d3")
                .override(200, 200)//指定图片大小，Glide会自动判断ImageView的大小，然后只将这么大的图片像素加载到内存当中，帮助我们节省内存开支
                .into(image_iv)
        }

        down_load_only_btn.setOnClickListener {
            //downloadOnly(int width, int height) 需要在子线程中运行
            Thread(Runnable {
                try {
                    val url =
                        "https://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg"
                    val target = Glide.with(this@MainActivity)
                        .load(url)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    val file = target.get()
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, file.path, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }).start()

            //target不需要在子线程中运行
            val url = "https://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg"
            Glide.with(this)
                .load(url)
                .downloadOnly(DownloadImageTarget())
        }

        listener_btn.setOnClickListener {
            val url =
                "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg"
            Glide.with(this)
                .load(url)
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
        }

        Glide.with(this)
            .load(MyGlideUrl("https://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"))
            .into(image_iv)

        start_activity_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, GlideV4Activity::class.java)
            startActivity(intent)
        }
    }

    private fun checkPermission() {
        val permissions = mutableListOf(
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE
        )
        PermissionManager.builder()
            .build(this)
            .checkMultiplePermission(permissions, object : OnPermissionListener {
                /**
                 * 关闭授权
                 */
                override fun onClose() {
                }

                /**
                 * 被拒绝
                 * @param permission 权限
                 * @param position 权限角标
                 */
                override fun onDenied(permission: String, position: Int) {
                }

                /**
                 * 全部授权完成
                 */
                override fun onFinish() {
                }

                /**
                 * 被授予
                 * @param permission 权限
                 * @param position 权限角标
                 */
                override fun onGranted(permission: String, position: Int) {
                }

            })
    }
}
