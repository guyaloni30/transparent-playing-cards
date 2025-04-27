package cardsgame;

import cardsgame.card.Card;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public abstract class Matcher {
    protected final Map<String, Set<String>> matches = new TreeMap<>();

    public Matcher(String type) {
        for (Card c1 : Card.distinct) {
            match(c1);
        }
        matches.forEach((k, v) -> {
            if (v.size() == 1) {
                System.out.println(k + " - " + v.iterator().next());
            } else {
                System.out.println(k);
                v.forEach(a -> System.out.println("\t" + a));
            }
        });
        System.out.println(Card.distinct.size() + " distinct cards with " + matches.values().stream().flatMap(s -> s.stream()).count() + " " + type);
    }

    protected void add(Card c1, Card... others) {
        matches.computeIfAbsent(c1.ansi(), _ -> new TreeSet<>()).add(Stream.of(others).map(Card::ansi).collect(joining(", ")));
    }

    protected abstract void match(Card c1);
}
