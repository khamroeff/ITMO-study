import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Eelektross extends Pokemon {
    public Eelektross(String name, int level) {
        super(name, level);
        setStats(85, 115, 80, 105, 80, 50);
        setType(Type.ELECTRIC);
        setMove(new Swagger(), new ShadowBall(), new MudBomb(), new IceFang());

    }
}
