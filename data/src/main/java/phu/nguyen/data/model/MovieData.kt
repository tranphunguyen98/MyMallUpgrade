package phu.nguyen.data.model

data class MovieData (
    val id: Int = 0,
    val voteCount: Int = 0,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val popularity: Double = 0.0,
    val adult: Boolean = false,
    val details: MovieDetailData? = null,
    val title: String,
    val posterPath: String? = "",
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String? = "",
    val releaseDate: String,
    val overview: String? = ""
)