package lista.ex3;

public class AlunoController {
    public Aluno header;

    public static void main(String[] args) {
        AlunoController n = new AlunoController();
        n.addStart(new Aluno("wesley"));
        n.addStart(new Aluno("samm"));
        n.addStart(new Aluno("Paula"));
        n.addStart(new Aluno("Lari"));
        n.addStart(new Aluno("Sasa"));
        n.addEnd(new Aluno("gabi"));
        n.addEnd(new Aluno("welli"));
        n.addEnd(new Aluno("Nfew"));
        n.addEnd(new Aluno("Andi"));
        n.addEnd(new Aluno("Mathia"));
        n.addPos(new Aluno("Pettecosteu"), 0);
        n.addPos(new Aluno("Pettecosteu"), 1);
        n.addPos(new Aluno("Pettecosteu"), 2);
        n.addPos(new Aluno("Pettecosteu"), 3);
        n.addPos(new Aluno("Pettecosteu"), 4);
        n.rmEnd();
        n.rmStart();
        n.rmPos(7);
        n.rmEndRecursiva();
        n.addEndRecursiva(new Aluno("aaaqqe"));
        //n.print();
        n.printRecursivo();
    }

    public void rmPos(int pos) {
        if (pos >= length()) {
            System.out.println("null");
        } else {
            Aluno aux = header;
            for (int i = 0; i < length(); i++, aux = aux.next) {
                if (pos == i) {
                    aux.next.previous = aux.previous;
                    aux.previous.next = aux.next;
                    if (pos == 0) header = aux.next;
                    break;
                }
            }
        }
    }

    public void rmPosRecursiva(int pos) { //chama o metodo recursivo com atributos estaticos
        rmPosRecursiva(pos, 0, header);
    }

    public void rmPosRecursiva(int pos, int i, Aluno aux) { //metodo procura posicao de indexação parcial e o retira da listagem encadeada
        if (pos == i) {
            aux.next.previous = aux.previous;
            aux.previous.next = aux.next;
            if (pos == 0) {
                header = aux.next;
            }
        } else {
            rmPosRecursiva(pos, i + 1, aux.next);
        }
    }

    public void rmStart() {
        if (isListNull()) {
            System.out.println("null");
        } else {
            Aluno aux = header.next;
            aux.previous = header.previous;
            header.previous.next = aux;
            header = aux;
        }
    }

    public void rmEnd() {
        if (isListNull()) {
            System.out.println("null");
        } else {
            Aluno aux = header;
            while (aux.next != header) {
                aux = aux.next;
            }
            aux.previous.next = header;
            header.previous = aux.previous;
        }
    }

    public void rmEndRecursiva() { //chama o metodo recursivo com atributos estaticos
        rmEndRecursiva(header);
    }

    public void rmEndRecursiva(Aluno aux) { //chama até o final e remove, caso entre em uma excessao logica aplica reset de matriza~~ao
        if (aux.next == header) {
            if (length() == 1) header = null;
            else if (length() == 2) {
                aux.next = aux;
                aux.previous = aux;
                header = aux;
            } else {
                aux.previous.next = header;
                header.previous = aux.previous;
            }
        } else {
            rmEndRecursiva(aux.next);
        }
    }

    public void addPos(Aluno newAluno, int pos) {
        if (pos >= length()) {
            System.out.println("null");
        } else {
            Aluno aux = header;
            for (int i = 0; i < length(); i++, aux = aux.next) {
                if (pos == i) {
                    aux.setNome(newAluno.getNome());
                    aux.setRA(newAluno.getRA());
                    aux.setSemestre(newAluno.getSemestre());
                    aux.setTurma(newAluno.getTurma());
                    break;
                }
            }
        }
    }

    public void addPosRecursiva(Aluno aluno, int pos) { //chama o metodo recursivo com atributos estaticos
        addPosRecursiva(aluno, header, pos, 0);
    }

    public void addPosRecursiva(Aluno aluno, Aluno aux, int pos, int i) { // procura posicao de index proposta e substitui os dados para não alterar endereço de memoria
        if (pos == i) {
            aux.setNome(aluno.getNome());
            aux.setRA(aluno.getRA());
            aux.setSemestre(aluno.getSemestre());
            aux.setTurma(aluno.getTurma());
        } else {
            addPosRecursiva(aluno, aux.next, pos, i + 1);
        }
    }

    public void addStart(Aluno newAluno) {
        if (isListNull()) {
            newAluno.next = newAluno;
            newAluno.previous = newAluno;
            header = newAluno;
        } else {
            Aluno aux = header;
            newAluno.next = aux;
            newAluno.previous = aux.previous;
            aux.previous = newAluno;
            newAluno.previous.next = newAluno;
            header = newAluno;
        }
    }

    public void addEnd(Aluno newAluno) {
        if (isListNull()) {
            newAluno.next = newAluno;
            newAluno.previous = newAluno;
            header = newAluno;
        } else {
            Aluno aux = header;
            while (aux.next != header) {
                aux = aux.next;
            }
            newAluno.next = header;
            newAluno.previous = aux;
            aux.next = newAluno;
            header.previous = newAluno;
        }
    }

    public void addEndRecursiva(Aluno aluno) { //chama o metodo recursivo com atributos estaticos
        addEndRecursiva(aluno, header);
    }

    public void addEndRecursiva(Aluno aluno, Aluno aux) { //repete o metodo recursivo ate o final da lista e quando encontrado é removido da sequencia
        if (aux.next == header) {
            aluno.next = header;
            aluno.previous = aux;
            aux.next = aluno;
            header.previous = aluno;
        } else {
            addEndRecursiva(aluno, aux.next);
        }
    }

    public void print() {
        Aluno aux = header;
        while (aux.next != header) {
            System.out.println(aux.getNome());
            aux = aux.next;
        }
        System.out.println(aux.getNome());
        System.out.println("=================");
    }

    public void printRecursivo() { //chama o metodo recursivo com atributos estaticos
        printRecursivo(header);
        System.out.println("==================");
    }

    public void printRecursivo(Aluno aluno) { //enquanto o aluno for anterior ao heaer, é apresentado
        System.out.println(aluno.getNome());
        if (aluno.next != header) printRecursivo(aluno.next);
    }

    public int length() {
        Aluno aux = header;
        if (header == null) return 0;
        int c = 1;
        while (aux.next != header) {
            c++;
            aux = aux.next;
        }
        return c;
    }

    public int lengthRecursivo() { //chama o metodo recursivo com atributos estaticos
        return lengthRecursivo(header, 0);
    }

    public int lengthRecursivo(Aluno aluno, int i) { //enquanto o next não for o header, retorna a recursão
        if (aluno.next == header) {
            return isListNull() ? 0 : i;
        } else {
            return lengthRecursivo(aluno.next, i + 1);
        }
    }

    //toda recursividade nessa classe é direta
    
    public boolean isListNull() {
        return header == null;
    }

    private static class Aluno {
        private int RA;
        private String nome, turma, semestre;
        private Aluno next, previous;

        public Aluno(String nome) {
            setNome(nome);
        }

        public int getRA() {
            return RA;
        }

        public void setRA(int RA) {
            this.RA = RA;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTurma() {
            return turma;
        }

        public void setTurma(String turma) {
            this.turma = turma;
        }

        public String getSemestre() {
            return semestre;
        }

        public void setSemestre(String semestre) {
            this.semestre = semestre;
        }
    }
}
