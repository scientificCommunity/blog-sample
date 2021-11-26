## 浅析hashCode

### hashCode是什么？

首先，`hashCode`在计算机领域指的是一个数据经过`hash funcation`后得到的一个摘要，而这个摘要可以作为索引应用到`hash map`中去。接下来我们聊聊`hashCode`在`java`中是什么样的。

`hashCode`是`Java.lang.Object`定义的一个`native`方法，默认返回的是一个由对象的内存地址转换而来的一个数值。

在Object的子类中会有一些其他的实现，比如在`Java.lang.String`中，`hashCode`返回的是这个字符串每个字符的`unicode`码类乘跟累加混合运算得出来的一个数值。

又比如在`Java.lang.Integer`中，`hashCode`返回的就是这个`Integer`的值

不管怎么去实现`hashCode`，`hashCode`都必须遵守以下约定：

1. 同一个对象返回的`hashCode`必须保持不变
2. 两个对象如果通过`equals`判断是相等的，那么`hashCode`也必须相等

对于通过`equals`判断为不相等的对象并没有强制要求`hashCode`也不相等，但是，为这类不相等的对象产生不同的`hashCode`可以提升操作哈希表的性能。

就拿`HashMap`来说，其`put`方法有这样一个判断：

```java
if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
```

如果，equals判断两个对象不相等，但是这两个对象的hashCode一样，那么上面这个if里所有的判断都会执行一遍，这显然会造成没必要的性能损耗。

### Java里为什么要有hashCode这个函数？

在`java`里，`hashCode`是实现`Java.util.HashMap`等`hash`类数据结构必不可少的一个组成部分。这类数据结构需要用`hashCode`来定位数据在`hash map`中的位置。比如，在`HashMap#put`中，有这么一段代码：

```java
if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
```

PS:上面代码中的hash是对象的`hashCode`经过特殊处理后的一个`int`类型的值。这个特殊处理主要是将高位的影响向低位传播，这样就不会导致往一些`size`比较小的`HashMap`里插入数据时，无论高位(超过size的部分)怎么变，最终都会定位到同一个位置的问题。总而言之，这个特殊处理是为了降低`hash`冲突发生的概率。

可以看出，`hashCode`被用来定位`key`在`tab`这个数组中的位置。

另外，hashCode由于自身的一些特性，`hashCode`这个函数通常会将第一次调用计算出来的结果存储下来，这样以后每次调用就会直接返回这个值。所以，其实`hashCode`这个函数的调用所产生的计算量是很少的。这就牵扯到`HashMap#put`的一个小细节：

```java
if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
```

上面这段代码用来在发生`hash`碰撞时判断即将插入的`key`是否跟`hash map`中已有的的`key`是同一个。可以看到，这段代码首先就是比较两个`key`的`hash`是否相等。只有hash相等时才会进入后面的比较，首先会比较引用地址是否相等，不等时才最终执行`equals()`方法进行等价关系判断。所以优化体现在哪呢？

`p.hash == hash`这段代码是需要`hashCode`做支撑的，相对后面的`equals()`来说，它的执行成本更低。这个更低一是因为方法执行执行需要做一些额外的操作，比如栈帧出栈入栈，程序计数器的跳转等。另一方面，像`Java.lang.String`这个类的`equals`方法，它是通过字符逐个遍历来进行比对的，这个性能就更低了。

### 一些有意思的点

在`Java.lang.String#hashCode`的实现中，有下面这么一段代码：

```java
h = 31 * h + val[i];
```

我当时看到这段代码有两个疑问：

- 为什么这里要乘以一个31呢？
- 别的数不可以吗？

关于这段代码，在Joshua Bloch的[《Effective Java》](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997/ref=sr_1_1?crid=1IA4SK3ZNSGUW&keywords=effective+java+3rd+edition&qid=1637915613&sprefix=Effective+Java+3%2Cstripbooks-intl-ship%2C403&sr=8-1) 这本书里有这么一段话(第三章第十一节)：

> The value 31 was chosen because it is
an odd prime. If it were even and the multiplication overflowed, information
would be lost, because multiplication by 2 is equivalent to shifting. The advantage
of using a prime is less clear, but it is traditional. A nice property of 31 is that the
multiplication can be replaced by a shift and a subtraction for better performance
on some architectures: **31 * i == (i << 5) - i**. Modern VMs do this sort of optimization automatically.
> 

这里可以解答第一个问题。首先，选择31是因为它既是素数又是奇数。因为一个数乘以一个奇数在乘法溢出时不会导致信息丢失(java里会变成减法)。又因为`31 * i = (i << 5) - i` ，位移+减法比乘法有着更好的性能。

关于大佬说的第二点好处，我也不是很理解，因为乘除都可以写成位移加上加减运算，区别在于位移的位数以及加减的次数。所以，我觉得应该是31转换乘位移加减法后，减法只需要执行一次即可。性能点应该在于加减执行的次数。那么，关于上面的疑问2，也就有了答案，只要一个数同时满足素数跟奇数，就行。

### 总结

1. `hashCode`是`hashMap`的实现中必不可少的一部分，同时呢，`hashCode`也间接参与到了`hashMap`内部的一些优化操作中。
2. `hashCode`跟`equals`具备两个约定跟一个建议，约定决定了与`hashCode`相关的代码(比如`hashMap`)的执行结果的正确性，而建议决定了执行的性能