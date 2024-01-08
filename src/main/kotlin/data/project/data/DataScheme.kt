package data.project.data

/**
 * This class represents a data scheme.
 * It contains a list of data scheme options.
 * If a data scheme option is selected, current project data will be applied to new loaded nGene file if possible.
 *
 *
 */
class DataScheme {

   lateinit var options : MutableList<DataSchemeOption>

   /**
    * This method compares two data schemes.
    * @param scheme the data scheme to compare
    * @return true if the data schemes are equal, false otherwise
    * In case of true , current project data will be applied to new loaded nGene file.
    */
   fun compareTo(scheme : DataScheme) : Boolean {
      return false
   }
}