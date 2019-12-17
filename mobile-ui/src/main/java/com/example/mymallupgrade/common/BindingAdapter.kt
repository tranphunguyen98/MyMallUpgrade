package com.example.mymallupgrade.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */
class BindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImageSource(view: ImageView, url: String?) {
            Timber.d("url= null")
            url?.let {
                Timber.d("url= $it" )
                Picasso.get()
                    .load(it)
                    .into(view)
            }
        }
    }
}
