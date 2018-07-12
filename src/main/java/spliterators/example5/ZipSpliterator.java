package spliterators.example5;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import spliterators.example4.Pair;

public class ZipSpliterator<T, U> extends Spliterators.AbstractSpliterator<Pair<T,U>> {

    private Spliterator<T> origin;
    private Spliterator<U> another;

    public ZipSpliterator(Spliterator<T> origin, Spliterator<U> another) {
        // TODO implementation
        super(origin.estimateSize(), another.characteristics() & ~CONCURRENT);
        this.origin = origin;
        this.another = another;
    }


    @Override
    public boolean tryAdvance(Consumer<? super Pair<T,U>> action) {
        // TODO implementation
        return origin.tryAdvance(
            x->another.tryAdvance(
                y->action.accept(new Pair<>(x,y))));
    }
}