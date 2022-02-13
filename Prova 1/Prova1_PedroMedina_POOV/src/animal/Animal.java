package animal;

public abstract class Animal {

    private String identificador;
    private String especie;
    private int idade;

    public Animal(String identificador, String especie, int idade) {
        this.identificador = identificador;
        this.especie = especie;
        this.idade = idade;
    }

    public Animal() {
        this.identificador = "sem id";
        this.especie = "sem especie";
        this.idade = 0;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "\n Identificador: " + identificador + "\n Espécie: " + especie + "\n Idade: " + idade + "\n";
    }
}
