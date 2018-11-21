package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.SpendGoldCombatAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class ObsessiveGreed extends AbstractGluttonCard
{
    private static final int PRICE = 30;
    private static final int UPGRADED_PRICE = 50;

    public static final String ID = "ObsessiveGreed";
    public static final String NAME = "Obsessive Greed";
    public static final String DESCRIPTION = "Pay " + PRICE + " Gold. NL Gain !M! Intangible.";
    public static final String UPGRADED_DESCRIPTION = "Pay " + UPGRADED_PRICE + " Gold. NL Gain !M! Intangible.";
    public static final String CANT_PLAY = "I can't afford this card.";
    public static final String IMG_PATH = "cards/obsessivegreed.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_BONUS = 1;


    public ObsessiveGreed()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int price = PRICE;
        if(this.upgraded)
            price = UPGRADED_PRICE;
        AbstractDungeon.actionManager.addToBottom(new SpendGoldCombatAction(price));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        int price = PRICE;
        if(this.upgraded)
            price = UPGRADED_PRICE;
        if(p.gold < price){
            this.cantUseMessage = CANT_PLAY;
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy()
    {
        return new ObsessiveGreed();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }
}



