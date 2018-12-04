package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.SpendGoldCombatAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Chrysosphagy extends AbstractGluttonCard
{
    private static final int PRICE = 20;

    public static final String ID = "Chrysosphagy";
    public static final String NAME = "Chrysosphagy";
    public static final String DESCRIPTION = "Pay " + PRICE + " Gold. NL Heal !M! HP.";
    public static final String CANT_PLAY = "I can't afford this card.";
    public static final String IMG_PATH = "cards/chrysosphagy.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 20;
    private static final int UPGRADE_MAGIC_BONUS = 10;

    public Chrysosphagy()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpendGoldCombatAction(PRICE));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if(p.gold < PRICE){
            this.cantUseMessage = CANT_PLAY;
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy()
    {
        return new Chrysosphagy();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
        }
    }
}



