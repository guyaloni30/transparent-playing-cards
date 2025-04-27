package cardsgame;

import cardsgame.card.Card;
import cardsgame.spot.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class CardsHtml {
    public static void main(String[] args) throws IOException {
        String styles = Stream.of(
                        Stream.of(Color.values()).map(color -> color + " {border-color:" + color + ";}"),
                        IntStream.of(0, 1, 2).mapToObj(n -> "x_" + n + "{left:" + (10 + 100 * n) + "px;}"),
                        IntStream.of(0, 1, 2, 3).mapToObj(n -> "y_" + n + "{top:" + (10 + 100 * n) + "px;}"))
                .flatMap(s -> s)
                .map(s -> "        .card ." + s)
                .collect(Collectors.joining("\n"));
        String cardsHtml = Card.cards.stream()
                .map(Card::toHtml)
                .collect(Collectors.joining("\n"));
        Files.writeString(
                Path.of("/temp/cards.html"),
                """
                        <html>
                        <head>
                            <style>
                                body {margin:0;}
                                .card {display:inline-block;margin:10px 0 0 10px;padding:10px; width:320px;height:420px; border:5px solid purple;position:relative;}
                                .card div { position:absolute;display:inline-block;border-radius:50%;border-style:solid;border-width:25px;box-sizing: border-box;}
                                .card div:nth-child(1) {margin:25px;width:50px;height:50px;}
                                .card div:nth-child(2) {width:100px;height:100px;}
                                {{STYLES}}
                            </style>
                        </head>
                        <body>
                        {{CARDS_HTML}}
                        </body>
                        </html>
                        """
                        .replace("{{STYLES}}", styles)
                        .replace("{{CARDS_HTML}}", cardsHtml));
    }
}
