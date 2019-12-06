package endroad.dl.models

import org.json.JSONObject

data class Enterprise(val name: String, val information: String, val imagePath: String) {

	companion object {
		private const val NAME = "name"
		private const val INFORMATION = "information"
		private const val IMAGE_PATH = "imagePath"

		fun fromJSON(json: JSONObject): Enterprise {
			val name = json.optString(NAME, "")
			val information = json.optString(INFORMATION, "")
			val imagePath = json.optString(IMAGE_PATH, "")

			return Enterprise(name, information, imagePath)
		}
	}
}