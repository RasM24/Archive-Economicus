package endroad.dl.models

class News @JvmOverloads constructor(var imageURL: String? = null, var bodyText: String? = null) {

	companion object {
		const val NEWS_COMMON = "news-common"
		const val NEWS_RATING = "news-rating"
	}
}