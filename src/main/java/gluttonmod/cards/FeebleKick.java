package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gluttonmod.GluttonMod;

public class FeebleKick extends AbstractGluttonCard
{
    public static final String ID = "FeebleKick";
    public static final String NAME = "Feeble Kick";
    public static final String DESCRIPTION = "Deal !D! damage. If you have a Debuff, Gain [R] and draw 1 card.";
    public static final String IMG_PATH = "cards/feeblekick.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int POWER = 7;
    private static final int UPGRADE_BONUS = 3;
    private static final int CARD_DRAW = 1;
    private static final int ENERGY_GAIN = 1;

    public FeebleKick()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
        if(GluttonMod.hasDebuff(AbstractDungeon.player)){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, CARD_DRAW));
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(ENERGY_GAIN));
        }
    }

    public AbstractCard makeCopy()
    {
        return new FeebleKick();
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
