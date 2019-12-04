package endroad.dl.support

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import kotlin.math.min

class ImageCircle @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyle: Int = 0) : ImageView(context, attrs, defStyle) {
	private val mDrawableRect = RectF()
	private val mShaderMatrix = Matrix()
	private val mBitmapPaint = Paint()
	private var mBitmap: Bitmap? = null
	private var mBitmapShader: BitmapShader? = null
	private var mBitmapWidth = 0
	private var mBitmapHeight = 0
	private var mDrawableRadius = 0f

	init {
		super.setScaleType(SCALE_TYPE)
		setup()
	}

	override fun onDraw(canvas: Canvas) {
		mBitmap ?: return
		canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mBitmapPaint)
	}

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		setup()
	}

	override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
		super.setPadding(left, top, right, bottom)
		setup()
	}

	override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
		super.setPaddingRelative(start, top, end, bottom)
		setup()
	}

	override fun setImageDrawable(drawable: Drawable?) {
		super.setImageDrawable(drawable)
		mBitmap = getDrawable().toBitmap()
		setup()
	}

	private fun Drawable?.toBitmap(): Bitmap? =
		if (this != null && this is BitmapDrawable) this.bitmap
		else null

	private fun setup() {
		if (width == 0 && height == 0) return

		mBitmap?.let {
			mBitmapShader = BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
			mBitmapPaint.isAntiAlias = true
			mBitmapPaint.shader = mBitmapShader
			mBitmapHeight = it.height
			mBitmapWidth = it.width
			mDrawableRect.set(calculateBounds())
			mDrawableRadius = min(mDrawableRect.height() / 2.0f, mDrawableRect.width() / 2.0f)
			updateShaderMatrix()
		}

		invalidate()
	}

	private fun calculateBounds(): RectF {
		val availableWidth = width - paddingLeft - paddingRight
		val availableHeight = height - paddingTop - paddingBottom
		val sideLength = min(availableWidth, availableHeight)
		val left = paddingLeft + (availableWidth - sideLength) / 2f
		val top = paddingTop + (availableHeight - sideLength) / 2f
		return RectF(left, top, left + sideLength, top + sideLength)
	}

	private fun updateShaderMatrix() {
		val scale: Float
		var dx = 0f
		var dy = 0f
		mShaderMatrix.set(null)
		if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
			scale = mDrawableRect.height() / mBitmapHeight.toFloat()
			dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f
		} else {
			scale = mDrawableRect.width() / mBitmapWidth.toFloat()
			dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f
		}
		mShaderMatrix.setScale(scale, scale)
		mShaderMatrix.postTranslate((dx + 0.5f).toInt() + mDrawableRect.left, (dy + 0.5f).toInt() + mDrawableRect.top)
		mBitmapShader?.run { setLocalMatrix(mShaderMatrix) }
	}

	companion object {
		private val SCALE_TYPE = ScaleType.CENTER_CROP
		private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
		private const val COLORDRAWABLE_DIMENSION = 2
	}
}