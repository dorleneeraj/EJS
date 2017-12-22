package data.structure.linkedlist;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author neeraj
 *
 * @param <T>
 */
public interface ILinkedList<T> extends Iterable<Integer> {
	/**
	 * <p>
	 * Gets the reference to the head of the Linked List
	 * </p>
	 * 
	 * @return INode<T>: Head of the List. NULL: If the List is empty or
	 *         un-initialized.
	 */
	INode<T> getHead();

	/**
	 * <p>
	 * Gets the reference to the tail of the Linked List.
	 * </p>
	 * 
	 * @return INode<T> : Tail of the list. NULL: If the list is empty or
	 *         un-initialized.
	 */
	INode<T> getTail();

	/**
	 * <p>
	 * Returns the length of the LinkedList.
	 * </p>
	 * 
	 * @return {@link Integer}
	 */
	Integer getLength();

	/**
	 * <p>
	 * Adds a node {@link INode} to the tail of the Linked List.
	 * </p>
	 *
	 * @param {@link INode} The node that needs to be added.
	 * @return {@link Boolean} Returns if the operation was successful or not
	 */
	boolean addNode(INode<T> node); // By default adds a node to the tail.

	/**
	 * <p>
	 * Adds a node {@link INode} to the list at the mentioned index. Index of
	 * the linked list starts from 1.
	 * </p>
	 * 
	 * @param node
	 * @param index
	 * @return {@link Boolean} Returns if the operation was successful or not
	 */
	boolean addNode(INode<T> node, int index);

	boolean addNodes(List<INode<T>> nodes);

	/**
	 * <p>
	 * By default removes the node from the list from the tail.
	 * </p>
	 * 
	 * @return {@link INode}: Removes the node and returns the value. Returns
	 *         Null if the operation was not successful
	 */
	INode<T> removeNode(); // Removes a Node from

	/**
	 * <p>
	 * Removes the node from the mentioned Index. Returns the node.
	 * </p>
	 * 
	 * @param index
	 *            <p>
	 *            The index from which the node needs to be removed.
	 *            </p>
	 * @return {@link INode}
	 */
	INode<T> removeNode(int index);

	/**
	 * <p>
	 * Removes the given node from the List if that node is present.
	 * </p>
	 * 
	 * @param node
	 *            The node that needs to be removed.
	 * @return {@link INode} Returns the removed node. Returns null if the
	 *         operation is unsuccessful.
	 */
	INode<T> removeNode(INode<T> node);

	/**
	 * <p>
	 * Returns the index of the node if it is present in the LinkedList
	 * </p>
	 * 
	 * @param node
	 *            The node that needs to be checked present.
	 * @return {@link Integer}
	 */
	Integer contains(INode<T> node);

	/**
	 * <p>
	 * Returns if the list is empty or not.
	 * </p>
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * <p>
	 * Returns the sorted list.
	 * </p>
	 * 
	 * @return {@link ILinkedList}
	 */
	ILinkedList<T> sortList();

	/**
	 * <p>
	 * Returns the reversed list
	 * </p>
	 * 
	 * @return {@link ILinkedList}
	 */
	ILinkedList<T> reverseList();
}
