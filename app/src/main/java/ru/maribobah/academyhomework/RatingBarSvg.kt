package ru.maribobah.academyhomework

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatRatingBar

class RatingBarSvg @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ratingBarStyle,
    var drawableHeight: Int = 0,
    var drawableWidth: Int = 0
) : AppCompatRatingBar(context, attrs, defStyleAttr) {

    private var sampleTile: Bitmap? = null
    private val drawableShape: Shape = RectShape()
    private val halfOfInnerPadding = 1

    init {
        attrs?.let { getSettingsFromAttr(it) }
        val drawable = tileify(progressDrawable, false)
        progressDrawable = drawable
    }

    private fun getSettingsFromAttr(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.RatingBarSvg).run {
            drawableHeight = getDimension(R.styleable.RatingBarSvg_drawableHeight, 0f).toInt()
            drawableWidth = getDimension(R.styleable.RatingBarSvg_drawableWidth, 0f).toInt()
            recycle()
        }
    }

    /**
     * Converts a drawable to a tiled version of itself. It will recursively
     * traverse layer and state list drawables.
     */
    private fun tileify(drawable: Drawable, clip: Boolean): Drawable {
        when (drawable) {
            is LayerDrawable -> {
                val numberOfLayers = drawable.numberOfLayers
                val outDrawables = arrayOfNulls<Drawable>(numberOfLayers)
                for (i in 0 until numberOfLayers) {
                    val id = drawable.getId(i)
                    outDrawables[i] = tileify(
                        drawable.getDrawable(i),
                        id == android.R.id.progress || id == android.R.id.secondaryProgress
                    )
                }
                val newBg = LayerDrawable(outDrawables)
                for (i in 0 until numberOfLayers) {
                    newBg.setId(i, drawable.getId(i))
                }
                return newBg
            }
            is BitmapDrawable -> {
                val tileBitmap = drawable.bitmap
                if (sampleTile == null) {
                    sampleTile = tileBitmap
                }
                val shapeDrawable = ShapeDrawable(drawableShape)
                val bitmapShader =
                    BitmapShader(tileBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP)
                shapeDrawable.paint.shader = bitmapShader
                shapeDrawable.paint.colorFilter = drawable.paint.colorFilter
                return if (clip)
                    ClipDrawable(shapeDrawable, Gravity.START, ClipDrawable.HORIZONTAL)
                else shapeDrawable
            }
            else -> return tileify(getBitmapDrawableFromVectorDrawable(drawable), clip)
        }
    }

    private fun getBitmapDrawableFromVectorDrawable(drawable: Drawable): BitmapDrawable {
        if (drawableHeight == 0) drawableHeight = drawable.intrinsicHeight
        if (drawableWidth == 0) drawableWidth = drawable.intrinsicWidth

        val bitmap = Bitmap.createBitmap(
            drawableWidth + halfOfInnerPadding * 2, //dp between svg images
            drawableHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(
            halfOfInnerPadding,
            0,
            drawableWidth + halfOfInnerPadding,
            drawableHeight
        )
        drawable.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var mHeight = measuredHeight
        val heightMS = heightMeasureSpec

        if (heightMeasureSpec != MeasureSpec.EXACTLY) {
            mHeight = drawableHeight
            MeasureSpec.EXACTLY
        }

        super.onMeasure(widthMeasureSpec, heightMS)

        sampleTile?.let {
            val width = it.width * numStars
            setMeasuredDimension(
                resolveSizeAndState(width, widthMeasureSpec, 0),
                resolveSizeAndState(mHeight, heightMeasureSpec, 0)
            )
        }
    }
}