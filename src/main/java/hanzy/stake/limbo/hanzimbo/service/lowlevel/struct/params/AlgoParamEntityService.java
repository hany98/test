package hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.params;

import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;

import java.util.List;
import java.util.Optional;

public interface AlgoParamEntityService<T,I> {
    public void saveAll(List<T> entities);

    public List<T> findAll();

    public T save(I entity);

    public Optional<T> findByParam(AlgoParam params);

}
