import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Noibat extends Pokemon {
    public Noibat(String name, int level){
        super(name, level);
        setStats(40, 30, 35, 45, 40, 55);
        setType(Type.FLYING, Type.DRAGON);
        setMove(new ChargeBeam(), new Psychic(), new Confide());
    }
}
