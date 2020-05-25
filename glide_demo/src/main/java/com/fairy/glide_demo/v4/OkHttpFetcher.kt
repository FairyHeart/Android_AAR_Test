package com.fairy.glide_demo.v4

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStream

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/5/25.
 */
class OkHttpFetcher(private val client: OkHttpClient, private val url: GlideUrl) :
    DataFetcher<InputStream> {

    private var stream: InputStream? = null

    private var isCancelled: Boolean = false

    private var responseBody: ResponseBody? = null

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun cleanup() {
        try {
            stream?.close()
            responseBody?.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }

    override fun cancel() {
        isCancelled = true
    }

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        try {
            val requestBuilder = Request.Builder().url(url.toStringUrl())
            url.headers.entries.forEach {
                val key = it.key
                requestBuilder.addHeader(key, it.value)
            }
            requestBuilder.addHeader("httpType", "OkHttp")//测试使用
            val request = requestBuilder.build()
            if (isCancelled) {
                return
            }
            val response = client.newCall(request).execute()
            responseBody = response.body()
            if (!response.isSuccessful) {
                throw IOException("Request failed with code: ${response.code()}")
            }
            if (responseBody != null) {
                stream = ContentLengthInputStream.obtain(
                    responseBody!!.byteStream(),
                    responseBody?.contentLength() ?: 0
                )
                callback.onDataReady(stream)
            }
        } catch (e: Exception) {
            callback.onLoadFailed(e)
        }
    }

}