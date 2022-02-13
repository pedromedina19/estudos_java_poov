package pessoa;

public class Professor extends Pessoa {

    private String matricula;
    private double salario;

    public Professor(String matricula, double salario, String cpf, String nome, int idade, String endereco, String telefone) {
        super(cpf, nome, idade, endereco, telefone);
        this.matricula = matricula;
        this.salario = salario;
    }

    public Professor() {
        super();
        this.matricula = "sem matricula";
        this.salario = 0;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "\n Professor" + super.toString() + " Matrícula: " + matricula + "\n Salário: " + salario + "\n";
    }
}
