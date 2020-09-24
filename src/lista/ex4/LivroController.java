package lista.ex4;

import lista.ex3.AlunoController;

public class LivroController {

    public Livro header;

    public static void main(String[] args) {
        LivroController n = new LivroController();
        n.addStart(new Livro("wesley", 5));
        n.addStart(new Livro("samm", 5));
        n.addStart(new Livro("Paula", 5));
        n.addStart(new Livro("Lari", 5));
        n.addStart(new Livro("Sasa", 5));
        n.addEndRecursiva(new Livro("gabi", 5));
        n.addEndRecursiva(new Livro("welli", 5));
        n.addEndRecursiva(new Livro("Nfew", 5));
        n.addEndRecursiva(new Livro("Andi", 5));
        n.addEndRecursiva(new Livro("Mathia", 5));
        n.addPosRecursiva(new Livro("Pettecosteu", 5), 0);
        n.addPosRecursiva(new Livro("Pettecosteu", 5), 1);
        n.addPosRecursiva(new Livro("Pettecosteu", 5), 2);
        n.addPosRecursiva(new Livro("Pettecosteu", 5), 3);
        n.addPosRecursiva(new Livro("Pettecosteu", 5), 4);
        n.rmEndRecursiva();
        n.rmStart();
        n.rmPosRecursiva(7);
        n.rmEndRecursiva();
        n.addEndRecursiva(new Livro("aaaqqe", 5));
        //n.print();
        n.printRecursivo();
        System.out.println(n.buscaSequencialRecursiva("gabi").titulo);
    }

    public Livro buscaSequencialRecursiva(String titulo) {//chama o metodo recursivo com atributos providos
        return buscaSequencialRecursiva(header, titulo);
    }

    public Livro buscaSequencialRecursiva(Livro livro, String titulo) { //método de busca sequencial que retorna caso o titulo seja encontrado
        if(livro.titulo.equals(titulo)) return livro;
        else if(livro.next==header) throw new RuntimeException("Mensagem avisando");
        else{
            return buscaSequencialRecursiva(livro.next, titulo);
        }
    }

    public void rmPos(int pos) {
        if (pos >= length()) {
            System.out.println("null");
        } else {
            Livro aux = header;
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

    public void rmPosRecursiva(int pos) { //chama o metodo recursivo com atributos providos
        rmPosRecursiva(pos, 0, header);
    }

    public void rmPosRecursiva(int pos, int i, Livro aux) { //metodo procura posicao de indexação parcial e o retira da listagem encadeada
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
            Livro aux = header.next;
            aux.previous = header.previous;
            header.previous.next = aux;
            header = aux;
        }
    }

    public void rmEnd() {
        if (isListNull()) {
            System.out.println("null");
        } else {
            Livro aux = header;
            while (aux.next != header) {
                aux = aux.next;
            }
            aux.previous.next = header;
            header.previous = aux.previous;
        }
    }

    public void rmEndRecursiva() { //chama o metodo recursivo com atributos providos
        rmEndRecursiva(header);
    }

    public void rmEndRecursiva(Livro aux) { //chama até o final e remove, caso entre em uma excessao logica aplica reset de matriza~~ao
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

    public void addPos(Livro newAluno, int pos) {
        if (pos >= length()) {
            System.out.println("null");
        } else {
            Livro aux = header;
            for (int i = 0; i < length(); i++, aux = aux.next) {
                if (pos == i) {
                    aux.qtd = newAluno.qtd;
                    aux.titulo = newAluno.titulo;
                    break;
                }
            }
        }
    }

    public void addPosRecursiva(Livro aluno, int pos) { //chama o metodo recursivo com atributos providos
        addPosRecursiva(aluno, header, pos, 0);
    }

    public void addPosRecursiva(Livro aluno, Livro aux, int pos, int i) { // procura posicao de index proposta e substitui os dados para não alterar endereço de memoria
        if (pos == i) {
            aux.qtd = aluno.qtd;
            aux.titulo = aluno.titulo;
        } else {
            addPosRecursiva(aluno, aux.next, pos, i + 1);
        }
    }

    public void addStart(Livro newAluno) {
        if (isListNull()) {
            newAluno.next = newAluno;
            newAluno.previous = newAluno;
            header = newAluno;
        } else {
            Livro aux = header;
            newAluno.next = aux;
            newAluno.previous = aux.previous;
            aux.previous = newAluno;
            newAluno.previous.next = newAluno;
            header = newAluno;
        }
    }

    public void addEnd(Livro newAluno) {
        if (isListNull()) {
            newAluno.next = newAluno;
            newAluno.previous = newAluno;
            header = newAluno;
        } else {
            Livro aux = header;
            while (aux.next != header) {
                aux = aux.next;
            }
            newAluno.next = header;
            newAluno.previous = aux;
            aux.next = newAluno;
            header.previous = newAluno;
        }
    }

    public void addEndRecursiva(Livro aluno) { //chama o metodo recursivo com atributos providos
        addEndRecursiva(aluno, header);
    }

    public void addEndRecursiva(Livro aluno, Livro aux) { //repete o metodo recursivo ate o final da lista e quando encontrado é removido da sequencia
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
        Livro aux = header;
        while (aux.next != header) {
            System.out.println(aux.titulo);
            aux = aux.next;
        }
        System.out.println(aux.titulo);
        System.out.println("=================");
    }

    public void printRecursivo() { //chama o metodo recursivo com atributos providos
        printRecursivo(header);
        System.out.println("==================");
    }

    public void printRecursivo(Livro aluno) { //enquanto o aluno for anterior ao heaer, é apresentado
        System.out.println(aluno.titulo);
        if (aluno.next != header) printRecursivo(aluno.next);
    }

    public int length() {
        Livro aux = header;
        if (header == null) return 0;
        int c = 1;
        while (aux.next != header) {
            c++;
            aux = aux.next;
        }
        return c;
    }

    public int lengthRecursivo() { //chama o metodo recursivo com atributos providos
        return lengthRecursivo(header, 0);
    }

    public int lengthRecursivo(Livro aluno, int i) { //enquanto o next não for o header, retorna a recursão
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

    private static class Livro {
        private String titulo;
        public int qtd;
        private Livro next, previous;

        public Livro(String titulo, int qtd) {
            this.titulo = titulo;
            this.qtd = qtd;
        }
    }
}
