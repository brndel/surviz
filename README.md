# SurViz

SurViz is a tool for better visualizing stated choice surveys.

## Usage

## Supported file types

### Input
- **.ngd**

### Output
- **.png** (whole situation or individual options)
- **.html** for [Unipark](https://www.unipark.com/)

## Adding features

### Importer

To add an Importer to support a new file type simply create a class that implements the <code>Importer</code> interface.
For the new Importer to be recognized by the rest of the program add <code>YourImporter</code> to <code>ImporterVariant</code>:

```kotlin
enum class ImporterVariant(private val importer: Importer) {
	Ngene(NgeneImporter),
	//....
	YourFileFormat(YourImporter);
	//....
}
```

### Exporter
