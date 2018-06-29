package streams.part2.exercise;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.Test;

public class Exercise4 {

    /**
     * Выбирает из текста наиболее часто встречающиеся слова. Подсчет слов выполняется без учета их
     * регистра, т.е. "Привет", "привет", "пРиВеТ" - одно и то же слово. Если некоторые слова имеют
     * одинаковую частоту, то в выходном списке они упорядочиваются в лексикографическом порядке.
     *
     * @param text Исходный текст в котором слова (в смешанном регистре) разделены пробелами.
     * @param numberWords Количество наиболее часто встречающихся слов, которые необходимо
     * отобрать.
     * @return Список отобранных слов (в нижнем регистре).
     */
    private List<String> getFrequentlyOccurringWords(String text, int numberWords) {
        Map<String, Long> map = Pattern.compile("\\s+").splitAsStream(text)
                                       .map(String::toLowerCase).collect(
                Collectors.groupingBy(string -> string, Collectors.counting()));

        return map.entrySet().stream().sorted(
            Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed()
                                                                                  .thenComparing(
                                                                                      Map.Entry::getKey))
                  .limit(numberWords).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    @Test
    public void test1() {
        String source = "Мама мыла мыла мыла раму";

        List<String> result = getFrequentlyOccurringWords(source, 5);

        assertEquals(Arrays.asList("мыла", "мама", "раму"), result);
    }

    @Test
    public void test2() {
        String source =
            "Lorem ipsum dolor sit amet consectetur adipiscing elit Sed sodales consectetur purus at "
                + "faucibus Donec mi quam tempor vel ipsum non faucibus suscipit massa Morbi lacinia velit "
                + "blandit tincidunt efficitur Vestibulum eget metus imperdiet sapien laoreet faucibus Nunc "
                + "eget vehicula mauris ac auctor lorem Lorem ipsum dolor sit amet consectetur adipiscing elit "
                + "Integer vel odio nec mi tempor dignissim";

        List<String> result = getFrequentlyOccurringWords(source, 10);

        assertEquals(Arrays
            .asList("consectetur", "faucibus", "ipsum", "lorem", "adipiscing", "amet", "dolor",
                "eget", "elit", "mi"), result);
    }
}
