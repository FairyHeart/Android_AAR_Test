package com.fairy.glide_demo.v4.okhttp

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * 自定义OkHttp
 *
 * @author: Fairy.
 * @date  : 2020/5/25.
 */
class OkHttpGlideUrlLoader(private val okHttpClient: OkHttpClient) :
    ModelLoader<GlideUrl, InputStream> {

    companion object {
        class Factory() : ModelLoaderFactory<GlideUrl, InputStream> {

            private var client = OkHttpClient()

            constructor(client: OkHttpClient) : this() {
                this.client = client
            }

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
        return ModelLoader.LoadData(
            model,
            OkHttpFetcher(okHttpClient, model)
        )
    }

    override fun handles(model: GlideUrl): Boolean {
        return true
    }

}