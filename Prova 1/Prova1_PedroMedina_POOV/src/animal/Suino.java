package animal;

public class Suino extends Animal {

    private float pesoDeCarneUtil;

    public Suino(float pesoDeCarneUtil, String identificador, String especie, int idade) {
        super(identificador, especie, idade);
        this.pesoDeCarneUtil = pesoDeCarneUtil;
    }

    public Suino() {
        super();
        this.pesoDeCarneUtil = pesoDeCarneUtil;
    }

    public float getPesoDeCarneUtil() {
        return pesoDeCarneUtil;
    }

    public void setPesoDeCarneUtil(float pesoDeCarneUtil) {
        this.pesoDeCarneUtil = pesoDeCarneUtil;
    }

    @Override
    public String toString() {
        return "\n Suíno" + super.toString() + " Peso de Carne Útil: " + pesoDeCarneUtil + "\n";
    }

}
