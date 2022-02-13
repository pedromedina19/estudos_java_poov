package pessoa;

public class Administrativo extends Pessoa {

    private String matricula;
    private double salario;
    private String setor;
    public int visitado;

    public Administrativo(String matricula, double salario, String setor, String cpf, String nome, int idade, String endereco, String telefone, int visitado) {
        super(cpf, nome, idade, endereco, telefone);
        this.matricula = matricula;
        this.salario = salario;
        this.setor = setor;
        this.visitado = visitado;
    }

    public Administrativo() {
        super();
        this.matricula = "sem matricula";
        this.salario = 0;
        this.setor = "sem setor";
        this.visitado = 0;
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

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "\n Administrativo" + super.toString() + " Matrícula: " + matricula + "\n Salário: " + salario + "\n Setor: " + setor + "\n";
    }
}
