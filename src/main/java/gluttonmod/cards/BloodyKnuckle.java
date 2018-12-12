package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class BloodyKnuckle extends AbstractGluttonCard
{
    public static final String ID = "BloodyKnuckle";
    public static final String NAME = "Bloody Knuckle";
    public static final String DESCRIPTION = "Give an enemy 1 Thorns. Deal !D! damage !M! times.";
    public static final String IMG_PATH = "cards/bloodyknuckle.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int POWER = 6;
    private static final int UPGRADE_BONUS = 2;

    public BloodyKnuckle()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new ThornsPower(m, 1), 1));
        for(int i=0; i<this.magicNumber-1; i++){
            AbstractDungeon.actionManager.addToBottom(
                    new PummelDamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public AbstractCard makeCopy()
    {
        return new BloodyKnuckle();
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
