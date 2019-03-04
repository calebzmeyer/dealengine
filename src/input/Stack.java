package input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack<T> implements Iterable<T> {

	private List<T> stack;
	
	public Stack(){
		this.stack = new ArrayList<T>();
	}
	
	public void push(T object) {
		this.stack.add(object);
	}
	
	public T peek() {
		if(this.size() == 0) return null;
		return this.stack.get(this.stack.size() - 1);
	}
	
	public T pop() {
		return this.stack.remove(this.stack.size() - 1);
	}
	
	public boolean isEmpty() {
		if(this.stack.size() == 0) return true;
		return false;
	}
	
	public int size() {
		return this.stack.size();
	}

	@Override
	public Iterator<T> iterator() {
		return this.stack.iterator();
	}
}