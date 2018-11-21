package gluttonmod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class Malignancy extends AbstractGluttonCard
{
    public static final String ID = "Malignancy";
    public static final String NAME = "Malignancy";
    public static final String DESCRIPTION = "Unplayable. NL If this card is Exhausted, gain !M! Intangible.";
    public static final String UPGRADE_DESCRIPTION = "Unplayable. NL Retain. NL If this card is Exhausted, gain !M! Intangible.";
    public static final String CANT_PLAY = "I can't play this card.";
    public static final String IMG_PATH = "cards/malignancy.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    private static final int COST = -2;
    private static final int MAGIC = 2;

    public Malignancy()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        this.cantUseMessage = CANT_PLAY;
        return false;
    }

    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new IntangiblePlayerPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new Malignancy();
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


