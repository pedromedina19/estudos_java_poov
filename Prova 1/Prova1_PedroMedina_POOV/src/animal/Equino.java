package animal;

public class Equino extends Animal {

    private float velocidadeDeMarcha;

    public Equino(float velocidadeDeMarcha, String identificador, String especie, int idade) {
        super(identificador, especie, idade);
        this.velocidadeDeMarcha = velocidadeDeMarcha;
    }

    public Equino() {
        super();
        this.velocidadeDeMarcha = 0;
    }

    public float getVelocidadeDeMarcha() {
        return velocidadeDeMarcha;
    }

    public void setVelocidadeDeMarcha(float velocidadeDeMarcha) {
        this.velocidadeDeMarcha = velocidadeDeMarcha;
    }

    @Override
    public String toString() {
        return "\n Equino" + super.toString() + " Velocidade de Marcha: " + velocidadeDeMarcha + "\n";
    }
}
