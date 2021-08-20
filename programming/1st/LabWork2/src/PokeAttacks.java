import javafx.print.PaperSource;
import ru.ifmo.se.pokemon.*;

class Swagger extends StatusMove {
    protected Swagger(){
        super(Type.NORMAL, 0, 85);
    }

    @Override
    protected void applySelfEffects (Pokemon pokemon) {
        pokemon.setMod(Stat.ATTACK, 2);
    }

    @Override
    protected String describe() {
        return "Повышает эту атаку на 2 ступени";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.confuse(pokemon);
    }
}

class ShadowBall extends SpecialMove {
    protected ShadowBall(){
        super(Type.GHOST, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() <= 0.2) pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);
    }
}

class MudBomb extends SpecialMove {
    protected MudBomb(){
        super(Type.GROUND, 65, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() <= 0.3) pokemon.setMod(Stat.ACCURACY, -1);
    }
}

class IceFang extends PhysicalMove {
    protected IceFang(){
        super(Type.ICE, 65,95);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() <= 0.2) Effect.flinch(pokemon);
        if(Math.random() <= 0.2) Effect.freeze(pokemon);
    }
}

class ChargeBeam extends SpecialMove {
    protected ChargeBeam(){
        super(Type.ELECTRIC, 50, 90);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        if(Math.random() <= 0.7) pokemon.setMod(Stat.SPECIAL_ATTACK, 1);

    }
}

class Psychic extends SpecialMove{
    protected Psychic(){
        super(Type.PSYCHIC, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() <= 0.1) pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);
    }
}

class Confide extends StatusMove {
    protected Confide(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.SPECIAL_ATTACK, -1);
    }
    @Override
    protected String describe(){
        return "Снижает спец. атаку цели на один пункт";
    }
}

class DarkPulse extends SpecialMove{
    protected DarkPulse(){
        super(Type.DARK, 80, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 30) Effect.flinch(pokemon);
    }
}

class WorkUp extends StatusMove {
    protected WorkUp(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applySelfEffects(Pokemon pokemon){
        pokemon.setMod(Stat.ATTACK, 1);
        pokemon.setMod(Stat.SPECIAL_ATTACK, 1);
    }
    @Override
    protected String describe(){
        return "Повышает атаку и спец. атаку использующего на одну ступень каждую";
    }
}

class ZenHeadbutt extends PhysicalMove{
    protected ZenHeadbutt(){
        super(Type.PSYCHIC, 80, 90);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon){
        if (Math.random() <= 0.2) Effect.flinch(pokemon);
    }
    @Override
    protected String describe(){
        return "имеет 20% вероятность заставить цель дрогнуть";
    }
}

class Extrasensory extends SpecialMove{
    protected Extrasensory(){
        super(Type.PSYCHIC, 80, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon){
        if (Math.random() <= 0.1) Effect.flinch(pokemon);
    }

}



