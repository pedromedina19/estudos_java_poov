package pessoa;

public class Aluno extends Pessoa {

    private String RA;
    private String curso;
    private int periodo;

    public Aluno(String RA, String curso, int periodo, String cpf, String nome, int idade, String endereco, String telefone) {
        super(cpf, nome, idade, endereco, telefone);
        this.RA = RA;
        this.curso = curso;
        this.periodo = periodo;
    }

    public Aluno() {
        super();
        this.RA = "sem RA";
        this.curso = "sem curso";
        this.periodo = 0;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "\n Aluno" + super.toString() + " RA: " + RA + "\n Curso: " + curso + "\n Período: " + periodo + "\n";
    }

    @Override
    public String andarPeloCampus() {
        return ("\n Adoro andar pelo campus, entretanto estou preocupado com a prova de POOV... \n");
    }
}
