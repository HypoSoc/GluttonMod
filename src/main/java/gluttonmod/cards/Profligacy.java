package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.SpendGoldCombatAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Profligacy extends AbstractGluttonCard
{
    private static final int PRICE = 10;

    public static final String ID = "Profligacy";
    public static final String NAME = "Profligacy";
    public static final String DESCRIPTION = "Pay " + PRICE + " Gold. NL Deal !D! damage. NL Gain !B! block.";
    public static final String CANT_PLAY = "I can't afford this card.";
    public static final String IMG_PATH = "cards/profligacy.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int POWER = 20;
    private static final int BLOCK = 8;
    private static final int UPGRADE_POWER_BONUS = 8;
    private static final int UPGRADE_BLOCK_BONUS = 3;

    public Profligacy()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new SpendGoldCombatAction(PRICE));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SLASH_HEAVY));
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
        return new Profligacy();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_POWER_BONUS);
            upgradeBlock(UPGRADE_BLOCK_BONUS);
        }
    }
}
