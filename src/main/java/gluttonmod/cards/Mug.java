package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class Mug extends AbstractGluttonCard
{
    public static final String ID = "Mug";
    public static final String NAME = "Mug";
    public static final String DESCRIPTION = "Deal !D! damage. NL Gain !M! Gold. Exhaust.";
    public static final String IMG_PATH = "cards/mug.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int MAGIC = 15;
    private static final int POWER = 10;
    private static final int UPGRADE_MAGIC_BONUS = 5;
    private static final int UPGRADE_POWER_BONUS = 2;

    public Mug()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
        p.gainGold(this.magicNumber);
        if(!p.hasRelic("Ectoplasm")) {
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(p, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));
            }
        }
    }

    public AbstractCard makeCopy()
    {
        return new Mug();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_POWER_BONUS);
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
        }
    }
}
