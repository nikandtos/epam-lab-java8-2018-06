package spliterators.example5;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TakeWhileSpliterator<T> extends Spliterators.AbstractSpliterator<T> {

    private final Spliterator<T> spliterator;
    private final Predicate<? super T> predicate;
    private boolean enough;


    public TakeWhileSpliterator(Spliterator<T> spliterator, Predicate<? super T> predicate) {
        // TODO implementation
        super(spliterator.estimateSize(), spliterator.characteristics() & ~(SIZED | SUBSIZED));
        this.spliterator = spliterator;
        this.predicate = predicate;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        // TODO implementation
        if (enough){
            return false;
        }
        spliterator.tryAdvance(value->{
            if(predicate.test(value)){
                action.accept(value);
            }else {
                enough = true;
            }
        });
        return true;
    }
}
