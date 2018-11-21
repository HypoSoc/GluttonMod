package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.powers.VitalityPower;

public class Vitality extends AbstractGluttonCard
{
    public static final String ID = "Vitality";
    public static final String NAME = "Vitality";
    public static final String DESCRIPTION = "Whenever you heal HP, gain [R].";
    public static final String UPGRADE_DESCRIPTION = "Innate. NL Whenever you heal HP, gain [R].";
    public static final String IMG_PATH = "cards/vitality.png";

    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;

    public Vitality()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new VitalityPower(p, 1), 1));
    }

    public AbstractCard makeCopy()
    {
        return new Vitality();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


