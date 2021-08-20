import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Eelektrik extends Pokemon {
    public Eelektrik(String name, int level){
        super(name, level);
        setStats(65, 85, 70, 75, 70, 40);
        setType(Type.ELECTRIC);
        setMove(new Swagger(), new ShadowBall(), new MudBomb());
    }

}
