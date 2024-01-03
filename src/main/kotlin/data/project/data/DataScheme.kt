package data.project.data

/**
 * This class represents a data scheme.
 * A data scheme is a set of data scheme options.
 *
 */
class DataScheme {

   lateinit var options : MutableList<DataSchemeOption>

   /**
    * This method compares two data schemes.
    * @param scheme the data scheme to compare
    */
   fun compareTo(scheme : DataScheme) : Boolean {
      return false
   }
}