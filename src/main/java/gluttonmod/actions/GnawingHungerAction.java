package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.UUID;

public class GnawingHungerAction
        extends AbstractGameAction
{
    private int miscIncrease;
    private int moreDamage;
    private UUID uuid;

    public GnawingHungerAction(UUID targetUUID, int miscIncrease, int moreDamage)
    {
        this.miscIncrease = miscIncrease;

        this.moreDamage = moreDamage;

        this.uuid = targetUUID;
    }

    public void update()
    {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(this.uuid))
            {
                c.misc += this.miscIncrease;
                c.applyPowers();
                c.baseDamage = c.misc * 2 + moreDamage;
                //c.baseMagicNumber = c.misc;
            }
        }
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid))
        {
            c.misc += this.miscIncrease;
            c.applyPowers();
            c.baseDamage = c.misc * 2 + moreDamage;
            //c.baseMagicNumber = c.misc;
        }
        this.isDone = true;
    }
}
