package endroad.dl

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import endroad.dl.models.Contact
import org.json.JSONArray
import java.util.*

val databaseReference = FirebaseDatabase.getInstance().reference
val linkList = ArrayList<Contact>()

fun Context.loadLinkArrayFromAssets() {
	runCatching {
		val jsonArray = JSONArray(getStringFromAssetFile("link"))
		linkList.clear()
		for (i in 0 until jsonArray.length()) {
			linkList.add(Contact.fromJSON(jsonArray.getJSONObject(i)))
		}
	}
}

private fun Context.getStringFromAssetFile(name: String): String {
	runCatching {
		assets.open(name).run {
			val size = available()
			val buffer = ByteArray(size)
			read(buffer)
			close()
			return String(buffer)
		}
	}
	return ""
}