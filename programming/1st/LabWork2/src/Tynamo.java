import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Tynamo extends Pokemon {
    public Tynamo(String name, int level){
        super(name, level);
        setStats(35, 55, 40, 45, 40, 60);
        setType(Type.ELECTRIC);
        setMove(new Swagger(), new ShadowBall());
    }


}
