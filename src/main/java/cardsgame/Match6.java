package cardsgame;

import cardsgame.card.Card;
import cardsgame.card.ComparableCard;

public class Match6 extends Matcher {
    public Match6() {
        super("6-s");
    }

    public static void main(String[] args) {
        new Match6();
    }

    @Override
    protected void match(Card c1) {
        for (Card c2 : c1.withDotMatchingMyCircle()) {
            for (Card flipOfC2 : ComparableCard.flips(c2)) {
                if (c1.circleMatchesDot(flipOfC2)) {
                    for (Card c3 : c2.withDotMatchingMyCircle()) {
                        for (Card flipOfC3 : ComparableCard.flips(c3)) {
                            if (flipOfC2.circleMatchesDot(flipOfC3)) {
                                for (Card c4 : c3.withDotMatchingMyCircle()) {
                                    for (Card flipOfC4 : ComparableCard.flips(c4)) {
                                        if (flipOfC3.circleMatchesDot(flipOfC4)) {
                                            for (Card c5 : c4.withDotMatchingMyCircle()) {
                                                for (Card flipOfC5 : ComparableCard.flips(c5)) {
                                                    if (flipOfC4.circleMatchesDot(flipOfC5)) {
                                                        for (Card c6 : c5.withDotMatchingMyCircle()) {
                                                            for (Card flipOfC6 : ComparableCard.flips(c6)) {
                                                                if (flipOfC5.circleMatchesDot(flipOfC6) && flipOfC6.circleMatchesDot(c1)) {
                                                                    add(c1, c2, c3, c4, c5, c6);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
