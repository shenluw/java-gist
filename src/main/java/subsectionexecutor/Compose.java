package subsectionexecutor;

import java.util.List;
import java.util.Set;

/**
 * @author shenlw
 * @date 9/20/2019 5:19 PM
 */
public interface Compose<E> {

    void put(E o);

    E get();

    void reset();

    class ListCompose<T> implements Compose<List<T>> {

        private List<T> data;

        @Override
        public void put(List<T> o) {
            if (data == null) {
                data = o;
            } else {
                data.addAll(o);
            }
        }

        @Override
        public List<T> get() {
            return data;
        }

        @Override
        public void reset() {
            data = null;
        }
    }

    class SetCompose<T> implements Compose<Set<T>> {

        private Set<T> data;

        @Override
        public void put(Set<T> o) {
            if (data == null) {
                data = o;
            } else {
                data.addAll(o);
            }
        }

        @Override
        public Set<T> get() {
            return data;
        }

        @Override
        public void reset() {
            data = null;
        }
    }

}
