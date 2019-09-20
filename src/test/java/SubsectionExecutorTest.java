import org.junit.Test;
import subsectionexecutor.Compose.ListCompose;
import subsectionexecutor.SubsectionExecutor;
import subsectionexecutor.Wait.DefaultWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenlw
 * @date 9/20/2019 5:27 PM
 */
public class SubsectionExecutorTest {

    @Test
    public void exec() {

        SubsectionExecutor<String, List<String>> executor = new SubsectionExecutor<String, List<String>>(new ListCompose<>(), 5) {

            @Override
            protected List<String> doExec(List<String> sub) {
                System.out.println("run: " + System.currentTimeMillis());
                return sub.stream().map(s -> s + Math.random()).collect(Collectors.toList());
            }
        };
        executor.setWait(new DefaultWait(1000));

        List<String> src = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            src.add(String.format("index %s;", i));
        }
        System.out.println(executor.exec(src));

    }
}
