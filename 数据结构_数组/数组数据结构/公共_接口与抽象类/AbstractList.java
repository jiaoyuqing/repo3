package 公共_接口与抽象类;

@SuppressWarnings("unchecked")
public abstract class AbstractList<E> implements List<E> {
	protected static final int DEFAULT_CAPACITY = 10;
	protected static final int HAVE_NOT_ELEMENT = -1;
	// 存储元素的数组
	protected E[] elements = null; 
	// 存储元素的数量
	protected int size = 0;
	
	public AbstractList(int initCapacity) {
		if(initCapacity < DEFAULT_CAPACITY){
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		}else{
			elements = (E[]) new Object[initCapacity];
		}
	}
	
	@Override // 在数组尾部添加新元素
	public boolean addEle(E element) {
		return insEle(size, element);
	}
	
	@Override // 从数组中删除指定元素
	public boolean delEle(E element) {
		return delIdx(indexOf(element)) != element;
	}
		
	@Override // 判断数组中是否存在元素
	public boolean contains(E element) {
		return indexOf(element) != HAVE_NOT_ELEMENT;
	}
	
	@Override // 返回数组已存储元素数量
	public int size() {
		return size;
	}
	
	@Override // 判断数组是否为空
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override // 清空数组
	public void clear() {
		size = 0;
	}
	
	/** 判断指定的index是否超过数组的合法范围, 其合法范围为 0 到 size **/
	protected void rangeCheck(int index){
		if(index < 0 || index > size){
			throw new ArrayIndexOutOfBoundsException("size:" + size + ", index" + index);
		}
	}
}
