package co.edu.uptc.structures;

public class NodeBinaryTree<T> {
	private T data;
	private NodeBinaryTree<T> left;
	private NodeBinaryTree<T> rigth;

	public NodeBinaryTree(T data){
		this.data = data;
		left = null;
		rigth = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public NodeBinaryTree<T> getLeft() {
		return left;
	}

	public void setLeft(NodeBinaryTree<T> left) {
		this.left = left;
	}

	public NodeBinaryTree<T> getRigth() {
		return rigth;
	}

	public void setRigth(NodeBinaryTree<T> rigth) {
		this.rigth = rigth;
	}
	
	
}
