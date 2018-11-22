package gluttonmod;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import gluttonmod.cards.*;
import gluttonmod.characters.GluttonCharacter;
import gluttonmod.patches.AbstractCardEnum;
import gluttonmod.patches.GluttonEnum;
import gluttonmod.powers.AbstractGluttonPower;
import gluttonmod.relics.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

import static basemod.BaseMod.addRelicToCustomPool;

@SpireInitializer
public class GluttonMod implements EditCharactersSubscriber, EditRelicsSubscriber,
        EditCardsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, PostDrawSubscriber,
        OnStartBattleSubscriber {

    private static final Color GLUTTON_COLOR = CardHelper.getColor(75.0f, 175.0f, 75.0f);
    private static final String ASSETS_FOLDER = "images";

    private static final String ATTACK_CARD = "512/bg_attack_glutton.png";
    private static final String SKILL_CARD = "512/bg_skill_glutton.png";
    private static final String POWER_CARD = "512/bg_power_glutton.png";
    private static final String ENERGY_ORB = "512/card_glutton_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_glutton.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_glutton.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_glutton.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_glutton_orb.png";

    private static final String CHAR_BUTTON = "charSelect/button.png";
    private static final String CHAR_PORTRAIT = "charSelect/portrait.png";

    public static String getResourcePath(String resource) {
        return ASSETS_FOLDER + "/" + resource;
    }

    public static final Logger logger = LogManager.getLogger(GluttonMod.class);

    public GluttonMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.GLUTTON,
                GLUTTON_COLOR, GLUTTON_COLOR, GLUTTON_COLOR, GLUTTON_COLOR, GLUTTON_COLOR, GLUTTON_COLOR, GLUTTON_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
                getResourcePath(ENERGY_ORB), getResourcePath(ATTACK_CARD_PORTRAIT),
                getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
                getResourcePath(ENERGY_ORB_PORTRAIT));
    }

    public static void initialize() {
        new GluttonMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new GluttonCharacter("The Glutton"),
                getResourcePath(CHAR_BUTTON),
                getResourcePath(CHAR_PORTRAIT),
                GluttonEnum.GLUTTON);
    }

    @Override
    public void receiveEditRelics() {
        // Starter
        addRelicToCustomPool(new EternalHunger(), AbstractCardEnum.GLUTTON);

        //Common
        addRelicToCustomPool(new AmuletOfPain(), AbstractCardEnum.GLUTTON);

        //Uncommon
        addRelicToCustomPool(new HalfEatenSandwich(), AbstractCardEnum.GLUTTON);

        //Rare
        addRelicToCustomPool(new Syringe(), AbstractCardEnum.GLUTTON);

        //Boss
        addRelicToCustomPool(new DoggyBag(), AbstractCardEnum.GLUTTON);
        addRelicToCustomPool(new Gemstone(), AbstractCardEnum.GLUTTON);
        addRelicToCustomPool(new InfiniteFamine(), AbstractCardEnum.GLUTTON); //Upgrade to Starter

        //Shop
        addRelicToCustomPool(new Lollipop(), AbstractCardEnum.GLUTTON);
    }

    @Override
    public void receiveEditCards() {
        //Status
        BaseMod.addCard(new HungerPang());

        //Basic
        BaseMod.addCard(new Defend_Glutton());
        BaseMod.addCard(new Strike_Glutton());
        BaseMod.addCard(new Flail());

        //Common
        //Attacks
        BaseMod.addCard(new Bite_Glutton());
        BaseMod.addCard(new Borborygmi());
        BaseMod.addCard(new Chomp());
        BaseMod.addCard(new Demolish());
        BaseMod.addCard(new Devour());
        BaseMod.addCard(new Feed_Glutton());
        BaseMod.addCard(new GnawingHunger());
        BaseMod.addCard(new KneeJerk());
        BaseMod.addCard(new Slam());
        BaseMod.addCard(new Tantrum());
        //Skills
        BaseMod.addCard(new Brace());
        BaseMod.addCard(new GuardStance());
        BaseMod.addCard(new PainMeditation());
        BaseMod.addCard(new Rest());
        BaseMod.addCard(new Salivate());
        BaseMod.addCard(new Scab());
        BaseMod.addCard(new SelfFlagellate());
        BaseMod.addCard(new Thrombosis());
        BaseMod.addCard(new Treat());
        BaseMod.addCard(new Yearn());

        //Uncommon
        //Attacks
        BaseMod.addCard(new BellySlam());
        BaseMod.addCard(new BloodyKnuckle());
        BaseMod.addCard(new DecrepitStrike());
        BaseMod.addCard(new DistilledAgony());
        BaseMod.addCard(new Feast());
        BaseMod.addCard(new FeebleKick());
        BaseMod.addCard(new LashOut());
        BaseMod.addCard(new Loot());
        BaseMod.addCard(new Mug());
        BaseMod.addCard(new Overreach());
        BaseMod.addCard(new Profligacy());
        BaseMod.addCard(new Regurgitate());
        BaseMod.addCard(new Strain());
        BaseMod.addCard(new Throb());
        BaseMod.addCard(new Torment());
        BaseMod.addCard(new Voracity());
        //Skills
        BaseMod.addCard(new Chrysosphagy());
        BaseMod.addCard(new Delusion());
        BaseMod.addCard(new Dispepsia());
        BaseMod.addCard(new GoldenArmor());
        BaseMod.addCard(new Migraine());
        BaseMod.addCard(new ObsessiveGreed());
        BaseMod.addCard(new ShareWeakness());
        BaseMod.addCard(new Toxicity());
        BaseMod.addCard(new Tumor());
        //Powers
        BaseMod.addCard(new FeverVisions());
        BaseMod.addCard(new Hoard());
        BaseMod.addCard(new Masochism());
        BaseMod.addCard(new Misery());
        BaseMod.addCard(new Revivification());
        BaseMod.addCard(new ToxicResidue());

        //Rare
        //Attacks
        BaseMod.addCard(new Avarice());
        BaseMod.addCard(new EatAlive());
        BaseMod.addCard(new Excrescence());
        BaseMod.addCard(new FragileMight());
        BaseMod.addCard(new Reminisce());
        BaseMod.addCard(new Retaliation());
        //Skills
        BaseMod.addCard(new Fast());
        BaseMod.addCard(new Hemophilia());
        BaseMod.addCard(new Inversion());
        BaseMod.addCard(new Malignancy());
        BaseMod.addCard(new Treasure());
        //Powers
        BaseMod.addCard(new Enfeeblement());
        BaseMod.addCard(new Megrim());
        BaseMod.addCard(new Nostalgia());
        BaseMod.addCard(new StarvationMode());
        BaseMod.addCard(new Vitality());
    }

    @Override
    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("strings/relic-strings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword("Hunger Pang", new String[]{"hunger pang", "hunger_pang", "hunger pangs", "hunger_pangs"},
                "Hunger Pangs are unplayable status cards that damage you and draw you new cards.");
        BaseMod.addKeyword(new String[]{"echo", "echoes"},
                "Echoes are copies of cards with ethereal and exhaust.");
        BaseMod.addKeyword(new String[]{"bleed"},
                "Bleeding creatures lose HP at the start of their turn.");
        BaseMod.addKeyword(new String[]{"starving"},
                "At the start of each turn, add Hunger Pangs to your discard pile.");
    }

    @Override
    public void receivePostDraw(AbstractCard c) {
        AbstractPlayer player = AbstractDungeon.player;
        //custom callback for card draw on powers
        for (AbstractPower p : player.powers) {
            if (p instanceof AbstractGluttonPower) {
                ((AbstractGluttonPower)p).onCardDraw(c);
            }
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        // Bug fix for damageReceivedThisTurn not resetting properly
        GameActionManager.damageReceivedThisTurn = 0;
    }

    public static boolean hasDebuff(AbstractCreature c) {
        for(AbstractPower power : c.powers){
            if(power.type == AbstractPower.PowerType.DEBUFF){
                return true;
            }
        }
        return false;
    }
}