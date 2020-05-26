package com.fairy.glide_demo.v4;

import androidx.annotation.NonNull;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.fairy.glide_demo.R;

/**
 * 自定义API
 * 官方提供了@GlideExtension的注解，使用的是静态方法实现拓展Glide的API
 *
 * @author: Fairy.
 * @date : 2020/5/24.
 */
@GlideExtension
public class MyJavaGlideExtension {

    private MyJavaGlideExtension() {
    }

    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> noCache(BaseRequestOptions<?> options) {

        return options.skipMemoryCache(true)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.ic_launcher_round);
    }

    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> initRe(BaseRequestOptions<?> options, int size) {
        return options.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .override(size)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(R.mipmap.ic_launcher_round);
    }

}
