package data.generator.resources

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Wrapper class so needed width can be passed along with the image and displayed in the UI
 */
data class ImageResult(val image: ImageBitmap, val neededWidth: Int) {

    /**
     * Check if all of the contents fit on the image
     *
     * @return if image is wide enough
     */
    fun checkWidth(): Boolean {
        return neededWidth <= image.width
    }
}
