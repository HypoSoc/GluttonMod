package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import gluttonmod.GluttonMod;

public class HoardPower extends AbstractGluttonPower {
    public static final String POWER_ID = "Glutton:Hoard";
    public static final String NAME = "Hoard";
    public static final String[] DESCRIPTIONS = {"Gain #b", " Gold in #b",
            " turn.", " turns."};
    public static final String IMG = "powers/hoard.png";

    private int gold;
    private static int hoardIdOffset = 0;

    public HoardPower(AbstractCreature owner, int amount, int gold) {
        this.name = NAME;
        this.ID = (POWER_ID + hoardIdOffset);
        hoardIdOffset += 1;
        this.owner = owner;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.gold = gold;
        this.updateDescription();

        this.isTurnBased = true;
    }

    @Override
    public void updateDescription() {
        if(amount == 1) {
            description = DESCRIPTIONS[0] + this.gold + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
        else{
            description = DESCRIPTIONS[0] + this.gold + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        GluttonMod.logger.debug(this.amount);
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    @Override
    public void onRemove() {
        AbstractPlayer p = AbstractDungeon.player;
        p.gainGold(this.gold);
        if(!p.hasRelic("Ectoplasm")) {
            for (int i = 0; i < this.gold; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(p, 0, 0, p.hb.cX, p.hb.cY, true));
            }
        }
    }
}
