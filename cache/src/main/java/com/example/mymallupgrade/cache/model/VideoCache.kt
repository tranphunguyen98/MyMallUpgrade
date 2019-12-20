package com.example.mymallupgrade.cache.model

data class VideoCache(
    val id: String,
    val name: String,
    val youtubeKey: String? = null
) {
    companion object {
        const val SOURCE_YOUTUBE = "YouTube"
        const val TYPE_TRAILER = "Trailer"
    }
}
