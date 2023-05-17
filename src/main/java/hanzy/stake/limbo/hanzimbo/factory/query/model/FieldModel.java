package hanzy.stake.limbo.hanzimbo.factory.query.model;

import hanzy.stake.limbo.hanzimbo.utils.StringUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Data
@Accessors(chain = true)
public class FieldModel {
    private Field field;
    private Class<?> type;
    private String getterName;
    private String columnName;
    private String attributeName;

    public FieldModel(Field field) {
        this.field = field;
        this.type = field.getType();
        this.attributeName = field.getName();
        this.columnName = buildColumnName(field);
        this.getterName = buildGetterName(field);
    }

    private String buildColumnName(Field field) {
        return field.getAnnotation(Column.class).name();
    }

    private String buildGetterName(Field field) {
        return ((field.getType() == boolean.class) ? "is" : "get").concat(StringUtils.CapitalizeFirstLetter(field.getName()));
    }

    private Object invokeFieldGetterName(Object entity, String fieldGetterName) {
        try {
            return entity.getClass().getMethod(fieldGetterName).invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private String checkForNeededQuotations(Field field, Object fieldValue) {
        return (field.getType() == String.class) ? StringUtils.surroundWithQuotations(fieldValue.toString()) : fieldValue.toString();
    }

    public String getFieldValue(Object entity) {
        Object fieldValue = invokeFieldGetterName(entity, getterName);
        return checkForNeededQuotations(field, fieldValue);
    }
}
