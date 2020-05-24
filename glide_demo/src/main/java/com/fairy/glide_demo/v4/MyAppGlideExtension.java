package com.fairy.glide_demo.v4;

import androidx.annotation.NonNull;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.fairy.glide_demo.R;

/**
 * @author: Fairy.
 * @date : 2020/5/24.
 */
@GlideExtension
public class MyAppGlideExtension {

    private MyAppGlideExtension() {
    }

    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> initRe(BaseRequestOptions<?> options) {

        return options.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .override(500)
                .placeholder(R.mipmap.ic_launcher_round);
    }

}
