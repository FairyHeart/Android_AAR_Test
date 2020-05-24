package com.fairy.glide_demo.v4

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

/**
 *  自定义模块功能
 *      可以将更改Glide配置，替换Glide组件等操作独立出来
 *      此类可生成出一个流式 API，内联了多种选项，和集成库中自定义的选项
 *      生成的 API 默认名为 GlideApp
 *
 * @author: GuaZi.
 * @date  : 2020-05-19.
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    /**
     * 更改Glide配置
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder
            .setMemoryCache(LruResourceCache(1024 * 1024 * 100))//内存大小
            .setDiskCache(
                ExternalPreferredCacheDiskCacheFactory(
                    context,
                    "cacheFileName",//缓存名字
                    1024 * 1024 * 100//缓存大小，默认250
                )
            )//磁盘缓存

    }

    /**
     * 替换Glide组件
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }
}