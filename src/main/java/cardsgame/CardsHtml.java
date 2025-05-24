package cardsgame;

import cardsgame.card.Card;
import cardsgame.spot.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CardsHtml {
    private static final int SIZE = 50;

    public static void main(String[] args) throws IOException {
        String styles = Stream.of(Color.values())
                .map(Color::html)
                .collect(Collectors.joining("\n        "));
        String cardsHtml = Card.cards.stream()
                .map(Card::toHtml)
                .collect(Collectors.joining("\n    "));
        Files.writeString(
                Path.of("cards.html"),
                """
                        <html>
                        <head>
                            <style>
                                :root {
                                    --body-bg-color: #ffffff;
                                    --body-color: #000000;
                                    --CARD_BORDER_WIDTH: 5px;
                                    --SIZE: {{SIZE}}px;
                                    --PADDING: calc(var(--SIZE) / 5);
                                }
                                html.dark-theme {
                                  --body-bg-color: #000000;
                                  --body-color: #ffffff;
                                }
                                body {
                                    margin: var(--PADDING) 0 0 var(--PADDING);
                                    background-color: var(--body-bg-color);
                                    color: var(--body-color);
                                }
                                label {
                                    display: block;
                                    padding: var(--PADDING);
                                    text-align: right;
                                    user-select: none;
                                    font-family: Arial, sans-serif;
                                }
                                label input[type="checkbox"] {
                                    margin-right: 5px;
                                }
                                div { box-sizing: border-box; }
                                .card {
                                    display: inline-block;
                                    margin: 0 var(--PADDING) var(--PADDING) 0;
                                    width:  calc(3 * calc(var(--SIZE) + var(--PADDING)) + var(--PADDING) + 2 * var(--CARD_BORDER_WIDTH));
                                    height: calc(4 * calc(var(--SIZE) + var(--PADDING)) + var(--PADDING) + 2 * var(--CARD_BORDER_WIDTH));
                                    border: var(--CARD_BORDER_WIDTH) solid purple;
                                    border-radius: calc(3 * var(--CARD_BORDER_WIDTH));
                                    position: relative;
                                }
                                .card div {
                                    position: absolute;
                                    display: inline-block;
                                    border-radius: 50%;
                                    border-style: solid;
                                }
                                .card .dot {
                                    margin: calc(var(--SIZE) / 4);
                                    width: calc(var(--SIZE) / 2);
                                    height: calc(var(--SIZE) / 2);
                                    border-width: 1px;
                                }
                                .card .circle {
                                    width: var(--SIZE);
                                    height: var(--SIZE);
                                    border-width: calc(var(--SIZE) / 4);
                                }
                                .card .left_0 { left: calc(var(--PADDING) + 0 * calc(var(--PADDING) + var(--SIZE))); }
                                .card .left_1 { left: calc(var(--PADDING) + 1 * calc(var(--PADDING) + var(--SIZE))); }
                                .card .left_2 { left: calc(var(--PADDING) + 2 * calc(var(--PADDING) + var(--SIZE))); }
                                .card .top_0 { top: calc(var(--PADDING) + 0 * calc(var(--PADDING) + var(--SIZE))); }
                                .card .top_1 { top: calc(var(--PADDING) + 1 * calc(var(--PADDING) + var(--SIZE))); }
                                .card .top_2 { top: calc(var(--PADDING) + 2 * calc(var(--PADDING) + var(--SIZE))); }
                                .card .top_3 { top: calc(var(--PADDING) + 3 * calc(var(--PADDING) + var(--SIZE))); }
                                {{COLORS_STYLES}}
                            </style>
                        </head>
                        <body>
                            <label><input type="checkbox" id="theme-switcher">Dark mode</label>
                            {{CARDS_HTML}}
                            <script>
                                const themeSwitcher = document.getElementById("theme-switcher");
                                themeSwitcher.addEventListener("change", () => {
                                    if (themeSwitcher.checked) {
                                        document.documentElement.classList.add("dark-theme");
                                    } else {
                                        document.documentElement.classList.remove("dark-theme");
                                    }
                                });
                            </script>
                        </body>
                        </html>
                        """
                        .replace("{{SIZE}}", String.valueOf(SIZE))
                        .replace("{{COLORS_STYLES}}", styles)
                        .replace("{{CARDS_HTML}}", cardsHtml));
    }
}
