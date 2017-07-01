package br.uefs.ecomp.util;

public class ArvoreAvl<E extends Comparable<E>> {

    private No<E> raiz;
    private int size = 0;
    
    public int size(){
        return size;
    }

    public void inserir(E elemento) {
        No<E> n = new No<>(elemento);
        inserirAVL(this.raiz, n);
    }

    private void inserirAVL(No<E> aComparar, No<E> aInserir) {

        if (aComparar == null) {

            this.raiz = aInserir;
            size++;

        } else {

            if ((aInserir.getElemento()).compareTo(aComparar.getElemento()) < 0) {

                if (aComparar.getEsquerda() == null) {
                    aComparar.setEsquerda(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);
                    size++;
                } else {
                    inserirAVL(aComparar.getEsquerda(), aInserir);
                }

            } else if ((aInserir.getElemento()).compareTo(aComparar.getElemento()) > 0) {

                if (aComparar.getDireita() == null) {
                    aComparar.setDireita(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);
                    size++;
                } else {
                    inserirAVL(aComparar.getDireita(), aInserir);
                }

            } else {
                // O nó já existe
            }
        }
    }

    private void verificarBalanceamento(No<E> atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2) {

            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                atual = rotacaoDireita(atual);
            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }

        } else if (balanceamento == 2) {

            if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                atual = rotacaoEsquerda(atual);
            } else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if (atual.getPai() != null) {
            verificarBalanceamento(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }

    public void remover(E elemento) {
        removerAVL(this.raiz, elemento);
    }

    private void removerAVL(No<E> atual, E elemento) {
        if (atual == null) {
            return;
        } else {

            if (atual.getElemento().compareTo(elemento) > 0) {
                removerAVL(atual.getEsquerda(), elemento);
            } else if (atual.getElemento().compareTo(elemento) < 0) {
                removerAVL(atual.getDireita(), elemento);
            } else if (atual.getElemento().equals(elemento)) {
                removerNoEncontrado(atual);
                size--;
            }
        }
    }

    private void removerNoEncontrado(No<E> aRemover) {
        No<E> r;

        if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

            if (aRemover.getPai() == null && aRemover.getEsquerda() == null && aRemover.getDireita() == null) {
                this.raiz = null;
                aRemover = null;
                return;
            }
            r = aRemover;

        } else {
            r = sucessor(aRemover);
            aRemover.setElemento(r.getElemento());
        }

        No<E> p;
        if (r.getEsquerda() != null) {
            p = r.getEsquerda();
        } else {
            p = r.getDireita();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.raiz = p;
        } else {
            if (r == r.getPai().getEsquerda()) {
                r.getPai().setEsquerda(p);
            } else {
                r.getPai().setDireita(p);
            }
            verificarBalanceamento(r.getPai());
        }
        r = null;
    }

    private No<E> rotacaoEsquerda(No<E> inicial) {

        No<E> direita = inicial.getDireita();
        direita.setPai(inicial.getPai());

        inicial.setDireita(direita.getEsquerda());

        if (inicial.getDireita() != null) {
            inicial.getDireita().setPai(inicial);
        }

        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == inicial) {
                direita.getPai().setDireita(direita);
            } else if (direita.getPai().getEsquerda() == inicial) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    private No<E> rotacaoDireita(No<E> inicial) {

        No<E> esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());

        inicial.setEsquerda(esquerda.getDireita());

        if (inicial.getEsquerda() != null) {
            inicial.getEsquerda().setPai(inicial);
        }

        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == inicial) {
                esquerda.getPai().setDireita(esquerda);
            } else if (esquerda.getPai().getEsquerda() == inicial) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }

    private No<E> duplaRotacaoEsquerdaDireita(No<E> inicial) {
        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
        return rotacaoDireita(inicial);
    }

    private No<E> duplaRotacaoDireitaEsquerda(No<E> inicial) {
        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
        return rotacaoEsquerda(inicial);
    }

    private No<E> sucessor(No<E> q) {
        if (q.getDireita() != null) {
            No<E> r = q.getDireita();
            while (r.getEsquerda() != null) {
                r = r.getEsquerda();
            }
            return r;
        } else {
            No<E> p = q.getPai();
            while (p != null && q == p.getDireita()) {
                q = p;
                p = q.getPai();
            }
            return p;
        }
    }

    private int altura(No<E> atual) {
        if (atual == null) {
            return -1;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            return 0;
        } else if (atual.getEsquerda() == null) {
            return 1 + altura(atual.getDireita());
        } else if (atual.getDireita() == null) {
            return 1 + altura(atual.getEsquerda());
        } else {
            return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
        }
    }

    private void setBalanceamento(No<E> no) {
        no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }

}
