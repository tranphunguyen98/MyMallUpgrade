package phu.nguyen.data.model

data class MovieDetailData(
    val belongsToCollection: Any? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdbId: String? = null,
    val overview: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val reviews: List<ReviewData>? = null,
    var videos: List<VideoData>? = null,
    val genres: List<GenreData>? = null
)