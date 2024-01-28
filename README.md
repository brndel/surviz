# SurViz

SurViz is a tool for better visualizing stated choice surveys.

## Usage

## Supported file types

### Input

#### Simulation data

- **.ngd** ([Ngene](https://www.choice-metrics.com/))

#### Icons

- **.png**
- **.svg**

### Output
- **.png** (whole situation or individual options)
- **.html** for [Unipark](https://www.unipark.com/)

## Internal Configuration

### ImageGenerator

The layout of the generated images can further be configured by changing the values in <code>image_generator.properties</code>.

>[!Warning]
>Changes to these values can lead to distorted images and improper functioning of the program.

The layout is based per single option in a situation.
The general layout of an option is as follows:

![General layout](assets/general_layout.png)

Green and blue area are representing the [single value](https://github.com/brndel/surviz?tab=readme-ov-file#single-value-section)/[timeline](https://github.com/brndel/surviz?tab=readme-ov-file#timeline-section) section. 
Further layout of those areas is explained in corresponding sections.


| Nr. | Identifier/Calculation                                               | Description                   | Unit |
|-----|----------------------------------------------------------------------|-------------------------------|------|
| 1   | <code>situation_width</code>                                         | width                         | px   |
| 2   | <code>situation_height</code>                                        | height                        | px   |
| 3   | <code>border_padding</code>                                          | border padding                | px   |
| 4   | $2 \times$ <code>column_padding</code>                               | padding around divider line   | px   |
| 5   | <code>max_single_values</code>$\times$<code>single_value_size</code> | width of single value section | px   |
| 6   | Nr. 1$-$($2 \times$Nr. 3)$-$Nr. 4$-$Nr. 5                            | width of timeline section     | px   |

**Other general adjustments that can be made:**

| Identifier                    | Description               | Unit              |
|-------------------------------|---------------------------|-------------------|
| <code>background_color</code> | background color          | ARGB value in hex |
| <code>divider_weight</code>   | thickness of divider line | pt                |
| <code>divider_color</code>    | color of divider line     | ARGB value in hex |

#### Single value section

#### Timeline section

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
