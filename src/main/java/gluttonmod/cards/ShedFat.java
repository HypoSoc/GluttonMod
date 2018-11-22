package gluttonmod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.actions.ShedFatAction;

public class ShedFat extends AbstractGluttonCard {
    public static final String ID = "ShedFat";
    public static final String NAME = "Shed Fat";
    public static final String DESCRIPTION = "Exhaust your hand. Draw that many cards plus !M!. Exhaust.";
    public static final String UPGRADE_DESCRIPTION = "Retain. Exhaust your hand. Draw that many cards plus !M!. Exhaust.";
    public static final String IMG_PATH = "cards/shedfat.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int MAGIC = 1;

    public ShedFat()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ShedFatAction(this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new ShedFat();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            AlwaysRetainField.alwaysRetain.set(this, true);
            this.retain = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}