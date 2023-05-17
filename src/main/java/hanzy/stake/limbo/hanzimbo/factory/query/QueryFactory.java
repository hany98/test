package hanzy.stake.limbo.hanzimbo.factory.query;

import hanzy.stake.limbo.hanzimbo.factory.query.model.TableModel;
import hanzy.stake.limbo.hanzimbo.factory.query.thread.SaveAllThread;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.metamodel.Type;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class QueryFactory {
    @PersistenceContext
    private EntityManager entityManager;
    public static Map<?, TableModel> tableModelMap;

    public QueryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
        tableModelMap = entityManager
                .getEntityManagerFactory()
                .getMetamodel()
                .getEntities()
                .stream()
                .map(Type::getJavaType)
                .filter(entityClass -> entityClass.isAnnotationPresent(Entity.class) && entityClass.isAnnotationPresent(Table.class))
                .map(TableModel::new)
                .collect(Collectors.toMap(TableModel::getEntityClass, model -> model));
    }

    public void saveAll(List<?> entityList, Class<?> entityClass) {
        if(entityList.size() == 0)
            return;

        int maxSublistSize = 5000;
        int numberOfThreads = (int) Math.ceil(entityList.size() / (double) maxSublistSize);

        int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfAvailableProcessors);

        EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();

        for (int threadIndex = 0; threadIndex < numberOfThreads; threadIndex++) {
            int startIndex = threadIndex * maxSublistSize;
            int endIndex = (threadIndex == numberOfThreads - 1)
                    ? Math.min(entityList.size(), startIndex + maxSublistSize)
                    : startIndex + maxSublistSize;

            executorService.execute(new SaveAllThread(entityList.subList(startIndex, endIndex), entityClass, entityManagerFactory));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
