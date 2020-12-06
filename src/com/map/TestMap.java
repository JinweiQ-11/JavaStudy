package com.map;
/**
 * ʵ��map
 * @author 86188
 *
 */
class Node2{
	int hash;
	Object key;
	Object value;
	Node2 next;
}

public class TestMap {
	
	Node2[] table;//λͰ����;
	int size;
	public TestMap() {
		table = new Node2[16];
		
	}
	public void put(Object key,Object value) {
		Node2 newNode = new Node2();
		newNode.hash = myHash(key.hashCode(),table.length);
		newNode.key = key;
		newNode.value = value;
		newNode.next = null;
		Node2 tempNode2 = table[newNode.hash];
		Node2 iterListNode2 = null;
		boolean keyRe=false;
		if(tempNode2==null) {
			table[newNode.hash] = newNode;
		}else {
			//�����б�
			while(tempNode2!=null) {
				//key�ظ�
				if(tempNode2.key.equals(key)) {
					keyRe = true;
					System.out.println("�ظ�");
					tempNode2.value = value;
					break;
				}else{
					iterListNode2 = tempNode2;
					tempNode2 = tempNode2.next;
				}
				//key���ظ�
				
			}
			if(!keyRe) {
				iterListNode2.next = newNode;
			}
			
		}
	}
	
	public Object get(Object key) {
		int hash = myHash(key.hashCode(), table.length);
		Object valueObject = null;
		if(table[hash]!=null) {
			Node2 temp = table[hash];
			while(temp!=null) {
				if(temp.key.equals(key)) {
					valueObject = temp.value;
					break;
				}else {
					temp = temp.next;
				}
			}
		}
		
		return valueObject;
	}
	public int myHash(int v,int length) {
		System.out.println("myhashcodek="+(v&(length-1)));
		return v&(length-1);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder("{");
		for(int i=0;i<table.length;i++) {
			Node2 temp = table[i];
			while(temp!=null) {
				sb.append(temp.key+":"+temp.value+",");
				temp = temp.next;
				
			}
			
		}
		sb.setCharAt(sb.length()-1, '}');
		return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMap map= new TestMap();
		map.put(10,"10");
		map.put(13,"14");
		System.out.println(map);
		System.out.println(map.get(10));
	}

}
