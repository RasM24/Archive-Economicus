package endroad.dl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import endroad.dl.view.SingleActivity

class SplashScreenActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val intent = Intent(this, SingleActivity::class.java)
		startActivity(intent)
		finish()
	}
}