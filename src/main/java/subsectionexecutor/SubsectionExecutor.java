package subsectionexecutor;

import java.util.List;

/**
 * @author shenlw
 * @date 9/20/2019 5:18 PM
 */
public abstract class SubsectionExecutor<T, R> {

    private final Compose<R> compose;
    private Wait wait;
    private int batch;

    public SubsectionExecutor(Compose<R> compose, int batch) {
        assert compose != null;
        this.compose = compose;
        this.batch = batch;
    }

    public R exec(List<T> sources) {
        int index = 0;
        int size = sources.size();
        while (index < size) {
            List<T> sub = sources.subList(index, Math.min(size, index + batch));
            R result = doExec(sub);
            compose.put(result);
            index += batch;
            if (wait != null) {
                wait.await();
            }
        }
        return compose.get();
    }

    protected abstract R doExec(List<T> sub);

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public Wait getWait() {
        return wait;
    }

    public void setWait(Wait wait) {
        this.wait = wait;
    }
}