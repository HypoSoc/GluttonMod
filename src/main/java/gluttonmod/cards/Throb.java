package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Throb extends AbstractGluttonCard
{
    public static final int HP_LOSS_CHECK = 3;

    public static final String ID = "Throb";
    public static final String NAME = "Throb";
    public static final String DESCRIPTION = "Deal !D! damage. If you have lost at least "
            + HP_LOSS_CHECK + " HP this turn, Gain !B! Block.";
    public static final String IMG_PATH = "cards/throb.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int POWER = 7;
    private static final int BLOCK = 7;
    private static final int UPGRADE_BONUS = 3;

    public Throb()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
        if(GameActionManager.damageReceivedThisTurn >= HP_LOSS_CHECK){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Throb();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
            upgradeBlock(UPGRADE_BONUS);
        }
    }
}
