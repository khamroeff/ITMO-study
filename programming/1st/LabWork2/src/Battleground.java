import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Battleground {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Noibat("John Cena", 2));
        b.addAlly(new Eelektrik("Khabib", 2));
        b.addAlly(new Tynamo("Naruto", 2));
        b.addFoe(new Eelektross("McGregor", 3));
        b.addFoe(new Spinda("Sasuke", 2));
        b.addFoe(new Noivern("The Rock", 3));
        b.go();


    }
}
