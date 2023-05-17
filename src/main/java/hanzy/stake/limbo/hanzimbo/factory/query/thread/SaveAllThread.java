package hanzy.stake.limbo.hanzimbo.factory.query.thread;

import hanzy.stake.limbo.hanzimbo.factory.query.model.EntityModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

import static hanzy.stake.limbo.hanzimbo.factory.query.QueryFactory.tableModelMap;

@Data
@AllArgsConstructor
public class SaveAllThread extends Thread {
    private List<?> subList;
    private Class<?> entityClass;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void run() {
        String queryStr = tableModelMap
                .get(entityClass)
                .buildInsertQuery()
                .concat(this.subList
                        .stream()
                        .map(entity -> new EntityModel(entity).toString())
                        .reduce((name1, name2) -> name1.concat(", ").concat(name2))
                        .orElse(""));

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin(); // Begin a new transaction
            Query query = entityManager.createNativeQuery(queryStr);
            query.executeUpdate();
            entityManager.getTransaction().commit(); // Commit the transaction
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Roll back the transaction if there was an error
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
