package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class Avarice extends AbstractGluttonCard
{
    public static final int PRICE = 100;

    public static final String ID = "Avarice";
    public static final String NAME = "Avarice";
    public static final String DESCRIPTION = "Costs 1 less [R]  for every "
        + PRICE + " Gold you have. NL Deal !D! damage.";
    public static final String IMG_PATH = "cards/avarice.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 5;
    private static final int POWER = 30;
    private static final int UPGRADE_BONUS = 10;

    public Avarice()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
    }

    public void updateCostAlt(int amt) {
        int tmpCost = this.cost;
        int diff = this.cost - this.costForTurn;

        tmpCost += amt;
        if (tmpCost < 0) {
            tmpCost = 0;
        }
        if (tmpCost != this.cost)
        {
            this.cost = tmpCost;
            this.costForTurn = (this.cost - diff);
            if (this.costForTurn < 0) {
                this.costForTurn = 0;
            }
        }
    }

    public void triggerWhenDrawn() {
        if(!this.isCostModified && !this.isCostModifiedForTurn) {
            int current = this.cost;
            int desired = COST - (AbstractDungeon.player.gold / PRICE);
            this.updateCostAlt(desired - current);
        }
    }

    public void onChangeGold(int amount) {
        if(!this.isCostModified && !this.isCostModifiedForTurn) {
            int current = this.cost;
            int desired = COST - (AbstractDungeon.player.gold / PRICE);
            this.updateCostAlt(desired - current);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy()
    {
        AbstractCard tmp = new Avarice();
        if(AbstractDungeon.player != null){
            ((Avarice)tmp).updateCostAlt(-(AbstractDungeon.player.gold / PRICE));
        }
        return tmp;
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
