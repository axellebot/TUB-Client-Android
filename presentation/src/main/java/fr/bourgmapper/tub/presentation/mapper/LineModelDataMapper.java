package fr.bourgmapper.tub.presentation.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import fr.bourgmapper.tub.domain.Line;
import fr.bourgmapper.tub.presentation.internal.di.PerActivity;
import fr.bourgmapper.tub.presentation.model.LineModel;


/**
 * Mapper class used to transform {@link Line} (in the domain layer) to {@link LineModel} in the
 * presentation layer.
 */
@PerActivity
public class LineModelDataMapper {

    @Inject
    public LineModelDataMapper() {
    }



    /**
     * Transform a {@link Line} into an {@link LineModel}.
     *
     * @param line Object to be transformed.
     * @return {@link LineModel}.
     */
    public LineModel transform(Line line) {
        if (line == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final LineModel lineModel = new LineModel(line.getLineId());
        lineModel.setNumber(line.getNumber());
        lineModel.setLabel(line.getLabel());
        lineModel.setColor(line.getColor());
        lineModel.setKmlPath(line.getKmlPath());

        return lineModel;
    }

    /**
     * Transform a Collection of {@link Line} into a Collection of {@link LineModel}.
     *
     * @param lineCollection Objects to be transformed.
     * @return List of {@link LineModel}.
     */
    public Collection<LineModel> transform(Collection<Line> lineCollection) {
        Collection<LineModel> lineModelCollection;

        if (lineCollection != null && !lineCollection.isEmpty()) {
            lineModelCollection = new ArrayList<>();
            for (Line user : lineCollection) {
                lineModelCollection.add(transform(user));
            }
        } else {
            lineModelCollection = Collections.emptyList();
        }

        return lineModelCollection;
    }
}
