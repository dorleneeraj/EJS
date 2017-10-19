package data.structure.linkedlist;

import java.util.List;

public class SinglyLinkedList<T> implements ILinkedList<T> {
	INode<T> Head;
	INode<T> Tail;
	Integer length;

	public SinglyLinkedList() {
		this.length = 0;
	}

	public SinglyLinkedList(List<INode<T>> nodes) {
		this.length = 0;
		if ((null != nodes) && (!nodes.isEmpty())) {
			for (INode<T> node : nodes) {
				this.addNode(node);
			}
		}
	}

	@Override
	public INode<T> getHead() {
		INode<T> head = null;
		if (null != this.Head) {
			head = this.Head;
		}
		return head;
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
		if (null != node) {
			INode<T> tail = this.getTail();
			if (null == tail) {
				this.Head = node;
			} else {
				((SNode<T>) tail).setNext(node);
			}
			isSuccess = true;
			this.length += 1; // Increase the length by 1
		}
		return isSuccess;
	}

	@Override
	public boolean addNode(INode<T> node, int index) {
		boolean isSuccess = false;
		if ((index > 0) && (index <= this.length + 1)) {
			if (node instanceof SNode) {
				if (index == 1) {
					if (null == this.Head) {
						isSuccess = this.addNode(node);
					} else {
						((SNode<T>) node).setNext(this.Head);
						this.Head = node;
					}
					isSuccess = true;
					this.length += 1;
				} else {
					SNode<T> helperNode = (SNode<T>) this.Head;
					int i = 1;
					while (i < index) {
						helperNode = (SNode<T>) helperNode.getNext();
						i++;
					}
					INode<T> tempNode = helperNode.getNext();
					helperNode.setNext(node);
					((SNode<T>) node).setNext(tempNode);
					isSuccess = true;
					this.length += 1;
				}
			}
		}
		return isSuccess;
	}

	@Override
	public INode<T> removeNode() {
		INode<T> removedNode = null;
		if (this.length > 0) {
			if (this.length == 1) {
				removedNode = this.Head;
				this.Head = null;
			} else {
				int i = 1;
				INode<T> helperNode = this.Head;
				while (i < this.length) {
					helperNode = ((SNode<T>) helperNode).getNext();
					i++;
				}
				removedNode = ((SNode<T>) helperNode).getNext();
				((SNode<T>) helperNode).setNext(null);
			}
			this.length -= 1;
		}
		return removedNode;
	}

	@Override
	public INode<T> removeNode(int index) {
		INode<T> removedNode = null;
		if ((this.length > 0) && ((index > 0) && (index <= this.length))) {
			if (index == 1) {
				removedNode = this.Head;
				this.Head = ((SNode<T>) removedNode).getNext();
			} else {
				int i = 1;
				INode<T> helperNode = this.Head;
				while (i < index - 1) {
					helperNode = ((SNode<T>) helperNode).getNext();
					i++;
				}
				removedNode = ((SNode<T>) helperNode).getNext();
				((SNode<T>) helperNode).setNext(((SNode<T>) removedNode)
						.getNext());

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
		}
		return removedNode;
	}

	@Override
	public Integer contains(INode<T> node) {
		int i = 1;
		int returnIndex = 0;
		SNode<T> helperNode = (SNode<T>) this.Head;
		while (i <= this.length) {
			if (helperNode.equals((SNode<T>) node)) {
				returnIndex = i;
				break;
			}
			helperNode = (SNode<T>) helperNode.Next;
			i++;
		}
		return returnIndex;
	}

	@Override
	public boolean isEmpty() {
		return (this.length == 0 ? true : false);
	}

	@Override
	public ILinkedList<T> sortList() {
		// TODO Auto-generated method stub
		return null;
	}

	// Reverses the linked list.
	@Override
	public ILinkedList<T> reverseList() {
		if (this.length > 1) {
			int j = 2;
			while (j <= this.length) {
				this.addNode(removeNode(j), 1);
				j++;
				System.out.println(this);
			}
		}

		System.out.println(this);
		return this;
	}

	public ILinkedList<T> reverseList2() {

		INode<T> nextNode = ((SNode<T>) this.Head).Next;
		while (null != nextNode) {
			INode<T> tempNode = ((SNode<T>) nextNode).Next;
			((SNode<T>) nextNode).Next = this.Head;
			this.Head = nextNode;
			nextNode = tempNode;
		}

		return this;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("SinglyLinkedList [");
		SNode<T> helperNode = (SNode<T>) this.Head;
		if (null != helperNode) {
			for (int i = 1; i <= this.length && helperNode != null; i++) {
				s.append(helperNode.toString());
				helperNode = (SNode<T>) helperNode.getNext();
			}
		}
		s.append("]");
		return s.toString();
	}

	@Override
	public boolean addNodes(List<INode<T>> nodes) {
		boolean isSuccess = false;
		if (null != nodes && !nodes.isEmpty()) {
			for (INode<T> node : nodes) {
				this.addNode(node);
			}
		}
		return isSuccess;
	}
}
