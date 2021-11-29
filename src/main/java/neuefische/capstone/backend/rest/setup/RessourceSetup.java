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
                .name("α-ω")
                .codepoints(ClosedRange.between("α", "ω"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase))
                .fonts(List.of(arial, jetBrainsMono))
                .name("Α-Ω")
                .codepoints(ClosedRange.between("Α", "Ω"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝟎-𝟗")
                .codepoints(ClosedRange.between("𝟎", "𝟗"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝐚-𝐳")
                .codepoints(ClosedRange.between("𝐚", "𝐳"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝐀-𝐙")
                .codepoints(ClosedRange.between("𝐀", "𝐙"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝛂-𝛚")
                .codepoints(ClosedRange.between("𝛂", "𝛚"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝚨-𝛀")
                .codepoints(ClosedRange.between("𝚨", "𝛀"))
                .build());

        List<String> greekExtrasSerifBold = ClosedRange.between("𝛛", "𝛡");
        greekExtrasSerifBold.add("𝛁");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, serif, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝛛-𝛁")
                .codepoints(greekExtrasSerifBold)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, italic))
                .fonts(List.of(arial))
                .name("𝑎-𝑧")
                .codepoints(ClosedRange.between("𝑎", "𝑧"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝐴-𝑍")
                .codepoints(ClosedRange.between("𝐴", "𝑍"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, extras, serif, italic, dotless))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝚤&𝚥")
                .codepoints(ClosedRange.between("𝚤", "𝚥"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝛼-𝜔")
                .codepoints(ClosedRange.between("𝛼", "𝜔"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝛢-𝛺")
                .codepoints(ClosedRange.between("𝛢", "𝛺"))
                .build());
        List<String> greekExtrasSerifItalic = ClosedRange.between("𝜕", "𝜛");
        greekExtrasSerifItalic.add("ℇ");
        greekExtrasSerifItalic.add("𝛻");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, serif, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝜕-𝛻")
                .codepoints(greekExtrasSerifItalic)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝒂-𝒛")
                .codepoints(ClosedRange.between("𝒂", "𝒛"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝑨-𝒁")
                .codepoints(ClosedRange.between("𝑨", "𝒁"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝜶-𝝎")
                .codepoints(ClosedRange.between("𝜶", "𝝎"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝜜-𝜴")
                .codepoints(ClosedRange.between("𝜜", "𝜴"))
                .build());
        List<String> greekExtrasSerifItalicBold = ClosedRange.between("𝝏", "𝝕");
        greekExtrasSerifItalic.add("𝜵");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, serif, italic, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝝏-𝜵")
                .codepoints(greekExtrasSerifItalicBold)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, sans))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝟢-𝟫")
                .codepoints(ClosedRange.between("𝟢", "𝟫"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝖺-𝗓")
                .codepoints(ClosedRange.between("𝖺", "𝗓"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝖠-𝖹")
                .codepoints(ClosedRange.between("𝖠", "𝖹"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝟬-𝟵")
                .codepoints(ClosedRange.between("𝟬", "𝟵"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝗮-𝘇")
                .codepoints(ClosedRange.between("𝗮", "𝘇"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝗔-𝗭")
                .codepoints(ClosedRange.between("𝗔", "𝗭"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝝰-𝞈")
                .codepoints(ClosedRange.between("𝝰", "𝞈"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝝖-𝝮")
                .codepoints(ClosedRange.between("𝝖", "𝝮"))
                .build());
        List<String> greekExtrasSansBold = ClosedRange.between("𝞉", "𝞏");
        greekExtrasSansBold.add("𝝯");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, sans, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝞉-𝝯")
                .codepoints(greekExtrasSansBold)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝘢-𝘻")
                .codepoints(ClosedRange.between("𝘢", "𝘻"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, sans, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝘈-𝘡")
                .codepoints(ClosedRange.between("𝘈", "𝘡"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝙖-𝙯")
                .codepoints(ClosedRange.between("𝙖", "𝙯"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝘼-𝙕")
                .codepoints(ClosedRange.between("𝘈", "𝘡"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, lowercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝞪-𝟂")
                .codepoints(ClosedRange.between("𝞪", "𝟂"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, uppercase, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝞐-𝞨")
                .codepoints(ClosedRange.between("𝞐", "𝞨"))
                .build());
        List<String> greekExtrasSansBoldItalic = ClosedRange.between("𝟃", "𝟉");
        greekExtrasSansBold.add("𝞩");
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, extras, sans, bold, italic))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝟃-𝞩")
                .codepoints(greekExtrasSansBoldItalic)
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, monospace))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝟶-𝟿")
                .codepoints(ClosedRange.between("𝟶", "𝟿"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, monospace))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝚊-𝚣")
                .codepoints(ClosedRange.between("𝚊", "𝚣"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, monospace))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝙰-𝚉")
                .codepoints(ClosedRange.between("𝙰", "𝚉"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, script))
                .fonts(List.of(arial))
                .name("𝒶-𝓏")
                .codepoints(ClosedRange.between("𝒶", "𝓏"))
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
                .name("𝓪-𝔃")
                .codepoints(ClosedRange.between("𝓪", "𝔃"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, script, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝓐-𝓩")
                .codepoints(ClosedRange.between("𝓐", "𝓩"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, fraktur))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝔞-𝔷")
                .codepoints(ClosedRange.between("𝔞", "𝔷"))
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
                .name("𝖆-𝖟")
                .codepoints(ClosedRange.between("𝔞", "𝔷"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, fraktur, bold))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝕬-𝖅")
                .codepoints(ClosedRange.between("𝕬", "𝖅"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, numbers, blackboard))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝟘-𝟡")
                .codepoints(ClosedRange.between("𝟘", "𝟡"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, blackboard))
                .fonts(List.of(arial, jetBrainsMono))
                .name("𝕒-𝕫")
                .codepoints(ClosedRange.between("𝕒", "𝕫"))
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
                .name("ⅅ-ⅉ")
                .codepoints(ClosedRange.between("ⅅ", "ⅉ"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(greek, blackboard, extras))
                .fonts(List.of(arial, jetBrainsMono))
                .name("ℼ-⅀")
                .codepoints(ClosedRange.between("ⅅ", "ⅉ"))
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, yi))
                .fonts(List.of(arial, jetBrainsMono))
                .name("ꋬ-ꁴ")
                .codepoints(List.of(
                        "ꋬ", "ꃃ", "꒝", "ꀒ", "ꍠ", "ꅲ", "ꁍ", "ꃅ", "ꀤ", "꒻", "ꀘ", "꒒", "꒿",
                        "ꀊ", "ꃪ", "ꉣ", "ꈲ", "ꋪ", "ꌚ", "꓅", "ꐇ", "ꃴ", "ꅐ", "ꉧ", "ꐟ", "ꁴ")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, banum))
                .fonts(List.of(arial, jetBrainsMono))
                .name("ꋬ-ꛉ")
                .codepoints(List.of(
                        "ꋬ", "ꃃ", "ꛕ", "ꀒ", "ꍠ", "ꛂ", "ꁍ", "ꛅ", "ꛢ", "ꚠ", "ꚾ", "ꛚ", "ꤵ",
                        "ꚡ", "ꚸ", "ꚰ", "ꈲ", "ꛓ", "ꌚ", "꓅", "ꚶ", "ꛟ", "ꛃ", "ꉧ", "ꚵ", "ꛉ")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, script, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("\uD835\uDC9C∽\uD835\uDCB5")
                .codepoints(List.of(
                        "𝒜", "ℬ", "𝒞", "𝒟", "ℰ", "ℱ", "𝒢", "ℋ", "ℐ", "𝒥", "𝒦", "ℋ", "ℳ",
                        "𝒩", "𝒪", "𝒫", "𝒬", "ℛ", "𝒮", "𝒯", "𝒰", "𝒱", "𝒲", "𝒳", "𝒴", "𝒵")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, script, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("𝒶∽𝓏")
                .codepoints(List.of(
                        "𝒶", "𝒷", "𝒸", "𝒹", "ℯ", "𝒻", "ℊ", "𝒽", "𝒾", "𝒿", "𝓀", "𝓁", "𝓂",
                        "𝓃", "ℴ", "𝓅", "𝓆", "𝓇", "𝓈", "𝓉", "𝓊", "𝓋", "𝓌", "𝓍", "𝓎", "𝓏")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, lowercase, serif, italic, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("𝑎∽𝑧")
                .codepoints(List.of(
                        "𝑎", "𝑏", "𝑐", "𝑑", "𝑒", "𝑓", "𝑔", "ℎ", "𝑖", "𝑗", "𝑘", "𝑙", "𝑚",
                        "𝑛", "𝑜", "𝑝", "𝑞", "𝑟", "𝑠", "𝑡", "𝑢", "𝑣", "𝑤", "𝑥", "𝑦", "𝑧")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, blackboard, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("𝔸∽ℤ")
                .codepoints(List.of(
                        "𝔸", "𝔹", "ℂ", "𝔻", "𝔼", "𝔽", "𝔾", "ℍ", "𝕀", "𝕁", "𝕂", "𝕃", "𝕄",
                        "ℕ", "𝕆", "ℙ", "ℚ", "ℝ", "𝕊", "𝕋", "𝕌", "𝕍", "𝕎", "𝕏", "𝕐", "ℤ")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(english, uppercase, blackboard, replaceable))
                .fonts(List.of(jetBrainsMono))
                .name("𝔄∽ℨ")
                .codepoints(List.of(
                        "𝔄", "𝔅", "ℭ", "𝔇", "𝔈", "𝔉", "𝔊", "ℌ", "ℐ", "𝔍", "𝔎", "𝔏", "𝔐",
                        "𝔑", "𝔒", "𝔓", "𝔔", "ℜ", "𝔖", "𝔗", "𝔘", "𝔙", "𝔚", "𝔛", "𝔜", "ℨ")
                )
                .build());
        codepointGroupRepo.save(CodepointGroup.builder()
                .tags(List.of(sums, integrals, products, math))
                .fonts(List.of(jetBrainsMono))
                .name("∑∫∏")
                .codepoints(List.of(// sums
                        "∑", "⨊", "⅀", "⨋", "⊕", "⊞",
                        // integrals
                        "∫", "∬", "∭", "∯", "∰", "⨛", "⨜", "⨍", "⨎", "⨏", "⨌", "∲", "∳", "∱", "⨑", "⨐", "⨒", "⨓", "⨔", "⨕",
                        "⨖", "⨗", "⨙", "⨚", "⨘",
                        // products
                        "∏", "∐", "ℿ", "╳", "☓", "⨳", "⨷", "⨶")
                )
                .build());



        alreadySetup = true;
    }
}

