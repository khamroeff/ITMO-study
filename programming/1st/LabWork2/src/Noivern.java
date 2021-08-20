import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Noivern extends Pokemon {
    public Noivern(String name, int level){
        super(name, level);
        setStats(85, 70, 80, 97, 80, 123);
        setType(Type.FLYING, Type.DRAGON);
        setMove(new ChargeBeam(), new Psychic(), new Confide(), new DarkPulse());
    }
}
