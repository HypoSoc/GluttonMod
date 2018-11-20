package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import gluttonmod.GluttonMod;

public class BleedPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Bleed";
    public static final String NAME = "Bleed";
    public static final String[] DESCRIPTIONS = {
            "At the start of your turn, lose #b",
            " HP.",
            "At the start of its turn, loses #b"
            };
    public static final String IMG = "powers/bleed.png";
    private AbstractCreature source;

    public BleedPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        this.updateDescription();
    }

    public void updateDescription()
    {
        if ((this.owner == null) || (this.owner.isPlayer)) {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        } else {
            this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1]);
        }
    }

    @Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
            {
                flashWithoutSound();
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }
}
