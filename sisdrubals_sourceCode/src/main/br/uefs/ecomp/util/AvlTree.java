package br.uefs.ecomp.util;

import java.io.Serializable;

import br.uefs.ecomp.util.exception.DuplicatedLocalization;

/**
 * @author JoÃ£o Victor & Resemblinck Freitas
 */
public class AvlTree<E extends Comparable<E>> implements Serializable {

	/**
	 * Necessário para a serialização
	 */
	private static final long serialVersionUID = -7488367896222321979L;
	
	/**
	 * Raíz da árvore
	 */
	private Node<E> root;
	
	/**
	 * Total de elementos da árvore
	 */
	private int size = 0;

	/**
	 * Construtor da árvore
	 */
	public AvlTree() {
		super();
		this.size = 0;
	}

	/**
	 * Método que retorna o total de elementos presentes na árvore
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * Método que recebe um elemento, cria um novo nó e insere na árvore
	 * @param data - elemento a ser inserido
	 * @throws DuplicatedLocalization
	 */
	public void add(E data) throws DuplicatedLocalization {
		Node<E> n = new Node<>(data);
		addAVL(this.root, n);
	}

	/**
	 * Método recursivo para encontrar a posição correta de inserção do elemento passado como parâmetro
	 * @param toCompare - Nó a ser comparado, para ver se a inserção deve ser feita no lado esquerdo ou direito
	 * @param toInsert - Nó a ser inserido
	 * @throws DuplicatedLocalization
	 */
	private void addAVL(Node<E> toCompare, Node<E> toInsert) throws DuplicatedLocalization {

		if (toCompare == null) { //Indica que a árvore está vazia

			this.root = toInsert;
			size++;

		} else { //Árvore não vazia

			if ((toInsert.getData()).compareTo(toCompare.getData()) < 0) {

				if (toCompare.getLeft() == null) { 
					toCompare.setLeft(toInsert);
					toInsert.setParent(toCompare);
					checkBalancing(toCompare);
					size++;
				} else {
					addAVL(toCompare.getLeft(), toInsert);
				}

			} else if ((toInsert.getData()).compareTo(toCompare.getData()) > 0) {

				if (toCompare.getRight() == null) {
					toCompare.setRight(toInsert);
					toInsert.setParent(toCompare);
					checkBalancing(toCompare);
					size++;
				} else {
					addAVL(toCompare.getRight(), toInsert);
				}

			} else {
				throw new DuplicatedLocalization("O item " + toInsert + " inserido ja existe");
			}
		}
	}

	/**
	 * Método que checa o fator de balanceamento da árvore para saber se precisa ser balanceada
	 * @param current - Raiz da árvore/sub-arvore que será checada
	 */
	private void checkBalancing(Node<E> current) {
		setBalancing(current);
		int balancing = current.getBalancing();

		if (balancing == -2) { //Verifica se precisa balancear

			if (height(current.getLeft().getLeft()) >= height(current.getLeft().getRight())) { //Verifica a altura nos dois lados
				current = rightRotation(current); //Faz uma rotação
			} else {
				current = doubleRotationLeftRight(current); //Faz uma dupla rotação
			}

		} else if (balancing == 2) { //Verifica se precisa balancear

			if (height(current.getRight().getRight()) >= height(current.getRight().getLeft())) { //Verifica a altura nos dois lados
				current = leftRotation(current); //Faz uma rotação
			} else {
				current = doubleRotationRightLeft(current); //Faz uma dupla rotação
			}
		}

		if (current.getParent() != null) { //Verifica se não é a raiz
			checkBalancing(current.getParent()); //Checa o fator de balanceamento do pai
		} else {
			this.root = current;
		}
	}

	/**
	 * Método de remoção da árvore
	 * @param data - Elemento a ser removido
	 * @return Referência para o objeto removido ou null caso ele não seja encontrado
	 */
	public E remove(E data) {
		return removeAVL(this.root, data);
	}

	/**
	 * Método recursivo para encontrar o Nó a ser removido
	 * @param current - Raiz da arvore/sub-arvore
	 * @param data - Elemento que sera removido
	 * @return Referência para o objeto removido ou null caso ele não seja encontrado
	 */
	private E removeAVL(Node<E> current, E data) {
		if (current != null) {

			if (current.getData().compareTo(data) > 0) {
				return removeAVL(current.getLeft(), data);
			} else if (current.getData().compareTo(data) < 0) {
				return removeAVL(current.getRight(), data);
			} else {
				E temp = current.getData();
				removeFoundNode(current);
				size--;
				return temp;
			}
		}
		return null;
	}

