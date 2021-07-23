package 实现_常规数组集合;

import 公共_接口与抽象类.List;
import 公共_接口与抽象类.AbstractList;

@SuppressWarnings("unchecked")
public class ArraySimpleList<E> extends AbstractList<E> implements List<E> {
	public ArraySimpleList(){
		super(DEFAULT_CAPACITY);
	}
	public ArraySimpleList(int initCapacity) {
		super(initCapacity);
	}
	
	/****** (重点)扩展容量  ******/
	private void increaseCapacity(){
		if(size == elements.length){
			// 新数组长度为原数组长度的1.5倍
			int length = elements.length + (elements.length >> 1);
			E[] newElements = (E[]) new Object[length];
			for(int i=0; i<elements.length; i++){
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}
	
	/****** (重点)缩小容量  ******/
	private void decreaseCapacity(){
		// 新数组为原数组的一半组长
		int length = elements.length >> 1;
		if(length > size && elements.length != DEFAULT_CAPACITY){
			E[] newElements = null;
			if(length < DEFAULT_CAPACITY){
				newElements = (E[]) new Object[DEFAULT_CAPACITY];
			}else{
				newElements = (E[]) new Object[length];
				for(int i=0; i < size; i++){
					newElements[i] = elements[i];
				}
			}
			elements = newElements;
		}
	}
	
	/*****  (重点)根据指定数组索引将元素插入到数组中  *****/
	@Override
	public boolean insEle(int index, E element) {
		super.rangeCheck(index);
		// 调用扩容方法, 确保具有足够的空间进行插入位移操作
		this.increaseCapacity();
		// (位移)插入新元素之前, 将 index 位置元素之后的所有元素都后移一位, 从后向前遍历, 腾出指定位置的空间
		for (int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}
		elements[index] = element;
		size++;
		return elements[index] == element;
	}
	
	/*****  (重点)根据指定数组索引将元素从数组中删除   *****/
	@Override
	public E delIdx(int index) {
		super.rangeCheck(index);
		E oldEle = elements[index];
		// (位移)删除元素, 就是将 index 位置元素之后的所有元素都迁移一位, 从前向后遍历, 用后一位覆盖前一位
		for(int i=index; i < size-1; i++){
			elements[i] = elements[i+1];
		}
		size--;
		// 删除元素后, 调用缩容方法, 可以减少数组过多的空间
		this.decreaseCapacity();
		return oldEle;
	}
	
	@Override // 替换 index 的元素
	public E setEle(int index, E element) {
		super.rangeCheck(index);
		E oldEle = elements[index];
		elements[index] = element;
		return oldEle;
	}
	
	@Override // 获取index的元素
	public E getEle(int index) {
		super.rangeCheck(index);
		return elements[index];
	}
	
	@Override // 获取元素位置
	public int indexOf(E element) {
		for (int i = 0; i < size; i++) {
			if(elements[i].equals(element))
				return i;
		}
		return HAVE_NOT_ELEMENT;
	}
}
