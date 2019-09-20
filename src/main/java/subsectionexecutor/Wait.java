package subsectionexecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 批处理等待操作
 *
 * @author shenlw
 * @date 9/20/2019 5:22 PM
 */
public abstract class Wait {

    /**
     * 每次等待时间
     */
    protected long time;

    public Wait(long time) {
        this.time = time;
    }

    public abstract boolean await();

    public static class DefaultWait extends Wait {

        public DefaultWait(long time) {
            super(time);
        }

        @Override
        public boolean await() {
            CountDownLatch latch = new CountDownLatch(1);
            try {
                return latch.await(time, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // ignore
            }
            return false;
        }
    }

}
