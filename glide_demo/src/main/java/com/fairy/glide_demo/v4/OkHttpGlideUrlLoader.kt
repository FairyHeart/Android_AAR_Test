package com.fairy.glide_demo.v4

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/5/25.
 */
class OkHttpGlideUrlLoader(val okHttpClient: OkHttpClient) : ModelLoader<GlideUrl, InputStream> {

    companion object {
        class Factory(private val client: OkHttpClient) :
            ModelLoaderFactory<GlideUrl, InputStream> {

            override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> {
                return OkHttpGlideUrlLoader(client)
            }

            override fun teardown() {
            }

        }
    }

    override fun buildLoadData(
        model: GlideUrl,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(null,OkHttpFetcher(okHttpClient, model)
    }

}