package neuefische.capstone.backend.util;

import javassist.compiler.ast.Symbol;
import neuefische.capstone.backend.util.exception.RangeOfIncompatibleStringsException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ClosedRange {

    public static List<String> between(String startingGlyph, String finalGlyph)
            throws RangeOfIncompatibleStringsException {

        List<String> glyphs;

        if (startingGlyph.toCharArray().length == 2 && finalGlyph.toCharArray().length == 2) {
            char utfPrefix = startingGlyph.toCharArray()[0];
            if (utfPrefix != finalGlyph.toCharArray()[0]) {
                throw new RangeOfIncompatibleStringsException(
                        "both glyphs have different prefix, " +
                                "the method can not provide a range for this case, " +
                                "your range might be very long, you can split your range");
            }
            char suffix1 = startingGlyph.toCharArray()[1];
            char suffix2 = finalGlyph.toCharArray()[1];
            if (suffix1 < suffix2) {
                glyphs = initSymbolsFromUtfRange(utfPrefix, suffix1, suffix2);
            } else {
                glyphs = initSymbolsFromUtfRange(utfPrefix, suffix2, suffix1);
            }
        } else if (startingGlyph.toCharArray().length == 1 && finalGlyph.toCharArray().length == 1) {
            glyphs = new ArrayList<>();
            char endIndex = (char) max(startingGlyph.charAt(0), finalGlyph.charAt(0));
            for (char charIndex = (char) min(startingGlyph.charAt(0), finalGlyph.charAt(0));
                 charIndex <= endIndex; charIndex++) {
                glyphs.add(String.valueOf(charIndex));
            }
        } else {
            throw new RangeOfIncompatibleStringsException(
                    "\nBoth input strings must have either one char, " +
                            "\nor two chars for surrogate pairs with same prefix."+
                            "\ninput was startingGlyph: "+startingGlyph+
                            " \t finalGlyph: "+finalGlyph);
        }
        return glyphs;
    }

    private static List<String> initSymbolsFromUtfRange(char utf8Prefix, char utf8Start, char utf8End) {
        List<String> glyphs = new ArrayList<>();
        char[] fullGlyph = new char[2];
        fullGlyph[0] = utf8Prefix;
        for (char utf8Suffix = utf8Start; utf8Suffix <= utf8End; utf8Suffix++) {
            fullGlyph[1] = utf8Suffix;
            glyphs.add(String.valueOf(fullGlyph));
        }
        return glyphs;
    }
}
