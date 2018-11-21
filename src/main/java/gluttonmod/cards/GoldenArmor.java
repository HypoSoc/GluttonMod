package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GoldenArmor extends AbstractGluttonCard {
    public static final String ID = "GoldenArmor";
    public static final String NAME = "Golden Armor";
    public static final String DESCRIPTION = "Gain 1 Block for every !M! gold you have.";
    public static final String EXTENDED_DESCRIPTION = " NL (Gain !B! block.)";
    public static final String IMG_PATH = "cards/goldenarmor.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;
    private static final int BLOCK = 0;
    private static final int MAGIC = 15;
    private static final int UPGRADE_MAGIC = -3;

    public GoldenArmor()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public AbstractCard makeCopy()
    {
        return new GoldenArmor();
    }

    public void applyPowers()
    {
        this.baseBlock = AbstractDungeon.player.gold / this.magicNumber;
        super.applyPowers();
        this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION);
        initializeDescription();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            this.baseBlock = AbstractDungeon.player.gold / this.magicNumber;
        }
    }
}

