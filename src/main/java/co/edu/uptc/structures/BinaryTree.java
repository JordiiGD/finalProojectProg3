package co.edu.uptc.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<T> implements ITree<T> {
	private NodeBinaryTree<T> root;
	private Comparator<T> comparator;

	public BinaryTree(Comparator<T> comparator) {
		this.root = null;
		this.comparator = comparator;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean add(T data) {
		Logical logical = new Logical();
		root = add(data, root, logical);
		return logical.isSuccess();
	}

	public NodeBinaryTree<T> add(T data, NodeBinaryTree<T> node, Logical logical) {
		if (node == null) {
			node = new NodeBinaryTree<T>(data);
			logical.setSuccess(true);
		} else {
			if (comparator.compare(data, node.getData()) < 0) {
				NodeBinaryTree<T> nodeNew = add(data, node.getLeft(), logical);
				node.setLeft(nodeNew);
			} else {
				NodeBinaryTree<T> nodeNew = add(data, node.getRigth(), logical);
				node.setRigth(nodeNew);
			}
		}
		return node;
	}

	public boolean remove(T data) {
		Logical logical = new Logical();
		root = remove(data, root, logical);
		return true;
	}

	public NodeBinaryTree<T> remove(T data, NodeBinaryTree<T> node, Logical logical) {
		NodeBinaryTree<T> aux;
		if (node == null) {
			node = null;// throw
		} else {
			if (comparator.compare(data, node.getData()) == 0) {
				node = eliminate(node);
				logical.setSuccess(true);
			} else {
				if (comparator.compare(data, node.getData()) < 0) {
					aux = remove(data, node.getLeft(), logical);
					node.setLeft(aux);
				} else {
					aux = remove(data, node.getRigth(), logical);
					node.setRigth(aux);
				}
			}
		}
		return node;
	}

	public NodeBinaryTree<T> eliminate(NodeBinaryTree<T> node) {
		if (node.getLeft() == null && node.getRigth() == null) {
			return null;
		} else {
			if (node.getLeft() == null) {
				node = node.getRigth();
			} else {
				if (node.getRigth() == null) {
					node = node.getLeft();
				} else {
					NodeBinaryTree<T> aux = node.getLeft();
					NodeBinaryTree<T> prev = node;

					while (aux.getRigth() != null) {
						prev = aux;
						aux = aux.getRigth();
					}
					node.setData(aux.getData());

					if (prev == node) {
						prev.setLeft(aux.getLeft());
					} else {
						prev.setRigth(aux.getLeft());
					}

				}
			}
		}
		return node;
	}

	public boolean contains(T data) {
		return contains(data, root);
	}

	public boolean contains(T data, NodeBinaryTree<T> node) {
		boolean exists = false;
		if (node == null) {
			exists = false;
		} else {
			if (comparator.compare(data, node.getData()) == 0) {
				exists = true;
			} else {
				if (comparator.compare(data, node.getData()) < 0) {
					exists = contains(data, node.getLeft());
				} else {
					exists = contains(data, node.getRigth());
				}
			}
		}
		return exists;
	}

	public List<T> inOrder() {
		ArrayList<T> result = new ArrayList<>();
		inOrder(root, result);
		return result;
	}

	public void inOrder(NodeBinaryTree<T> node, ArrayList<T> ordered) {
		if (node != null) {
			inOrder(node.getLeft(), ordered);
			ordered.add(node.getData());
			inOrder(node.getRigth(), ordered);
		}
	}
	
	@Override
	public Comparator<T> comparator() {
		return comparator;
	}

	@Override
	public Iterator<T> iterator() {
		ArrayList<T> list = new ArrayList<T>();
		inOrder(root, list);
		return list.iterator();
	}

}
