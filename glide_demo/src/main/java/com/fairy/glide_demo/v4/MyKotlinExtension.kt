package com.fairy.glide_demo.v4

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fairy.glide_demo.R


/**
 * 自定义API
 * 官方提供了@GlideExtension的注解，使用的是静态方法实现拓展Glide的API。但是Kotlin本身提供了扩展方法，所以我们使用Kotlin的扩展即可，例如：
 *
 * @author: Fairy.
 * @date  : 2020/5/24.
 */
fun RequestOptions.initMyExt(): RequestOptions {
    return this.fitCenter()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.mipmap.ic_launcher_round)
}