package com.fairy.glide_demo

import com.bumptech.glide.load.model.GlideUrl

/**
 * 自定义缓存key
 *
 * @author: Fairy.
 * @date  : 2020-05-19.
 */
class MyGlideUrl(var url: String) : GlideUrl(url) {
    override fun getCacheKey(): String {
        return url.replace(findTokenParam(), "")
    }

    private fun findTokenParam(): String {
        var tokenParam = ""
        var tokenKeyIndex = if (url.indexOf("?token=") >= 0) {
            url.indexOf("?token=")
        } else {
            url.indexOf("&token=")
        }
        if (tokenKeyIndex != -1) {
            var nextAndIndex = url.indexOf("&", tokenKeyIndex + 1);
            tokenParam = if (nextAndIndex != -1) {
                url.substring(tokenKeyIndex + 1, nextAndIndex + 1);
            } else {
                url.substring(tokenKeyIndex);
            }
        }
        return tokenParam
    }
}