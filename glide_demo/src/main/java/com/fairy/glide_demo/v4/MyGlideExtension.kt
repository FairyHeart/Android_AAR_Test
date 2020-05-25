package com.fairy.glide_demo.v4

import androidx.annotation.NonNull
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import com.fairy.glide_demo.R


/**
 * 自定义API
 *
 * @author: Fairy.
 * @date  : 2020/5/24.
 */
@GlideExtension
class MyGlideExtension private constructor() {

    companion object {

        @NonNull
        @GlideOption
        fun init(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
            return options
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.mipmap.ic_launcher_round)
                .fallback(R.mipmap.ic_launcher)
                .override(200)
        }
    }

}