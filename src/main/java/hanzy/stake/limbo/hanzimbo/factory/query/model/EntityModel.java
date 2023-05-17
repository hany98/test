package hanzy.stake.limbo.hanzimbo.factory.query.model;

import hanzy.stake.limbo.hanzimbo.factory.query.QueryFactory;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntityModel {
    private TableModel tableModel;
    private Object entity;

    public EntityModel(Object entity) {
        this.entity = entity;
        this.tableModel = QueryFactory.tableModelMap.get(entity.getClass());
    }

    public String toString() {
        return "("
                .concat(this.tableModel
                        .getFieldModels()
                        .stream()
                        .map(fieldModel -> fieldModel.getFieldValue(this.entity))
                        .reduce((name1, name2) -> name1.concat(", ").concat(name2))
                        .orElse(""))
                .concat(")");
    }
}