	/**
	 * Método que remove um determinado Nó da arvore
	 * @param toRemove - Nó a ser removido
	 */
	private void removeFoundNode(Node<E> toRemove) {
		Node<E> r;

		if (toRemove.getLeft() == null || toRemove.getRight() == null) { //Verifica se o Nó possui 0 ou 1 filho

			if (toRemove.getParent() == null && toRemove.getLeft() == null && toRemove.getRight() == null) { //Raiz sem filhos
				this.root = null;
				return;
			}
			r = toRemove;

		} else { //Com 2 filhos
			r = successor(toRemove); //Captura o sucessor
			toRemove.setData(r.getData()); //Coloca o filho sucessor no lugar do pai
		}

		Node<E> p;
		if (r.getLeft() != null) { //Verifica se tem filho esquerdo
			p = r.getLeft();
		} else { //Verifica se tem filho direito
			p = r.getRight();
		}

		if (p != null) {
			p.setParent(r.getParent());
		}

		if (r.getParent() == null) {
			this.root = p;
		} else {
			if (r == r.getParent().getLeft()) {
				r.getParent().setLeft(p);
			} else {
				r.getParent().setRight(p);
			}
			checkBalancing(r.getParent());
		}
	}

	/**
	 * Método que realiza uma rotação simples à esquerda, ocorre quando é inserido um Nó na direita de
	 * um filho à direita, desbalanceando a árvore, então tem de ser feita uma rotação para a esquerda. 
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore após o balanceamento
	 */
	private Node<E> leftRotation(Node<E> initial) {

		Node<E> right = initial.getRight(); //Guarda a referência para o filho direito
		right.setParent(initial.getParent()); //Seta o pai do nó

		initial.setRight(right.getLeft());  //Seta o filho direito de "initial" como sendo o filho esquerdo do seu filho direito

		if (initial.getRight() != null) { //Verifica que "initial" ainda possui filho direito
			initial.getRight().setParent(initial);
		}

		right.setLeft(initial); //Põe "initial" na esquerda 
		initial.setParent(right); //O antigo filho direito agora vira a nova raiz

		if (right.getParent() != null) { //Verifica que não é a raiz da arvore

			if (right.getParent().getRight() == initial) { 
				right.getParent().setRight(right);
			} else if (right.getParent().getLeft() == initial) {
				right.getParent().setLeft(right);
			}
		}

		setBalancing(initial); //Atualiza o fator de balanceamento 
		setBalancing(right);

		return right; //Retorna a nova raiz da sub-arvore que acaba de ser balanceada
	}

	/**
	 * Método que realiza uma rotação simples à direita, ocorre quando é inserido um Nó na esquerda de
	 * um filho à esquerda, desbalanceando a árvore, então tem de ser feita uma rotação para a direita.
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore após o balanceamento
	 */
	private Node<E> rightRotation(Node<E> initial) {

		Node<E> left = initial.getLeft(); //Guarda referência para o filho esquerdo de "initial"
		left.setParent(initial.getParent()); //Seta o pai do nó

		initial.setLeft(left.getRight()); //Seta o filho esquerdo de "initial" como sendo o filho direito do seu filho esquerdo

		if (initial.getLeft() != null) { //Verifica que "initial" ainda possui filho esquerdo
			initial.getLeft().setParent(initial);
		}

		left.setRight(initial); //Põe "initial" na direita
		initial.setParent(left); //O antigo filho esquerdo agora vira a nova raiz

		if (left.getParent() != null) { //Verifica que não é a raiz da arvore

			if (left.getParent().getRight() == initial) {
				left.getParent().setRight(left);
			} else if (left.getParent().getLeft() == initial) {
				left.getParent().setLeft(left);
			}
		}

		setBalancing(initial); //Atualiza o fator de balanceamento
		setBalancing(left);

		return left; //Retorna a nova raiz da sub-arvore que acaba de ser balanceada
	}

	/**
	 * Método que realiza uma dupla rotação. Primeiro uma rotação simples à esquerda, depois uma rotação
	 * simples à direita. Ocorre quando é inserido um Nó na sub-arvore direita de uma sub-arvore à esquerda
	 * de uma determinada raiz, desbalanceando a árvore.
	 * 
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore após o balanceamento
	 */
	private Node<E> doubleRotationLeftRight(Node<E> initial) {
		initial.setLeft(leftRotation(initial.getLeft()));
		return rightRotation(initial);
	}

	/**
	 * Método que realiza uma dupla rotação. Primeiro uma rotação simples à direita, depois uma rotação
	 * simples à esquerda. Ocorre quando é inserido um Nó na sub-arvore esquerda de uma sub-arvore à direita
	 * de uma determinada raiz, desbalanceando a árvore.
	 * 
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore após o balanceamento
	 */
	private Node<E> doubleRotationRightLeft(Node<E> initial) {
		initial.setRight(rightRotation(initial.getRight()));
		return leftRotation(initial);
	}

