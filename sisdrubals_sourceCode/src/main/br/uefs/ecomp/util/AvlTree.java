package br.uefs.ecomp.util;

import java.io.Serializable;

import br.uefs.ecomp.util.exception.DuplicatedLocalization;

public class AvlTree<E extends Comparable<E>> implements Serializable {

	/**
	 * Necessário para a serialização
	 */
	private static final long serialVersionUID = -7488367896222321979L;
	
	private Node<E> root;
	private int size = 0;

	public AvlTree() {
		super();
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public void add(E data) throws DuplicatedLocalization {
		Node<E> n = new Node<>(data);
		addAVL(this.root, n);
	}

	private void addAVL(Node<E> toCompare, Node<E> toInsert) throws DuplicatedLocalization {

		if (toCompare == null) {

			this.root = toInsert;
			size++;

		} else {

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

	private void checkBalancing(Node<E> current) {
		setBalancing(current);
		int balancing = current.getBalancing();

		if (balancing == -2) {

			if (height(current.getLeft().getLeft()) >= height(current.getLeft().getRight())) {
				current = rightRotation(current);
			} else {
				current = doubleRotationLeftRight(current);
			}

		} else if (balancing == 2) {

			if (height(current.getRight().getRight()) >= height(current.getRight().getLeft())) {
				current = leftRotation(current);
			} else {
				current = doubleRotationRightLeft(current);
			}
		}

		if (current.getParent() != null) {
			checkBalancing(current.getParent());
		} else {
			this.root = current;
		}
	}

	public E remove(E data) {
		return removeAVL(this.root, data);
	}

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

	private void removeFoundNode(Node<E> toRemove) {
		Node<E> r;

		if (toRemove.getLeft() == null || toRemove.getRight() == null) {

			if (toRemove.getParent() == null && toRemove.getLeft() == null && toRemove.getRight() == null) {
				this.root = null;
				return;
			}
			r = toRemove;

		} else {
			r = successor(toRemove);
			toRemove.setData(r.getData());
		}

		Node<E> p;
		if (r.getLeft() != null) {
			p = r.getLeft();
		} else {
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

	private Node<E> leftRotation(Node<E> initial) {

		Node<E> right = initial.getRight();
		right.setParent(initial.getParent());

		initial.setRight(right.getLeft());

		if (initial.getRight() != null) {
			initial.getRight().setParent(initial);
		}

		right.setLeft(initial);
		initial.setParent(right);

		if (right.getParent() != null) {

			if (right.getParent().getRight() == initial) {
				right.getParent().setRight(right);
			} else if (right.getParent().getLeft() == initial) {
				right.getParent().setLeft(right);
			}
		}

		setBalancing(initial);
		setBalancing(right);

		return right;
	}

	private Node<E> rightRotation(Node<E> initial) {

		Node<E> left = initial.getLeft();
		left.setParent(initial.getParent());

		initial.setLeft(left.getRight());

		if (initial.getLeft() != null) {
			initial.getLeft().setParent(initial);
		}

		left.setRight(initial);
		initial.setParent(left);

		if (left.getParent() != null) {

			if (left.getParent().getRight() == initial) {
				left.getParent().setRight(left);
			} else if (left.getParent().getLeft() == initial) {
				left.getParent().setLeft(left);
			}
		}

		setBalancing(initial);
		setBalancing(left);

		return left;
	}

	private Node<E> doubleRotationLeftRight(Node<E> initial) {
		initial.setLeft(leftRotation(initial.getLeft()));
		return rightRotation(initial);
	}

	private Node<E> doubleRotationRightLeft(Node<E> initial) {
		initial.setRight(rightRotation(initial.getRight()));
		return leftRotation(initial);
	}

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
	
	public int height(){
		return height(this.root);
	}

	private int height(Node<E> current) {
		if (current == null) {
			return -1;
		}

		if (current.getLeft() == null && current.getRight() == null) {
			return 0;
		} else if (current.getLeft() == null) {
			return 1 + height(current.getRight());
		} else if (current.getRight() == null) {
			return 1 + height(current.getLeft());
		} else {
			return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
		}
	}

	private void setBalancing(Node<E> node) {
		node.setBalancing(height(node.getRight()) - height(node.getLeft()));
	}

	public E search(E data) {
		return search(this.root, data);
	}

	private E search(Node<E> current, E data) {
		if (current == null) {
			return null;
		}

		if (data.compareTo(current.getData()) < 0) {
			return search(current.getLeft(), data);
		} else if (data.compareTo(current.getData()) > 0) {
			return search(current.getRight(), data);
		} else {
			return current.getData();
		}

	}

	final protected IQueue<E> inOrder() {
		IQueue<E> queue = new Queue<E>();
		inOrder(root, queue);
		return queue;
	}

	final protected void inOrder(Node<E> node, IQueue<E> queue) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), queue);
		queue.add(node.getData());
		inOrder(node.getRight(), queue);
	}
	
	private class AvlIterator implements Iterator<E> {
		private IQueue<E> search;
		
		protected AvlIterator(IQueue<E> received){
			this.search = received;
		}

		@Override
		public boolean hasNext() {
			return !this.search.isEmpty();
		}

		@Override
		public E next() {
			if(this.hasNext())
				return this.search.remove();
			
			return null;
		}
		
	}
	
	final public Iterator<E> list(){
		return new AvlIterator(this.inOrder());
	}

}
