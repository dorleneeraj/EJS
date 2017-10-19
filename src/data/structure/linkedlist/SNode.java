package data.structure.linkedlist;

public class SNode<T> implements INode<T> {
	protected T value;
	protected INode<T> Next;

	public SNode(T value) {
		this.value = value;
		this.Next = null;
	}

	@Override
	public T getData() {
		return null;
	}

	public INode<T> getNext() {
		return Next;
	}

	public void setNext(INode<T> next) {
		this.Next = next;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SNode other = (SNode) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value.toString() + " ";
	}

}