	/**
	 * Método que retorna o filho sucessor de um determinado nó
	 * @param q - Nó do qual se deseja recuperar o sucessor
	 * @return O filho sucessor do nó passado
	 */
	private Node<E> successor(Node<E> q) {
		if (q.getRight() != null) {
			Node<E> r = q.getRight();
			while (r.getLeft() != null) {
				r = r.getLeft();
			}
			return r;
		} else {
			Node<E> p = q.getParent();
			while (p != null && q == p.getRight()) {
				q = p;
				p = q.getParent();
			}
			return p;
		}
	}
	
	/**
	 * Método que retorna a altura desde a raiz da arvore
	 * @return Altura da arvore completa
	 */
	public int height(){
		return height(this.root);
	}

	/**
	 * Método recursivo que retorna a altura de qualquer sub-arvore
	 * @param current - Raiz da sub-arvore
	 * @return Altura da sub-arvore
	 */
	private int height(Node<E> current) {
		if (current == null) { //Verifica se tem raiz
			return -1;
		}

		if (current.getLeft() == null && current.getRight() == null) { //Retorna 0 se tiver uma raiz sem filhos
			return 0;
		} else if (current.getLeft() == null) { //Se não tiver filho esquerdo retorna a altura apenas da direita
			return 1 + height(current.getRight());
		} else if (current.getRight() == null) { //Se não tiver filho direito retorna a altura apenas da esquerda
			return 1 + height(current.getLeft());
		} else { //Se tiver 2 filhos, retorna a maior altura entre os dois
			return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
		}
	}

	/**
	 * Método que atualiza o fator de balanceamento de um nó, com base na altura dos filhos
	 * @param node - Nó que tera o fator de balanceamento atualizado
	 */
	private void setBalancing(Node<E> node) {
		node.setBalancing(height(node.getRight()) - height(node.getLeft()));
	}

	/**
	 * Método que busca um elemento na árvore
	 * @param data - Elemento que será buscado
	 * @return Referência para o elemento buscado ou null caso ele não seja encontrado
	 */
	public E search(E data) {
		return search(this.root, data);
	}

	/**
	 * Método recursivo de busca binária na árvore
	 * @param current - Raiz da arvore/sub-arvore
	 * @param data - Elemento buscado
	 * @return Referência para o elemento buscado ou null caso ele não seja encontrado
	 */
	private E search(Node<E> current, E data) {
		if (current == null) { //Chegou ao final e não encontrou
			return null;
		}

		if (data.compareTo(current.getData()) < 0) { //Verifica se está no lado esquerdo
			return search(current.getLeft(), data);
		} else if (data.compareTo(current.getData()) > 0) { //Verifica se está no lado direito 
			return search(current.getRight(), data);
		} else { //Encontrou
			return current.getData();
		}

	}

	/**
	 * Método para percorrer a árvore em ordem
	 * @return Fila contendo os elementos da arvore ordenados
	 */
	final protected IQueue<E> inOrder() {
		IQueue<E> queue = new Queue<E>();
		inOrder(root, queue);
		return queue;
	}

	/**
	 * Método recursivo para perorrer a arvore em ordem e adicionar os elementos em uma fila
	 * @param node - Raiz da arvore/sub-arvore
	 * @param queue - Fila que irá conter os elementos
	 */
	final protected void inOrder(Node<E> node, IQueue<E> queue) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), queue); 
		queue.add(node.getData());
		inOrder(node.getRight(), queue);
	}
	
	/**
	 * Método que retorna o iterador para que possa listar os elementos da árvore
	 * @return Iterador da arvore
	 */
	final public Iterator<E> list(){
		return new AvlIterator(this.inOrder());
	}
	
	/**
	 * Classe privada que implementa o iterador da arvore
	 * @author João Victor & Resemblinck Freitas
	 *
	 */
	private class AvlIterator implements Iterator<E> {
		/**
		 * Fila para ser percorrida
		 */
		private IQueue<E> search;
		
		/**
		 * Construtor que inicializa o iterador
		 * @param received - Fila com os elementos ordenados
		 */
		protected AvlIterator(IQueue<E> received){
			this.search = received;
		}

		/**
		 * Método que verifica se ainda há elementos para serem listados
		 */
		@Override
		public boolean hasNext() {
			return !this.search.isEmpty();
		}

		/**
		 * Método que retorna o próximo elemento que será listado
		 */
		@Override
		public E next() {
			if(this.hasNext())
				return this.search.remove();
			
			return null;
		}
		
	}

}
