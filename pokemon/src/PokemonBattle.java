enum Tipo{
    FUEGO , AGUA, PLANTA
}

interface Evolucionable{
    boolean puedeEvolucionar();
}

abstract class pokemon {
    protected String nombre;
    protected int nivel;
    protected int hp;
    protected final Tipo tipo;

    //Constructor con validaciones
    //Creamos un objeto pokemon pero recordar que no lo podemos instanciar desde la clase pokemon
    public pokemon(String nombre, int nivel, int hp, Tipo tipo) {
        if (nivel< 1) throw new IllegalArgumentException("El nivel debe ser mayor a 1");
        if (hp <= 0) throw new IllegalArgumentException("El hp debe ser mayor a 0");
        this.nivel = nivel;
        this.tipo = tipo;
        this.nombre = nombre;
        this.hp = hp;
    }

    //Constructor sobrecargado
    //Otro metodo con el mismo nombre pero con distinta firma = sobrecarga
    public pokemon(String nombre, Tipo tipo){
        this(nombre , 1, 50, tipo);
    }

    //Lo que puede hacer un pokemon
    public abstract void hacerSonido();
    protected abstract int poderBase();

    //Metodo para atacar
    public void atacar(pokemon objetivo){
        int danio = calcularDanio(objetivo, 1.0); // Sin clima

        System.out.println(nombre + " ataca a " + objetivo.nombre + " y causa "+danio + " de daño.");
        objetivo.recibirDanio(danio);

    }

    //Metodo atacar con modificador del clima
    //sobrecarga del metodo atacar
    public void atacar(pokemon objetivo, String clima){
        double modificadorClima = ObtenerModificadorClima(clima);
        int danio = calcularDanio(objetivo, modificadorClima);
        System.out.println(nombre + " ataca a " + objetivo.nombre + " con clima " + clima + " y causa "+ danio + " de daño.");
        objetivo.recibirDanio(danio);
    }

    protected int calcularDanio(pokemon objetivo, double modificadorClima){
        double efectividad = obtenerEfectividad(this.tipo, objetivo.tipo);
        return (int)(poderBase() * nivel * efectividad * modificadorClima);
    }

    protected void recibirDanio(int danio){
        hp -= danio;
        if (hp < 0) hp = 0;
        System.out.println(nombre + " ahora tiene " + hp + " HP.");
    }

    private double  obtenerEfectividad(Tipo atacante, Tipo defensor){
        if((atacante == Tipo.FUEGO && defensor == Tipo.PLANTA) ||
            (atacante == Tipo.AGUA && defensor == Tipo.FUEGO) ||
            (atacante == Tipo.PLANTA && defensor == Tipo.AGUA)){
            return 1.5;
        }
        return 1.0;
    }

    private double ObtenerModificadorClima(String clima){
        switch (clima.toLowerCase()){
            case "soleado":
                if (tipo == Tipo.FUEGO) return 1.2;
                if (tipo == Tipo.AGUA) return 0.8;
                break;
            case "lluvia":
                if (tipo == Tipo.FUEGO) return 0.8;
                if (tipo == Tipo.AGUA) return 1.2;
                break;
        }
        return 1.0;
    }
}

//Clases
//Hereda de pokemon e implementa evoluciones
class Fuego extends pokemon implements Evolucionable{

    //Constructor
    public Fuego(String nombre, int nivel, int hp) {
        super(nombre, nivel, hp, Tipo.FUEGO);
    }
    //Sobrecarga
    public Fuego(String nombre){
        super(nombre, Tipo.FUEGO);
    }

    //Sobreescribimos metodos de la clase heredada
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: CHAAARRRR");
    }

    @Override
    protected int poderBase() {
        return 12;
    }

    @Override
    public boolean puedeEvolucionar() {
        return nivel < 16;
    }
}

class Agua extends pokemon implements Evolucionable{

    //Constructor
    public Agua(String nombre, int nivel, int hp) {
        super(nombre, nivel, hp, Tipo.AGUA);
    }
    //Sobrecarga
    public Agua(String nombre){
        super(nombre, Tipo.AGUA);
    }

    //Sobreescribimos metodos de la clase heredada
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: SPLASHH");
    }

    @Override
    protected int poderBase() {
        return 10;
    }

    @Override
    public boolean puedeEvolucionar() {
        return nivel < 18;
    }
}


class Planta extends pokemon implements Evolucionable{

    //Constructor
    public Planta(String nombre, int nivel, int hp) {
        super(nombre, nivel, hp, Tipo.PLANTA);
    }
    //Sobrecarga
    public Planta(String nombre){
        super(nombre, Tipo.PLANTA);
    }

    //Sobreescribimos metodos de la clase heredada
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: GREEEEEEN");
    }

    @Override
    protected int poderBase() {
        return 11;
    }

    @Override
    public boolean puedeEvolucionar() {
        return nivel < 14;
    }
}

//Main
public class PokemonBattle{

    public static void main(String[] args) {
        //Creamos los pokemon usando diferentes constructores
        //Declaramos un objeto nuevo en base a una clase
        pokemon charmander = new Fuego("charmander" , 5, 60);
        pokemon squirtle = new Agua("squirtle");
        pokemon bulbasaur = new Planta("bulbasaur", 3, 70);

        //Polimorfismo para mostrar los sonidos
        charmander.hacerSonido();
        squirtle.hacerSonido();
        bulbasaur.hacerSonido();

        //Ataques comunes
        charmander.atacar(bulbasaur);
        squirtle.atacar(charmander);
        bulbasaur.atacar(squirtle);

        //Ataques con clima
        charmander.atacar(squirtle, "soleado");
        squirtle.atacar(charmander, "lluvia");

        Evolucionable[] pokemons = {(Fuego) charmander ,  (Agua) squirtle, (Planta) bulbasaur};
        for (Evolucionable p: pokemons){
            System.out.println(((pokemon) p).nombre + " puede evolucionar " + p.puedeEvolucionar());
        }
    }
}
