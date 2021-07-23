package 实现_循环数据集合;

import 公共_接口与抽象类.List;
import 公共_接口与抽象类.AbstractList;

@SuppressWarnings("unchecked")
public class ArrayCircleList<E> extends AbstractList<E> implements List<E> {
	/***** (核心)循环数组头元素的索引位置 *****/
	private int beginIndex = 0;
	public ArrayCircleList(){
		super(DEFAULT_CAPACITY);
	}
	public ArrayCircleList(int initCapacity){
		super(initCapacity);
	}
	
	/***** (核心)确定循环数组中真实的索引位置 *****/
	private int exactElementIndex(int index){
		// 头元素及后面所有元素空间数量
		int beginRightLength = elements.length - beginIndex;
		if(index < beginRightLength){
			// 头元素位置后的真实元素索引位置 
			return beginIndex + index;
		} else {
			// 头元素位置前的真实元素索引位置
			return index - beginRightLength;
		}
	}
	
	/****** (重点)扩展容量  ******/
	private void increaseCapacity(){
		if(size == elements.length){
			int length = elements.length + (elements.length >> 1);
			E[] newElements = (E[]) new Object[length];
			// (位移)将原数组从头元素索引位置开始, 将头元素之后的元素存入新数组前部, 将头元素之前的元素存入新数组后部
			for(int i = 0; i < size; i++){
				newElements[i] = elements[exactElementIndex(i)];
			}
			beginIndex = 0;
			elements = newElements;
		}
	}
		
	/****** (重点)缩小容量  ******/
	private void decreaseCapacity(){
		int length = elements.length >> 1;
		if(length > size && elements.length != DEFAULT_CAPACITY){
			E[] newElements = null;
			if(length < DEFAULT_CAPACITY){
				newElements = (E[]) new Object[DEFAULT_CAPACITY];
			}else{
				newElements = (E[]) new Object[length];
			}
			// (位移)将原数组从头元素索引位置开始, 将头元素之后的元素存入新数组前部, 将头元素之前的元素存入新数组后部
			for(int i = 0; i <= size; i++){
				newElements[i] = elements[exactElementIndex(i)];
			}
			beginIndex = 0;
			elements = newElements;
		}
	}
	
	/*****  (重点)根据指定数组索引将元素插入到数组中  *****/
	@Override
	public boolean insEle(int index, E element) {
		rangeCheck(index);
		increaseCapacity();
		// 插入向后位移, 需要使用循环数组的真实索引位置进行位移
		for (int i = size; i > index; i--) {
			elements[exactElementIndex(i)] = elements[exactElementIndex(i-1)];
		}
		elements[exactElementIndex(index)] = element;
		size++;
		return false;
	}
	
	/*****  (重点)根据指定数组索引将元素从数组中删除   *****/
	@Override
	public E delIdx(int index) {
		rangeCheck(index);
		E oldEle = elements[exactElementIndex(index)];
		if(elements[beginIndex] == oldEle){
			if(beginIndex == elements.length -1) {
				beginIndex = 0;
			}
			beginIndex++;
		}else{
			// 删除元素向前位移来覆盖, 需要使用循环数组的真实索引位置进行位移
			for (int i = index; i < size-1; i++) {
				elements[exactElementIndex(i)] = elements[exactElementIndex(i+1)];
			}
		}
		size--;
		decreaseCapacity();
		return oldEle;
	}

	@Override // 替换 index 的元素
	public E setEle(int index, E element) {
		rangeCheck(index);
		E oldEld = elements[exactElementIndex(index)];
		elements[exactElementIndex(index)] = element;
		return oldEld;
	}

	@Override // 获取index的元素
	public E getEle(int index) {
		rangeCheck(index);
		return elements[exactElementIndex(index)];
	}
	
	@Override // 获取元素位置
	public int indexOf(E element) {
		for (int i = 0; i < size; i++) {
			// (重点)使用获取数组中的元素位置之后, 再进行计算
			if(elements[i].equals(element)) {
				if(i >= beginIndex){
					return i - beginIndex;
				}else{
					return i + size - beginIndex;
				}
			}
		}
		return HAVE_NOT_ELEMENT;
	}
}
