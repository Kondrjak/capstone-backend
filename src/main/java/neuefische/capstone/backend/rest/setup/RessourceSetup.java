package neuefische.capstone.backend.rest.setup;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import neuefische.capstone.backend.rest.model.CodepointGroup;
import neuefische.capstone.backend.rest.model.Font;
import neuefische.capstone.backend.rest.model.Tag;
import neuefische.capstone.backend.rest.ressource.CodepointGroupRessource;
import neuefische.capstone.backend.rest.ressource.FontRessource;
import neuefische.capstone.backend.rest.ressource.TagRessource;
import neuefische.capstone.backend.util.ClosedRange;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Loads initial tags, fonts, codepoint-groups, key-layouts and key-revolvers
 */
@Component
@RequiredArgsConstructor
public class RessourceSetup implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final CodepointGroupRessource codepointGroupRepo;
    private final TagRessource tagRepo;
    private final FontRessource fontRepo;

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // add tags to repository
        Tag english = tagRepo.save(Tag.builder().name("english").build());
        Tag numbers = tagRepo.save(Tag.builder().name("numbers").build());
        Tag lowercase = tagRepo.save(Tag.builder().name("lowercase").build());
        Tag uppercase = tagRepo.save(Tag.builder().name("uppercase").build());
        Tag greek = tagRepo.save(Tag.builder().name("greek").build());
        Tag serif = tagRepo.save(Tag.builder().name("serif").build());
        Tag bold = tagRepo.save(Tag.builder().name("bold").build());
        Tag extras = tagRepo.save(Tag.builder().name("extras").build());
        Tag italic = tagRepo.save(Tag.builder().name("italic").build());
        Tag dotless = tagRepo.save(Tag.builder().name("dotless").build());
        Tag sans = tagRepo.save(Tag.builder().name("sans").build());
        Tag monospace = tagRepo.save(Tag.builder().name("monospace").build());
        Tag script = tagRepo.save(Tag.builder().name("script").build());
        Tag fraktur = tagRepo.save(Tag.builder().name("fraktur").build());
        Tag blackboard = tagRepo.save(Tag.builder().name("blackboard").build());
        Tag yi = tagRepo.save(Tag.builder().name("yi").build());
        Tag banum = tagRepo.save(Tag.builder().name("banum").build());
        Tag replaceable = tagRepo.save(Tag.builder().name("replaceable").build());
        Tag ascii = tagRepo.save(Tag.builder().name("ASCII").build());
        Tag sums = tagRepo.save(Tag.builder().name("sums").build());
        Tag products = tagRepo.save(Tag.builder().name("products").build());
        Tag integrals = tagRepo.save(Tag.builder().name("integrals").build());
        Tag math = tagRepo.save(Tag.builder().name("math").build());

        // add fonts to repository
        Font arial = fontRepo.save(Font.builder().name("Arial").build());
        Font jetBrainsMono = fontRepo.save(Font.builder().name("JetBrains Mono").build());

        // add codepoint groups to repository
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, ascii))
                .fonts(List.of(arial, jetBrainsMono))
                .name("0-9")
                .codepoints(ClosedRange.between("0", "9"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, ascii))
                .fonts(List.of(arial, jetBrainsMono))
                .name("a-z")
                .codepoints(ClosedRange.between("a", "z"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, ascii))
                .fonts(List.of(arial, jetBrainsMono))
                .name("A-Z")
                .codepoints(ClosedRange.between("A", "Z"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase))
                .fonts(List.of(arial, jetBrainsMono))
                .name("??-??")
                .codepoints(ClosedRange.between("??", "??"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase))
                .fonts(List.of(arial, jetBrainsMono))
                .name("??-??")
                .codepoints(ClosedRange.between("??", "??"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());

        List<String> greekExtrasSerifBold = ClosedRange.between("????", "????");
        greekExtrasSerifBold.add("????");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(greekExtrasSerifBold)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, italic))
                .fonts(List.of(arial))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, extras, serif, italic, dotless))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????&????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        List<String> greekExtrasSerifItalic = ClosedRange.between("????", "????");
        greekExtrasSerifItalic.add("???");
        greekExtrasSerifItalic.add("????");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(greekExtrasSerifItalic)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        List<String> greekExtrasSerifItalicBold = ClosedRange.between("????", "????");
        greekExtrasSerifItalic.add("????");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(greekExtrasSerifItalicBold)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, sans))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        List<String> greekExtrasSansBold = ClosedRange.between("????", "????");
        greekExtrasSansBold.add("????");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(greekExtrasSansBold)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, sans, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        List<String> greekExtrasSansBoldItalic = ClosedRange.between("????", "????");
        greekExtrasSansBold.add("????");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(greekExtrasSansBoldItalic)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, monospace))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, monospace))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, monospace))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, script))
                .fonts(List.of(arial))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, script))
                .fonts(List.of(arial))
                .name("\uD835\uDC9C-\uD835\uDCB5")
                .codepoints(ClosedRange.between("\uD835\uDC9C", "\uD835\uDCB5"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, script, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, script, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, fraktur))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, fraktur))
                .fonts(List.of(arial))
                .name("\uD835\uDD04-\uD835\uDD1D")
                .codepoints(ClosedRange.between("\uD835\uDD04", "\uD835\uDD1D"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, fraktur, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, fraktur, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, blackboard))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, blackboard))
                .fonts(List.of(arial, jetBrainsMono))
                .name("????-????")
                .codepoints(ClosedRange.between("????", "????"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, blackboard))
                .fonts(List.of(arial))
                .name("\uD835\uDD38-\uD835\uDD51")
                .codepoints(ClosedRange.between("\uD835\uDD38", "\uD835\uDD51"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, blackboard, italic, extras))
                .fonts(List.of(arial, jetBrainsMono))
                .name("???-???")
                .codepoints(ClosedRange.between("???", "???"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, blackboard, extras))
                .fonts(List.of(arial, jetBrainsMono))
                .name("???-???")
                .codepoints(ClosedRange.between("???", "???"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, yi))
                .fonts(List.of(arial, jetBrainsMono))
                .name("???-???")
                .codepoints(List.of(
                        "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???",
                        "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, banum))
                .fonts(List.of(arial, jetBrainsMono))
                .name("???-???")
                .codepoints(List.of(
                        "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???",
                        "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, script, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("\uD835\uDC9C???\uD835\uDCB5")
                .codepoints(List.of(
                        "????", "???", "????", "????", "???", "???", "????", "???", "???", "????", "????", "???", "???",
                        "????", "????", "????", "????", "???", "????", "????", "????", "????", "????", "????", "????", "????")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, script, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("???????????")
                .codepoints(List.of(
                        "????", "????", "????", "????", "???", "????", "???", "????", "????", "????", "????", "????", "????",
                        "????", "???", "????", "????", "????", "????", "????", "????", "????", "????", "????", "????", "????")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, italic, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("???????????")
                .codepoints(List.of(
                        "????", "????", "????", "????", "????", "????", "????", "???", "????", "????", "????", "????", "????",
                        "????", "????", "????", "????", "????", "????", "????", "????", "????", "????", "????", "????", "????")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, blackboard, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("??????????")
                .codepoints(List.of(
                        "????", "????", "???", "????", "????", "????", "????", "???", "????", "????", "????", "????", "????",
                        "???", "????", "???", "???", "???", "????", "????", "????", "????", "????", "????", "????", "???")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, blackboard, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("??????????")
                .codepoints(List.of(
                        "????", "????", "???", "????", "????", "????", "????", "???", "???", "????", "????", "????", "????",
                        "????", "????", "????", "????", "???", "????", "????", "????", "????", "????", "????", "????", "???")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(sums, integrals, products, math))
                .fonts(List.of(jetBrainsMono))
                .name("?????????")
                .codepoints(List.of(// sums
                        "???", "???", "???", "???", "???", "???",
                        // integrals
                        "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???",
                        "???", "???", "???", "???", "???",
                        // products
                        "???", "???", "???", "???", "???", "???", "???", "???")
                )
                .build());



        alreadySetup = true;
    }
}

