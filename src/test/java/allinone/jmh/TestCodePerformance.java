package allinone.jmh;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class TestCodePerformance {

    public static void main(String args[]) throws RunnerException {
        Options opt = new OptionsBuilder()
        .include(TestCodePerformance.class.getSimpleName())
        .warmupIterations(10)
        .mode(Mode.AverageTime)
        .timeUnit(TimeUnit.MILLISECONDS)
        .measurementIterations(10)
        .forks(1)
        .build();

        new Runner(opt).run();

    }

    @Benchmark
    public static void helloWorld() {

        List<Integer> ints = IntStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList());

        for (int i = 0; i < ints.size(); i++) {
            if (ints.get(i).equals(5000)) {
                System.out.println("Found 1 in for loop. " + i);
            }
        }

    }

    @Benchmark
    public static void helloWorld2() {

        List<Integer> ints = IntStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList());

        Iterator<Integer> iter = ints.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(5000)) {
                System.out.printf("Found 1 in for loop. %s",iter.toString());
            }
        }

    }
}
