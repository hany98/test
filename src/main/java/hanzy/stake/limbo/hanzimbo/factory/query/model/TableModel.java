package hanzy.stake.limbo.hanzimbo.factory.query.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class TableModel {
    private String tableName;
    private Class<?> entityClass;
    private List<FieldModel> fieldModels;

    public TableModel(Class<?> entityClass) {
        this.entityClass = entityClass;
        this.tableName = entityClass.getAnnotation(Table.class).name().toLowerCase();
        this.fieldModels = buildFieldModelList(entityClass);
    }

    private List<FieldModel> buildFieldModelList(Class<?> entityClass) {
        return Arrays
                .stream(entityClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(FieldModel::new)
                .collect(Collectors.toList());
    }

    public String buildInsertQuery() {
        String tableFields = fieldModels
                .stream()
                .map(FieldModel::getColumnName)
                .reduce((name1, name2) -> name1.concat(", ").concat(name2))
                .orElse("");
        return String.format("INSERT INTO %s(%s) VALUES ", tableName, tableFields);
    }
}
