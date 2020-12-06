package com.map;
class Node{
	Node previousNode;
	Node nextNode;
	Object elementObject;
	public Node(Node previousNode, Node nextNode, Object elementObject) {
		this.previousNode = previousNode;
		this.nextNode = nextNode;
		this.elementObject = elementObject;
	}
	public Node(Object elementObject) {
		this.elementObject = elementObject;
	}
	
}
public class txtLinkedList {
	
	private Node first;
	private Node last;
	private int  size;
	public void add(Object obj) {
		Node node = new Node(obj);
		if(first==null) {
			//node.previousNode = null;
			
			first = node;
			last = node;
		}else {
			node.previousNode=last;
			node.nextNode = null;
			last.nextNode = node;
			last = node;
		}
		size++;
	}
	
	public Object get(int index) {
		if(index<0||index>size-1) {
			throw new RuntimeException("�������Ϸ�");
		}
		Node tempNode = getNode(index);
		return tempNode!=null?tempNode.elementObject:null;
	}
	
	
	public Node getNode(int index) {
		Node tempNode = null;
		if(index <= (size>>1)) {
			tempNode = first;
			for(int i=0;i<index;i++) {
				tempNode = tempNode.nextNode;
			}
		}else {
			tempNode = last;
			for(int i=size-1;i>index;i--) {
				tempNode = tempNode.previousNode;
			}
		}
		return tempNode;
	}
	public void remove(int index) {
		Node tempNode = getNode(index);
		if(tempNode!=null) {
			Node upNode = tempNode.previousNode;
			Node down = tempNode.nextNode;
		if(upNode!=null) {
			upNode.nextNode = down;
		}
		if(down!=null) {
			down.previousNode = upNode;
		}
		if(index==0) {
			first = down;
		}
		if(index==size-1) {
			last = upNode;
		}
		size--;
		}
		
	}
	//������λ����
	public void add(int index,Object obj) {
		Node newNode = new Node(obj);
		Node tempNode = getNode(index);
		if(tempNode!=null) {
			Node up = tempNode.previousNode;
			up.nextNode = newNode;
			newNode.previousNode = up;
			newNode.nextNode = tempNode;
			tempNode.previousNode = newNode;
		}
	}
	
	public String toString() {
	//[a,b,c]
		// TODO Auto-generated method stub
		StringBuilder sb= new StringBuilder();
		sb.append("[");
	    Node temp = first;
	    while(temp!=null) {
	    	//System.out.println(temp.elementObject);
	    	sb.append(temp.elementObject+",");
	    	temp = temp.nextNode;
	    }
		sb.setCharAt(sb.length()-1, ']');
		return sb.toString();
	}
	public static void main(String args[]) {
		txtLinkedList List = new txtLinkedList();
		List.add("1");
		List.add("2");
		List.add("3");
		List.add("4");
		List.add("5");
		List.add("6");
		System.out.println(List);
		List.remove(3);
		System.out.println(List);
		List.add(3,"qin");
		System.out.println(List);
	}
}
