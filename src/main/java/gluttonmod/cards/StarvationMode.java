package gluttonmod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.powers.StarvationModePower;
import gluttonmod.powers.StarvingPower;

public class StarvationMode extends AbstractGluttonCard
{
    public static final String ID = "StarvationMode";
    public static final String NAME = "Starvation Mode";
    public static final String DESCRIPTION = "Gain Starving. NL Whenever you draw a status or curse, apply !M! Poison to ALL enemies.";
    public static final String IMG_PATH = "cards/starvationmode.png";

    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 3;
    private static final int MAGIC = 4;
    private static final int UPGRADE_BONUS = 2;

    public StarvationMode()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(BaseModCardTags.FORM);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StarvingPower(p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StarvationModePower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new StarvationMode();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}

