package com.fairy.glide_demo

import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.io.File

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020-05-19.
 */
class DownloadImageTarget : Target<File> {

    override fun onLoadStarted(placeholder: Drawable?) {
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
    }

    override fun getSize(cb: SizeReadyCallback) {
        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    override fun getRequest(): Request? {
        return null
    }

    override fun onStop() {
    }

    override fun setRequest(request: Request?) {
    }

    override fun removeCallback(cb: SizeReadyCallback) {
    }

    override fun onLoadCleared(placeholder: Drawable?) {
    }

    override fun onStart() {
    }

    override fun onDestroy() {
    }

    override fun onResourceReady(resource: File, transition: Transition<in File>?) {
        Log.i("glide", resource.path)
    }
}