import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Spinda extends Pokemon {
    public Spinda(String name, int level){
        super(name, level);
        setStats(60, 60, 60, 60, 60, 60);
        setType(Type.NORMAL);
        setMove(new ShadowBall(), new WorkUp(), new ZenHeadbutt(), new Extrasensory());

    }
}
