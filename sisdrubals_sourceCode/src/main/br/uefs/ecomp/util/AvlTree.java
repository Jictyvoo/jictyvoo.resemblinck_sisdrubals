package br.uefs.ecomp.util;

import java.io.Serializable;

import br.uefs.ecomp.util.exception.DuplicatedLocalization;

/**
 * @author João Victor & Resemblinck Freitas
 */
public class AvlTree<E extends Comparable<E>> implements Serializable {

	/**
	 * Necess�rio para a serializa��o
	 */
	private static final long serialVersionUID = -7488367896222321979L;
	
	/**
	 * Ra�z da �rvore
	 */
	private Node<E> root;
	
	/**
	 * Total de elementos da �rvore
	 */
	private int size = 0;

	/**
	 * Construtor da �rvore
	 */
	public AvlTree() {
		super();
		this.size = 0;
	}

	/**
	 * M�todo que retorna o total de elementos presentes na �rvore
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * M�todo que recebe um elemento, cria um novo n� e insere na �rvore
	 * @param data - elemento a ser inserido
	 * @throws DuplicatedLocalization
	 */
	public void add(E data) throws DuplicatedLocalization {
		Node<E> n = new Node<>(data);
		addAVL(this.root, n);
	}

	/**
	 * M�todo recursivo para encontrar a posi��o correta de inser��o do elemento passado como par�metro
	 * @param toCompare - N� a ser comparado, para ver se a inser��o deve ser feita no lado esquerdo ou direito
	 * @param toInsert - N� a ser inserido
	 * @throws DuplicatedLocalization
	 */
	private void addAVL(Node<E> toCompare, Node<E> toInsert) throws DuplicatedLocalization {

		if (toCompare == null) { //Indica que a �rvore est� vazia

			this.root = toInsert;
			size++;

		} else { //�rvore n�o vazia

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
	 * M�todo que checa o fator de balanceamento da �rvore para saber se precisa ser balanceada
	 * @param current - Raiz da �rvore/sub-arvore que ser� checada
	 */
	private void checkBalancing(Node<E> current) {
		setBalancing(current);
		int balancing = current.getBalancing();

		if (balancing == -2) { //Verifica se precisa balancear

			if (height(current.getLeft().getLeft()) >= height(current.getLeft().getRight())) { //Verifica a altura nos dois lados
				current = rightRotation(current); //Faz uma rota��o
			} else {
				current = doubleRotationLeftRight(current); //Faz uma dupla rota��o
			}

		} else if (balancing == 2) { //Verifica se precisa balancear

			if (height(current.getRight().getRight()) >= height(current.getRight().getLeft())) { //Verifica a altura nos dois lados
				current = leftRotation(current); //Faz uma rota��o
			} else {
				current = doubleRotationRightLeft(current); //Faz uma dupla rota��o
			}
		}

		if (current.getParent() != null) { //Verifica se n�o � a raiz
			checkBalancing(current.getParent()); //Checa o fator de balanceamento do pai
		} else {
			this.root = current;
		}
	}

	/**
	 * M�todo de remo��o da �rvore
	 * @param data - Elemento a ser removido
	 * @return Refer�ncia para o objeto removido ou null caso ele n�o seja encontrado
	 */
	public E remove(E data) {
		return removeAVL(this.root, data);
	}

	/**
	 * M�todo recursivo para encontrar o N� a ser removido
	 * @param current - Raiz da arvore/sub-arvore
	 * @param data - Elemento que sera removido
	 * @return Refer�ncia para o objeto removido ou null caso ele n�o seja encontrado
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
	 * M�todo que remove um determinado N� da arvore
	 * @param toRemove - N� a ser removido
	 */
	private void removeFoundNode(Node<E> toRemove) {
		Node<E> r;

		if (toRemove.getLeft() == null || toRemove.getRight() == null) { //Verifica se o N� possui 0 ou 1 filho

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
	 * M�todo que realiza uma rota��o simples � esquerda, ocorre quando � inserido um N� na direita de
	 * um filho � direita, desbalanceando a �rvore, ent�o tem de ser feita uma rota��o para a esquerda. 
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore ap�s o balanceamento
	 */
	private Node<E> leftRotation(Node<E> initial) {

		Node<E> right = initial.getRight(); //Guarda a refer�ncia para o filho direito
		right.setParent(initial.getParent()); //Seta o pai do n�

		initial.setRight(right.getLeft());  //Seta o filho direito de "initial" como sendo o filho esquerdo do seu filho direito

		if (initial.getRight() != null) { //Verifica que "initial" ainda possui filho direito
			initial.getRight().setParent(initial);
		}

		right.setLeft(initial); //P�e "initial" na esquerda 
		initial.setParent(right); //O antigo filho direito agora vira a nova raiz

		if (right.getParent() != null) { //Verifica que n�o � a raiz da arvore

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
	 * M�todo que realiza uma rota��o simples � direita, ocorre quando � inserido um N� na esquerda de
	 * um filho � esquerda, desbalanceando a �rvore, ent�o tem de ser feita uma rota��o para a direita.
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore ap�s o balanceamento
	 */
	private Node<E> rightRotation(Node<E> initial) {

		Node<E> left = initial.getLeft(); //Guarda refer�ncia para o filho esquerdo de "initial"
		left.setParent(initial.getParent()); //Seta o pai do n�

		initial.setLeft(left.getRight()); //Seta o filho esquerdo de "initial" como sendo o filho direito do seu filho esquerdo

		if (initial.getLeft() != null) { //Verifica que "initial" ainda possui filho esquerdo
			initial.getLeft().setParent(initial);
		}

		left.setRight(initial); //P�e "initial" na direita
		initial.setParent(left); //O antigo filho esquerdo agora vira a nova raiz

		if (left.getParent() != null) { //Verifica que n�o � a raiz da arvore

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
	 * M�todo que realiza uma dupla rota��o. Primeiro uma rota��o simples � esquerda, depois uma rota��o
	 * simples � direita. Ocorre quando � inserido um N� na sub-arvore direita de uma sub-arvore � esquerda
	 * de uma determinada raiz, desbalanceando a �rvore.
	 * 
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore ap�s o balanceamento
	 */
	private Node<E> doubleRotationLeftRight(Node<E> initial) {
		initial.setLeft(leftRotation(initial.getLeft()));
		return rightRotation(initial);
	}

	/**
	 * M�todo que realiza uma dupla rota��o. Primeiro uma rota��o simples � direita, depois uma rota��o
	 * simples � esquerda. Ocorre quando � inserido um N� na sub-arvore esquerda de uma sub-arvore � direita
	 * de uma determinada raiz, desbalanceando a �rvore.
	 * 
	 * @param initial - Raiz da sub-arvore
	 * @return A nova raiz da sub-arvore ap�s o balanceamento
	 */
	private Node<E> doubleRotationRightLeft(Node<E> initial) {
		initial.setRight(rightRotation(initial.getRight()));
		return leftRotation(initial);
	}

	/**
	 * M�todo que retorna o filho sucessor de um determinado n�
	 * @param q - N� do qual se deseja recuperar o sucessor
	 * @return O filho sucessor do n� passado
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
	 * M�todo que retorna a altura desde a raiz da arvore
	 * @return Altura da arvore completa
	 */
	public int height(){
		return height(this.root);
	}

	/**
	 * M�todo recursivo que retorna a altura de qualquer sub-arvore
	 * @param current - Raiz da sub-arvore
	 * @return Altura da sub-arvore
	 */
	private int height(Node<E> current) {
		if (current == null) { //Verifica se tem raiz
			return -1;
		}

		if (current.getLeft() == null && current.getRight() == null) { //Retorna 0 se tiver uma raiz sem filhos
			return 0;
		} else if (current.getLeft() == null) { //Se n�o tiver filho esquerdo retorna a altura apenas da direita
			return 1 + height(current.getRight());
		} else if (current.getRight() == null) { //Se n�o tiver filho direito retorna a altura apenas da esquerda
			return 1 + height(current.getLeft());
		} else { //Se tiver 2 filhos, retorna a maior altura entre os dois
			return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
		}
	}

	/**
	 * M�todo que atualiza o fator de balanceamento de um n�, com base na altura dos filhos
	 * @param node - N� que tera o fator de balanceamento atualizado
	 */
	private void setBalancing(Node<E> node) {
		node.setBalancing(height(node.getRight()) - height(node.getLeft()));
	}

	/**
	 * M�todo que busca um elemento na �rvore
	 * @param data - Elemento que ser� buscado
	 * @return Refer�ncia para o elemento buscado ou null caso ele n�o seja encontrado
	 */
	public E search(E data) {
		return search(this.root, data);
	}

	/**
	 * M�todo recursivo de busca bin�ria na �rvore
	 * @param current - Raiz da arvore/sub-arvore
	 * @param data - Elemento buscado
	 * @return Refer�ncia para o elemento buscado ou null caso ele n�o seja encontrado
	 */
	private E search(Node<E> current, E data) {
		if (current == null) { //Chegou ao final e n�o encontrou
			return null;
		}

		if (data.compareTo(current.getData()) < 0) { //Verifica se est� no lado esquerdo
			return search(current.getLeft(), data);
		} else if (data.compareTo(current.getData()) > 0) { //Verifica se est� no lado direito 
			return search(current.getRight(), data);
		} else { //Encontrou
			return current.getData();
		}

	}

	/**
	 * M�todo para percorrer a �rvore em ordem
	 * @return Fila contendo os elementos da arvore ordenados
	 */
	final protected IQueue<E> inOrder() {
		IQueue<E> queue = new Queue<E>();
		inOrder(root, queue);
		return queue;
	}

	/**
	 * M�todo recursivo para perorrer a arvore em ordem e adicionar os elementos em uma fila
	 * @param node - Raiz da arvore/sub-arvore
	 * @param queue - Fila que ir� conter os elementos
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
	 * M�todo que retorna o iterador para que possa listar os elementos da �rvore
	 * @return Iterador da arvore
	 */
	final public Iterator<E> list(){
		return new AvlIterator(this.inOrder());
	}
	
	/**
	 * Classe privada que implementa o iterador da arvore
	 * @author Jo�o Victor & Resemblinck Freitas
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
		 * M�todo que verifica se ainda h� elementos para serem listados
		 */
		@Override
		public boolean hasNext() {
			return !this.search.isEmpty();
		}

		/**
		 * M�todo que retorna o pr�ximo elemento que ser� listado
		 */
		@Override
		public E next() {
			if(this.hasNext())
				return this.search.remove();
			
			return null;
		}
		
	}

}
