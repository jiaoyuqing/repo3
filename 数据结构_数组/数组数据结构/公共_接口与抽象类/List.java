package 公共_接口与抽象类;

public interface List<E> {
	/** 
	 * 循环数组和列表数组各自实现的方法
	 * 因为数组索引位置不同, 因此需要各自实现
	 */
	// 插入新元素
	public boolean insEle(int index, E element);
	// 删除指定下表的元素
	public E delIdx(int index);
	// 置换新元素
	public E setEle(int index, E element);
	// 获取索引位置的的元素
	public E getEle(int index);
	// 获取元素位置的的索引
	public int indexOf(E element);
	
	/** 
	 *  数组数据结构通用方法
	 *  循环数组和列表数组对元素的操作相同, 因此是通用方法
	 **/
	// 添加新元素
	public boolean addEle(E element);
	// 删除指定的全部元素
	public boolean delEle(E element);
	// 是否包含
	public boolean contains(E element);
	// 是否为空
	public boolean isEmpty();
	// 清空数组
	public void clear();
	// 返回大小
	public int size();
}
