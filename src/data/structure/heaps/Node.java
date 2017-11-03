package data.structure.heaps;

/**
 * 
 * @author neeraj
 *
 * @param <T>
 * @param <K>
 */
public class Node<T, K> {
	private T data;
	private K key; // The Heap property is maintained using this attribute

	public Node(T data, K key) {
		this.data = data;
		this.key = key;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", key=" + key + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		Node<T, K> other = (Node<T, K>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
