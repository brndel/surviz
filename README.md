# Surviz

Surviz is a tool for better visualizing stated choice surveys.

## Usage

## Adding Code

### Importer

To add an Importer to support a new file extension simply create a class that implements the <code>Importer</code> interface.
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