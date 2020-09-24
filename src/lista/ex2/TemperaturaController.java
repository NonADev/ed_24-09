package lista.ex2;

public class TemperaturaController {
    private Temperatura head = null;

    public boolean isEmpty() {
        return head == null;
    }

    public void addStart(Temperatura temperatura) {
        if (isEmpty()) {
            head = temperatura;
        } else {
            try {
                temperatura.proximo = head;
                head = temperatura;
            } catch (Exception e) {
                throw new RuntimeException("mensagem avisando");
            }
        }
    }

    public void addPos(Temperatura temperatura, int pos) {
        if (pos >= length() || pos < 0) {
            throw new RuntimeException("mensagem avisando");
        } else {
            Temperatura aux = head;
            for (int i = 0; i < length(); i++, aux = aux.proximo) {
                if (i == pos) {
                    aux.temperatura = temperatura.temperatura;
                    return;
                }
            }
        }
    }

    public void addPosRecursiva(Temperatura temperatura, int pos) { // chama metodo recursivo com atributos estaticos
        if (isEmpty() || pos >= length()) throw new RuntimeException("mensagem avisando");
        addPosRecursiva(temperatura, head, pos, 0);
    }

    public void addPosRecursiva(Temperatura temperatura, Temperatura aux, int pos, int i) { // recursivamente se chamando até a posição requerida pos
        if (i == pos) {
            aux.temperatura = temperatura.temperatura;
        } else {
            addPosRecursiva(temperatura, aux.proximo, pos, i + 1);
        }
    }

    public void addEnd(Temperatura temperatura) {
        if (isEmpty()) {
            head = temperatura;
        } else {
            try {
                Temperatura aux = head;
                while (aux.proximo != null) {
                    aux = aux.proximo;
                }
                aux.proximo = temperatura;
            } catch (Exception e) {
                throw new RuntimeException("mensagem avisando");
            }
        }
    }

    public void addEndRecursiva(Temperatura temperatura) {// chama metodo recursivo com atributos estaticos
        if (isEmpty()) throw new RuntimeException("mensagem avisando");
        addEndRecursiva(temperatura, head);
    }

    public void addEndRecursiva(Temperatura temperatura, Temperatura aux) {// se chama até o final, que é null aparecer
        if (aux.proximo == null) {
            aux.proximo = temperatura;
        } else {
            addEndRecursiva(temperatura, aux.proximo);
        }
    }

    public int rmStart() {
        if (isEmpty()) throw new RuntimeException("mensagem avisando");
        else {
            int aux = head.temperatura;
            head = head.proximo;
            return aux;
        }
    }

    public int rmEnd() {
        if (isEmpty()) throw new RuntimeException("mensagem avisando");
        else if (length() == 1) {
            int r = head.temperatura;
            head = null;
            return r;
        } else {
            Temperatura aux = head;
            while (aux.proximo.proximo != null) {
                aux = aux.proximo;
            }
            int returnValue = aux.proximo.temperatura;
            aux.proximo = null;
            return returnValue;
        }
    }

    public int rmEndRecursiva() {// chama metodo recursivo com atributos estaticos
        if (isEmpty()) throw new RuntimeException("mensagem avisando");
        return rmEndRecursiva(head);
    }

    public int rmEndRecursiva(Temperatura aux) {//se chama até o final que é null aparecer ou suas exeções aparecerem
        int val = 0;
        if (length() == 1 || aux.proximo == null) {
            val = head.temperatura;
            head = null;
        } else if (aux.proximo.proximo == null) {
            val = aux.proximo.temperatura;
            aux.proximo = null;
        } else {
            val = rmEndRecursiva(aux.proximo);
        }
        return val;
    }

    public int rmPos(int pos) {
        int r = 0;
        if (pos >= length() || pos < 0) {
            throw new RuntimeException("mensagem avisando");
        } else if (pos == 0) rmStart();
        else if (pos == length() - 1) rmEnd();
        else {
            Temperatura aux = head;
            for (int i = 0; i < length(); i++, aux = aux.proximo) {
                if (i == pos - 1) {
                    r = aux.proximo.temperatura;
                    aux.proximo = aux.proximo.proximo;
                    break;
                }
            }
        }
        return r;
    }

    public int rmPosRecursiva(int pos) {// chama metodo recursivo com atributos estaticos
        if (pos >= length() || isEmpty()) throw new RuntimeException("mensagem avisando");
        else if (pos == 0) return rmStart();
        else return rmPosRecursiva(head, pos, 0);
    }

    public int rmPosRecursiva(Temperatura aux, int pos, int i) {// se chama até as exeções ou a posicao requerida
        int val = 0;
        if (i == pos - 1) {
            val = aux.proximo.temperatura;
            aux.proximo = aux.proximo.proximo;
        } else {
            val = rmPosRecursiva(aux.proximo, pos, i++);
        }
        return val;
    }

    public int length() {
        if (isEmpty()) return 0;
        Temperatura aux = head;
        int counter = 0;
        while (aux != null) {
            counter++;
            aux = aux.proximo;
        }
        return counter;
    }
    // todas recursoes nessa atividade são diretas
    public void print() {
        Temperatura aux = head;

        while (aux != null) {
            System.out.println(aux.temperatura);
            aux = aux.proximo;
        }
        System.out.println("==========");
    }

    public static void main(String[] args) {
        TemperaturaController t = new TemperaturaController();
        t.addStart(new Temperatura(3));
        t.print();
        t.addStart(new Temperatura(7));
        t.print();
        t.addStart(new Temperatura(14));
        t.print();
        t.addStart(new Temperatura(2));
        t.print();
        t.addEnd(new Temperatura(5));
        t.print();
        t.addPos(new Temperatura(1), 4);
        t.print();
        t.rmStart();
        t.print();
        t.rmEnd();
        t.print();
        t.rmEnd();
        t.print();
        t.rmPos(0);
        t.print();
        t.addEndRecursiva(new Temperatura(17));
        t.print();
        t.addPosRecursiva(new Temperatura(34), 1);
        t.print();
        t.rmEndRecursiva();
        t.print();
        t.rmEndRecursiva();
        t.print();
        t.addStart(new Temperatura(3));
        t.print();
        t.addStart(new Temperatura(7));
        t.print();
        t.addStart(new Temperatura(14));
        t.print();
        t.addStart(new Temperatura(2));
        t.print();
        t.addEnd(new Temperatura(5));
        t.print();
        t.addPos(new Temperatura(1), 4);
        t.print();
        t.rmPosRecursiva(1);
        t.print();
        t.rmPosRecursiva(0);
        t.print();
        t.rmPosRecursiva(0);
        t.print();
        t.rmPosRecursiva(1);
        t.print();
        t.rmPosRecursiva(0);
        t.print();

    }

    private static class Temperatura {
        public int temperatura;
        public Temperatura proximo = null;

        public Temperatura(int aux) {
            this.temperatura = aux;
        }
    }
}
