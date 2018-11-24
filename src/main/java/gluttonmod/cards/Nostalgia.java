package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.powers.MegrimPower;
import gluttonmod.powers.NostalgiaPower;

public class Nostalgia extends AbstractGluttonCard
{
    public static final String ID = "Nostalgia";
    public static final String NAME = "Nostalgia";
    public static final String DESCRIPTION = "Whenever you Exhaust a non-Ethereal Skill or Attack, create an Echo of it.";
    public static final String IMG_PATH = "cards/nostalgia.png";

    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public Nostalgia()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new NostalgiaPower(p, 1), 1));
    }

    public AbstractCard makeCopy()
    {
        return new Nostalgia();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}

