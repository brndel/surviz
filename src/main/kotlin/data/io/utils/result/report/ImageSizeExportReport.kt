package data.io.utils.result.report

import data.io.utils.OptionId
import data.project.Project
import ui.Labels

/**
 * Image size export report reports that the image size is too small to contain all the contents without any cut offs
 *
 * @constructor create a report with given id and needed width
 *
 * @param id ID where the error occurred
 * @param info needed with for the image
 */
class ImageSizeExportReport(id: OptionId, info: Int) :
    ExportReport(id, Labels.NEEDED_WIDTH, info, "px") {

    /**
     * Set the size of the images to the needed width
     *
     * @param project the project the fix should be made in
     */
    override fun applyFix(project: Project) {
        project.configuration.imageConfig.width.value = info.toString().toInt()
    }
}