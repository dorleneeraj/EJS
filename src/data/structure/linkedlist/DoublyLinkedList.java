package data.structure.linkedlist;

import java.util.Iterator;
import java.util.List;

public class DoublyLinkedList<T> implements ILinkedList<T> {

	protected INode<T> Head;
	protected int length;
	protected INode<T> Tail;

	public DoublyLinkedList() {
		this.length = 0;
		this.Head = null;
		this.Tail = null;
	}

	public DoublyLinkedList(List<INode<T>> nodes) {
		this.length = 0;
		if ((null != nodes) && (!nodes.isEmpty())) {
			for (INode<T> node : nodes) {
				this.addNode(node);
			}
		}
	}

	@Override
	public INode<T> getHead() {
		return this.Head;
	}

	@Override
	public INode<T> getTail() {
		INode<T> node = this.Head;
		if (null != node) {
			while (null != ((SNode<T>) node).getNext()) {
				node = ((SNode<T>) node).getNext();
			}
		}
		return node;
	}

	@Override
	public Integer getLength() {
		return this.length;
	}

	@Override
	public boolean addNode(INode<T> node) {
		boolean isSuccess = false;
		if (null == this.Head) {
			this.Head = node;
			isSuccess = true;
		} else {
			INode<T> helperNode = this.Head;
			while (null != ((DNode<T>) helperNode).Next) {
				helperNode = ((DNode<T>) helperNode).Next;
			}
			((DNode<T>) helperNode).Next = node;
			((DNode<T>) node).Previous = helperNode;
			this.length += 1;
			isSuccess = true;
		}
		return isSuccess;
	}

	@Override
	public boolean addNode(INode<T> node, int index) {
		boolean isSuccess = false;
		if (index > 0 && (index <= this.length + 1)) {
			int i = 1;
			INode<T> helperNode = this.Head;
			while (i < index - 1) {
				helperNode = ((DNode<T>) helperNode).Next;
				i++;
			}
			((DNode<T>) node).Next = ((DNode<T>) helperNode).Next;
			if (null != ((DNode<T>) helperNode).Next) {
				((DNode<T>) (((DNode<T>) helperNode).Next)).Previous = node;
			}
			((DNode<T>) helperNode).Next = node;
			((DNode<T>) node).Previous = helperNode;
			this.length += 1;
			isSuccess = true;
		}
		return isSuccess;
	}

	@Override
	public boolean addNodes(List<INode<T>> nodes) {
		boolean isSuccess = false;
		if (null != nodes && !nodes.isEmpty()) {
			for (INode<T> dN : nodes) {
				this.addNode(dN);
				this.length += 1;
			}
			isSuccess = true;
		}

		return isSuccess;
	}

	@Override
	public INode<T> removeNode() {
		int i = 1;
		INode<T> removedNode = null;
		INode<T> helperNode = this.Head;
		while (i < this.length - 1 && (null != helperNode)) {
			helperNode = ((DNode<T>) helperNode).Next;
			i++;
		}
		if (null != helperNode) {
			removedNode = ((DNode<T>) helperNode).Next;
			((DNode<T>) helperNode).Next = null;
			this.length -= 1;
		}
		return removedNode;
	}

	@Override
	public INode<T> removeNode(int index) {
		INode<T> removedNode = null;
		int i = 1;
		INode<T> helperNode = this.Head;
		if (index > 0 && (index <= this.length)) {
			while (i < this.length - 1 && (null != helperNode)) {
				helperNode = ((DNode<T>) helperNode).Next;
				i++;
			}
			if (null != helperNode) {
				removedNode = ((DNode<T>) helperNode).Next;
				((DNode<T>) helperNode).Next = ((DNode<T>) removedNode).Next;
				if (null != ((DNode<T>) removedNode).Next) {
					((DNode<T>) ((DNode<T>) removedNode).Next).Previous = helperNode;
				}
			}
			this.length -= 1;
		}

		return removedNode;
	}

	@Override
	public INode<T> removeNode(INode<T> node) {
		INode<T> removedNode = null;
		int index = this.contains(node);
		if (index > 0) {
			removedNode = this.removeNode(index);
			this.length -= 1;
		}
		return removedNode;
	}

	@Override
	public Integer contains(INode<T> node) {
		int i = 1;
		int returnIndex = 0;
		DNode<T> helperNode = (DNode<T>) this.Head;
		while (i <= this.length) {
			if (helperNode.equals((DNode<T>) node)) {
				returnIndex = i;
				break;
			}
			helperNode = (DNode<T>) helperNode.Next;
			i++;
		}
		return returnIndex;
	}

	@Override
	public boolean isEmpty() {
		return this.length == 0 ? true : false;
	}

	@Override
	public ILinkedList<T> sortList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILinkedList<T> reverseList() {

		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new DListIterator();
	}

	private class DListIterator implements Iterator<Integer> {
		int i = 0;
		int j = length;
		INode<T> node = Head;

		@Override
		public boolean hasNext() {
			return i < (j - 1);
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			int val = (Integer) node.getData();
			node = ((DNode) node).Next;
			i++;
			return val;
		}

		@Override
		public void remove() {

			// Currently not supported...
		}

	}

}
