package spliterators.exercise;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.junit.Test;

public class Exercise1 {

    @Test
    public void testSumUsingSpliterator() {
        int[][] data = Stream.generate(() -> IntStream.generate(() -> 1)
                                                      .limit(5)
                                                      .toArray())
                             .limit(10)
                             .toArray(int[][]::new);

        IntStream stream = StreamSupport.intStream(new FairRectangleSpliterator(data), false);

        assertEquals(50, stream.sum());
    }

    @Test
    public void testMinUsingSpliterator() {
        int[][] data = Stream.generate(() -> IntStream.generate(() -> 42)
                                                      .limit(5)
                                                      .toArray())
                             .limit(10)
                             .toArray(int[][]::new);
        data[0][0] = 0;

        IntStream stream = StreamSupport.intStream(new FairRectangleSpliterator(data), false);

        assertEquals(0, stream.min().orElseThrow(IllegalStateException::new));
    }

    @Test
    public void testMaxUsingSpliterator() {
          int[][] data = Stream.generate(() -> IntStream.generate(() -> 42)
                                                      .limit(5)
                                                      .toArray())
                             .limit(10)
                             .toArray(int[][]::new);
        data[3][3] = 50;

        IntStream stream = StreamSupport.intStream(new FairRectangleSpliterator(data), true);

        assertEquals(50, stream.max().orElseThrow(IllegalStateException::new));
    }
}
