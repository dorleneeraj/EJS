package data.structure.linkedlist;

public class DNode<T> implements INode<T> {

	protected T value;
	protected INode<T> Next;
	protected INode<T> Previous;

	public DNode(T value) {
		this.value = value;
		this.Next = null;
		this.Previous = null;
	}

	@Override
	public T getData() {
		return this.value;
	}

	public INode<T> getNext() {
		return Next;
	}

	public void setNext(INode<T> next) {
		Next = next;
	}

	public INode<T> getPrevious() {
		return Previous;
	}

	public void setPrevious(INode<T> previous) {
		Previous = previous;
	}

	@Override
	public String toString() {
		return "DNode [value=" + value + "]";
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
		DNode<T> other = (DNode<T>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
