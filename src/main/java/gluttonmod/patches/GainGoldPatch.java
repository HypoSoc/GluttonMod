package gluttonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import gluttonmod.cards.AbstractGluttonCard;

@SpirePatch(clz = AbstractPlayer.class, method = "gainGold")
public class GainGoldPatch {
    public static SpireReturn Prefix(AbstractPlayer abstractPlayer, int amount){
        if(abstractPlayer.hasRelic("Glutton:LuckySock") && !abstractPlayer.hasRelic("Ectoplasm")
            && (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)){
            amount += (amount / 4);
            AbstractRelic sock = abstractPlayer.getRelic("Glutton:LuckySock");
            sock.flash();
            for (int i = 1; i < amount/4; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(abstractPlayer, sock.hb.cX, sock.hb.cY, abstractPlayer.hb.cX, abstractPlayer.hb.cY, true));
            }
            if(amount > 0){
                CardCrawlGame.goldGained += amount;
                abstractPlayer.gold += amount;
                for (AbstractRelic r : abstractPlayer.relics) {
                    r.onGainGold();
                }
                applyToCardsInGroup(abstractPlayer.drawPile, amount);
                applyToCardsInGroup(abstractPlayer.discardPile, amount);
                applyToCardsInGroup(abstractPlayer.hand, amount);
                applyToCardsInGroup(abstractPlayer.exhaustPile, amount);
            }
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    public static void Postfix(AbstractPlayer abstractPlayer, int amount) {
        if(amount > 0) {
            applyToCardsInGroup(abstractPlayer.drawPile, amount);
            applyToCardsInGroup(abstractPlayer.discardPile, amount);
            applyToCardsInGroup(abstractPlayer.hand, amount);
            applyToCardsInGroup(abstractPlayer.exhaustPile, amount);
        }
    }

    private static void applyToCardsInGroup(CardGroup cardGroup, int amount){
        for (AbstractCard card : cardGroup.group) {
            if (card instanceof AbstractGluttonCard) {
                AbstractGluttonCard goldenCard = (AbstractGluttonCard) card;
                goldenCard.onChangeGold(amount);
            }
        }
    }
}
