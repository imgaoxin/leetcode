/*
 * @lc app=leetcode.cn id=622 lang=java
 *
 * [622] 设计循环队列
 *
 * https://leetcode-cn.com/problems/design-circular-queue/description/
 *
 * algorithms
 * Medium (38.79%)
 * Likes:    63
 * Dislikes: 0
 * Total Accepted:    14.9K
 * Total Submissions: 37.9K
 * Testcase Example:  '["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]\n[[3],[1],[2],[3],[4],[],[],[],[4],[]]'
 *
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于
 * FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 
 * 
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * 
 * 你的实现应该支持如下操作：
 * 
 * 
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * MyCircularQueue circularQueue = new MycircularQueue(3); // 设置长度为 3
 * 
 * circularQueue.enQueue(1);  // 返回 true
 * 
 * circularQueue.enQueue(2);  // 返回 true
 * 
 * circularQueue.enQueue(3);  // 返回 true
 * 
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * 
 * circularQueue.Rear();  // 返回 3
 * 
 * circularQueue.isFull();  // 返回 true
 * 
 * circularQueue.deQueue();  // 返回 true
 * 
 * circularQueue.enQueue(4);  // 返回 true
 * 
 * circularQueue.Rear();  // 返回 4
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 * 
 * 
 */

// @lc code=start
class MyCircularQueue {
    private int capcity; // array values length
    // private int size;
    private int readIndex; // head index
    private int writeIndex; // tail index
    private int[] values;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        capcity = k + 1;
        values = new int[capcity];
        readIndex = 0;
        writeIndex = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        values[writeIndex] = value;
        writeIndex = ++writeIndex % capcity;

        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        readIndex = ++readIndex % capcity;

        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : values[readIndex];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        // int last = writeIndex != 0 ? writeIndex - 1 : capcity - 1;
        return isEmpty() ? -1 : values[(writeIndex + capcity - 1) % capcity];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return readIndex == writeIndex;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (readIndex + capcity - writeIndex) % capcity == 1;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
// @lc code=end

