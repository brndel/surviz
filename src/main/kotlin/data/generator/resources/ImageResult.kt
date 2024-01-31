package data.generator.resources

import androidx.compose.ui.graphics.ImageBitmap

data class ImageResult(val image: ImageBitmap, val neededWidth: Int) {

    /**
     * Check if all of the contents fit on the image
     *
     * @return if image is wide enough
     */
    fun checkWidth(): Boolean{
        return neededWidth <= image.width
    }
}
