package data.io.exporter;

import data.project.config.ProjectConfiguration;
import data.resources.fields.NamedField;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class PngExporter implements Exporter {

    @Override
    public void export(@NotNull ProjectConfiguration configuration, @NotNull Map<String, ?> exportConfig) {

    }

    @NotNull
    @Override
    public List<NamedField> getFields() {
        return null;
    }
}
