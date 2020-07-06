[toc]

## shell

### 第一个shell脚本

```shell
#!/bin/bash
echo "Hello World !"
```

chmod +x ./test.sh  #使脚本具有执行权限
./test.sh  #执行脚本

**#!** 是一个约定的标记，它告诉系统这个脚本需要什么解释器来执行，即使用哪一种 Shell。echo 命令用于向窗口输出文本。一定要写成 **./test.sh**，而不是 **test.sh**，运行其它二进制的程序也一样，直接写 test.sh，linux 系统会去 PATH 里寻找有没有叫 test.sh 的，而只有 /bin, /sbin, /usr/bin，/usr/sbin 等在 PATH 里，你的当前目录通常不在 PATH 里，所以写成 test.sh 是会找不到命令的，要用 ./test.sh 告诉系统说，就在当前目录找。

### shell变量

==变量和等号间不能有空格==

```shell
your_name="zzz.com"
```

还可以用语句给变量赋值

```shell
for file in `ls /etc`
#或
for file in $(ls /etc)
```

使用示例：

```shell
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill}Script"
done
```



##### 只读变量

```shell
#!/bin/bash
myUrl="https://www.google.com"
readonly myUrl
myUrl="https://www.zzz.com"
```

##### 删除变量

使用unset可以删除变量，但不得=能删除只读变量

```shell
unset zzz_name
```



##### 变量类型

运行shell时，会同时存在三种变量：

1. 局部变量： 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量。
2. **环境变量** 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量。
3. **shell变量** shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行



##### shell字符串

字符串是shell编程中最常用最有用的数据类型（除了数字和字符串，也没啥其它类型好用了），字符串可以用单引号，也可以用双引号，也可以不用引号。单双引号的区别跟PHP类似。

###### 单引号

```shell
str='this is a string'
```

单引号字符串的限制：

* 单引号里的任何字符都会原样输出，单引号里的字符串变量是无效的
* 单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

###### 双引号

```shell
your_name='zzz'
str="Hello, I know you are \"$your_name\"! \n"
echo -e $str
```



###### 获取字符串长度

```shell
str="abcdef"
echo ${#str}
```

###### 提取字符串

```shell
str="zzz is god nb"
echo ${str:1:4} # 输出 zz i
```

###### 查找字符串

查找字符 i 或 o 的位置，哪个先出现就计算哪个

```shell
string="zzz is a great site oo"
echo `expr index "$string" io`  # 输出 5
```

##### shell数组

bash支持一维数组（不支持多维数组），并且没有限定数组的大小。数组元素的下标由 0 开始编号。获取数组中的元素要利用下标，下标可以是整数或算术表达式，其值应大于或等于 0。

###### 定义数组

```shell
array_name=(value0 value1 value2 value3)
#或
array_name=(
value0
value1
value2
value3
)

#或
array_name[0]=value0
array_name[1]=value1
array_name[n]=valuen
```

###### 读取数组

${数组名[下标]}

```shell
valuen=${array_name[n]}
#使用 @ 符号可以获取数组中的所有元素
echo ${array_name[@]}
```

###### 获取数组的长度

```shell
# 取得数组元素的个数
length=${#array_name[@]}
# 或者
length=${#array_name[*]}
# 取得数组单个元素的长度
lengthn=${#array_name[n]}
```



##### shell注释

###### 单行注释

单行注释：以 **#** 开头的行就是注释，会被解释器忽略。通过每一行加一个 **#** 号设置多行注释。

###### 多行注释

1. 可以把这一段要注释的代码用一对花括号括起来，定义成一个函数，没有地方调用这个函数，这块代码就不会执行，达到了和注释一样的效果。

2. ```shell
   :<<EOF
   注释内容...
   注释内容...
   注释内容...
   EOF
   #EOF 也可以使用其他符号:  
   :<<'
   注释内容...
   注释内容...
   注释内容...
   '
   #或
   :<<!
   注释内容...
   注释内容...
   注释内容...
   !
   ```

   

### shell传递参数

我们可以在执行 Shell 脚本时，向脚本传递参数，脚本内获取参数的格式为：**$n**。**n** 代表一个数字，1 为执行脚本的第一个参数，2 为执行脚本的第二个参数。

```shell
#!/bin/bash
# author:zzz
# url:www.zjdnb.com

echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第二个参数为：$2";
echo "第三个参数为：$3";
```

```
$ chmod +x test.sh 
$ ./test.sh 1 2 3
Shell 传递参数实例！
执行的文件名：./test.sh
第一个参数为：1
第二个参数为：2
第三个参数为：3
```

| 参数处理 |                             说明                             |
| -------- | :----------------------------------------------------------: |
| $#       |                     传递到脚本的参数个数                     |
| $*       | 以一个单字符串显示所有向脚本传递的参数。<br/>如"$*"用「"」括起来的情况、以"$1 $2 … $n"的形式输出所有参数。 |
| $$       |                    脚本运行的当前进程ID号                    |
| $!       |                 后台运行的最后一个进程的ID号                 |
| $@       | 与$*相同，但是使用时加引号，并在引号中返回每个参数。<br/>如"$@"用「"」括起来的情况、以"$1" "$2" … "$n" 的形式输出所有参数 |
| $-       |         显示Shell使用的当前选项，与set命令功能相同。         |
| $?       | 显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。 |

```
$* 与 $@ 区别:只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，，则 " * " 等价于 "1 2 3"（传递了一个参数），而 "@" 等价于 "1" "2" "3"（传递了三个参数）。
```

### shell运算符

* 算数运算符
* 关系运算符
* 布尔运算符
* 字符串运算符
* 文件测试运算符

##### 算数运算符

原生bash不支持简单的数学运算，但是可以通过其他命令来实现，例如 awk 和 expr，expr 最常用。expr 是一款表达式计算工具，使用它能完成表达式的求值操作。

例如，两个数相加(**注意使用的是反引号 ` 而不是单引号 '**)：

```shell
#!/bin/bash

val=`expr 2 + 2`
echo "两数之和为 : $val"
```



==注意==

- 表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2
- 完整的表达式要被 **` `** 包含，注意这个字符不是常用的单引号，在 Esc 键下边。假设a为10 ,b为20

| 运算符 | 说明                                          | 举例                          |
| :----- | :-------------------------------------------- | :---------------------------- |
| +      | 加法                                          | `expr $a + $b` 结果为 30。    |
| -      | 减法                                          | `expr $a - $b` 结果为 -10。   |
| *      | 乘法                                          | `expr $a \* $b` 结果为  200。 |
| /      | 除法                                          | `expr $b / $a` 结果为 2。     |
| %      | 取余                                          | `expr $b % $a` 结果为 0。     |
| =      | 赋值                                          | a=$b 将把变量 b 的值赋给 a。  |
| ==     | 相等。用于比较两个数字，相同则返回 true。     | [ \$a == $b ] 返回 false。    |
| !=     | 不相等。用于比较两个数字，不相同则返回 true。 | [ \$a != $b ] 返回 true。     |

##### 关系运算符

关系运算符只支持数字，不支持字符串，除非字符串的值是数字。（假设a为10 ,b为20）

| 运算符 | 说明                                                  | 举例                        |
| :----- | :---------------------------------------------------- | :-------------------------- |
| -eq    | 检测两个数是否相等，相等返回 true。                   | [ \$a -eq $b ] 返回 false。 |
| -ne    | 检测两个数是否不相等，不相等返回 true。               | [ \$a -ne $b ] 返回 true。  |
| -gt    | 检测左边的数是否大于右边的，如果是，则返回 true。     | [ \$a -gt $b ] 返回 false。 |
| -lt    | 检测左边的数是否小于右边的，如果是，则返回 true。     | [ \$a -lt $b ] 返回 true。  |
| -ge    | 检测左边的数是否大于等于右边的，如果是，则返回 true。 | [ \$a -ge $b ] 返回 false。 |
| -le    | 检测左边的数是否小于等于右边的，如果是，则返回 true。 | [ \$a -le $b ] 返回 true。  |



##### 布尔运算符

假设a为10 ,b为20

| 运算符 | 说明                                                | 举例                                      |
| :----- | :-------------------------------------------------- | :---------------------------------------- |
| !      | 非运算，表达式为 true 则返回 false，否则返回 true。 | [ ! false ] 返回 true。                   |
| -o     | 或运算，有一个表达式为 true 则返回 true。           | [ \$a -lt 20 -o $b -gt 100 ] 返回 true。  |
| -a     | 与运算，两个表达式都为 true 才返回 true。           | [ \$a -lt 20 -a $b -gt 100 ] 返回 false。 |



##### 逻辑运算符

假设a为10 ,b为20

| 运算符 | 说明       | 举例                                        |
| :----- | :--------- | :------------------------------------------ |
| &&     | 逻辑的 AND | [[ \$a -lt 100 && $b -gt 100 ]] 返回 false  |
| \|\|   | 逻辑的 OR  | [[ \$a -lt 100 \|\| $b -gt 100 ]] 返回 true |

##### 字符串运算符

假设a 为 "abc"，变量 b 为 "efg"

| 运算符 | 说明                                         | 举例                      |
| :----- | :------------------------------------------- | :------------------------ |
| =      | 检测两个字符串是否相等，相等返回 true。      | [ \$a = $b ] 返回 false。 |
| !=     | 检测两个字符串是否相等，不相等返回 true。    | [ \$a != $b ] 返回 true。 |
| -z     | 检测字符串长度是否为0，为0返回 true。        | [ -z $a ] 返回 false。    |
| -n     | 检测字符串长度是否不为 0，不为 0 返回 true。 | [ -n "$a" ] 返回 true。   |
| $      | 检测字符串是否为空，不为空返回 true。        | [ $a ] 返回 true。        |

##### 文件测试运算符

| 操作符  | 说明                                                         | 举例                      |
| :------ | :----------------------------------------------------------- | :------------------------ |
| -b file | 检测文件是否是块设备文件，如果是，则返回 true。              | [ -b $file ] 返回 false。 |
| -c file | 检测文件是否是字符设备文件，如果是，则返回 true。            | [ -c $file ] 返回 false。 |
| -d file | 检测文件是否是目录，如果是，则返回 true。                    | [ -d $file ] 返回 false。 |
| -f file | 检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。 | [ -f $file ] 返回 true。  |
| -g file | 检测文件是否设置了 SGID 位，如果是，则返回 true。            | [ -g $file ] 返回 false。 |
| -k file | 检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。  | [ -k $file ] 返回 false。 |
| -p file | 检测文件是否是有名管道，如果是，则返回 true。                | [ -p $file ] 返回 false。 |
| -u file | 检测文件是否设置了 SUID 位，如果是，则返回 true。            | [ -u $file ] 返回 false。 |
| -r file | 检测文件是否可读，如果是，则返回 true。                      | [ -r $file ] 返回 true。  |
| -w file | 检测文件是否可写，如果是，则返回 true。                      | [ -w $file ] 返回 true。  |
| -x file | 检测文件是否可执行，如果是，则返回 true。                    | [ -x $file ] 返回 true。  |
| -s file | 检测文件是否为空（文件大小是否大于0），不为空返回 true。     | [ -s $file ] 返回 true。  |
| -e file | 检测文件（包括目录）是否存在，如果是，则返回 true。          | [ -e $file ] 返回 true。  |

```shell
#!/bin/bash
# author:zzz
# url:www.zjd.com

file="/home/zzz/sh/demo.sh"
if [ -r $file ]
then
   echo "文件可读"
else
   echo "文件不可读"
fi
if [ -w $file ]
then
   echo "文件可写"
else
   echo "文件不可写"
fi
if [ -x $file ]
then
   echo "文件可执行"
else
   echo "文件不可执行"
fi
if [ -f $file ]
then
   echo "文件为普通文件"
else
   echo "文件为特殊文件"
fi
if [ -d $file ]
then
   echo "文件是个目录"
else
   echo "文件不是个目录"
fi
if [ -s $file ]
then
   echo "文件不为空"
else
   echo "文件为空"
fi
if [ -e $file ]
then
   echo "文件存在"
else
   echo "文件不存在"
fi
```



### 命令

#### echo

字符串输出

```shell
#显示普通字符串:
echo "It is a test"   echo It is a test
#显示转义字符
echo "\"It is a test\""
#显示变量 read用来接收执行时输入的变量
#!/bin/sh
read name 
echo "$name It is a test"
#显示换行  -e 开启转义
echo -e "OK! \n" 
echo "It is a test"
#显示不换行  -e 开启转义 \c 不换行
#!/bin/sh
echo -e "OK! \c" 
echo "It is a test"
#显示结果定向至文件
echo "It is a test" > myfile
#原样输出字符串，不进行转义或取变量(用单引号)
echo '$name\"'
#显示命令执行结果
echo `date`
```

#### printf

##### 语法:

```
printf  format-string  [arguments...]
```

- **format-string:** 为格式控制字符串
- **arguments:** 为参数列表。

```shell
#!/bin/bash
# author:zjd
# url:www.zjd.com
 
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
printf "%-10s %-8s %-4.2f\n" a 男 66.1234 
printf "%-10s %-8s %-4.2f\n" b 男 48.6543 
printf "%-10s %-8s %-4.2f\n" c 女 47.9876 
```

```
%s %c %d %f都是格式替代符
%-10s 指一个宽度为10个字符（-表示左对齐，没有则表示右对齐），任何字符都会被显示在10个字符宽的字符内，如果不足则自动以空格填充，超过也会将内容全部显示出来。
%-4.2f 指格式化为小数，其中.2指保留2位小数。
```

##### printf的转义序列

| 序列  | 说明                                                         |
| :---- | :----------------------------------------------------------- |
| \a    | 警告字符，通常为ASCII的BEL字符                               |
| \b    | 后退                                                         |
| \c    | 抑制（不显示）输出结果中任何结尾的换行字符（只在%b格式指示符控制下的参数字符串中有效），而且，任何留在参数里的字符、任何接下来的参数以及任何留在格式字符串中的字符，都被忽略 |
| \f    | 换页（formfeed）                                             |
| \n    | 换行                                                         |
| \r    | 回车（Carriage return）                                      |
| \t    | 水平制表符                                                   |
| \v    | 垂直制表符                                                   |
| \\    | 一个字面上的反斜杠字符                                       |
| \ddd  | 表示1到3位数八进制值的字符。仅在格式字符串中有效             |
| \0ddd | 表示1到3位的八进制值字符                                     |



#### test

- 数值测试
- 字符串测试
- 文件测试

##### 数值测试

| 参数 | 说明           |
| :--- | :------------- |
| -eq  | 等于则为真     |
| -ne  | 不等于则为真   |
| -gt  | 大于则为真     |
| -ge  | 大于等于则为真 |
| -lt  | 小于则为真     |
| -le  | 小于等于则为真 |

```shell
#代码中的 [] 执行基本的算数运算
num1=100
num2=100
if test $[num1] -eq $[num2]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi

a=5
b=6
result=$[a+b] # 注意等号两边不能有空格
echo "result 为： $result"
```

##### 字符串测试

| 参数      | 说明                     |
| :-------- | :----------------------- |
| =         | 等于则为真               |
| !=        | 不相等则为真             |
| -z 字符串 | 字符串的长度为零则为真   |
| -n 字符串 | 字符串的长度不为零则为真 |

```shell
num1="zjd"
num2="zjdnb"
if test $num1 = $num2
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi
```

##### 文件测试

| 参数      | 说明                                 |
| :-------- | :----------------------------------- |
| -e 文件名 | 如果文件存在则为真                   |
| -r 文件名 | 如果文件存在且可读则为真             |
| -w 文件名 | 如果文件存在且可写则为真             |
| -x 文件名 | 如果文件存在且可执行则为真           |
| -s 文件名 | 如果文件存在且至少有一个字符则为真   |
| -d 文件名 | 如果文件存在且为目录则为真           |
| -f 文件名 | 如果文件存在且为普通文件则为真       |
| -c 文件名 | 如果文件存在且为字符型特殊文件则为真 |
| -b 文件名 | 如果文件存在且为块特殊文件则为真     |

==Shell还提供了与( -a )、或( -o )、非( ! )三个逻辑操作符用于将测试条件连接起来，其优先级为："!"最高，"-a"次之，"-o"最低。==

```shell
cd /bin
if test -e ./notFile -o -e ./bash
then
    echo '至少有一个文件存在!'
else
    echo '两个文件都不存在'
fi
```

#### shell流程控制

##### 循环

###### if else

在sh/bash里，如果else分支没有语句执行，就不要写这个else。

```shell
if condition
then
    command1 
    command2
    ...
    commandN 
fi

if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi

#if else-if else
if condition1
then
    command1
elif condition2 
then 
    command2
else
    commandN
fi

#if test结合使用
num1=$[2*3]
num2=$[1+5]
if test $[num1] -eq $[num2]
then
    echo '两个数字相等!'
else
    echo '两个数字不相等!'
fi
```

```
末尾的fi就是if倒过来拼写
```



###### for循环

```shell
for var in item1 item2 ... itemN
do
    command1
    command2
    ...
    commandN
done

for var in item1 item2 ... itemN; do command1; command2… done;

for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
done

for str in 'This is a string'
do
    echo $str
done
```

```
当变量值在列表里，for循环即执行一次所有命令，使用变量名获取列表中的当前取值。命令可为任何有效的shell命令和语句。in列表可以包含替换、字符串和文件名。
in列表是可选的，如果不用它，for循环使用命令行的位置参数。
```

###### while循环

```shell
#!/bin/bash
int=1
while(( $int<=5 ))
do
    echo $int
    let "int++"
done


echo '按下 <CTRL-D> 退出'
echo -n '输入你最喜欢的网站名: '
while read FILM
do
    echo "是的！$FILM 是一个好网站"
done
```

```
 Bash let 命令，它用于执行一个或多个表达式，变量计算中不需要加上 $ 来表示变量
```

###### until 循环

```
until 循环执行一系列命令直至条件为 true 时停止。
until 循环与 while 循环在处理方式上刚好相反。
一般 while 循环优于 until 循环，但在某些时候—也只是极少数情况下，until 循环更加有用。
until condition
do
    command
done
condition 一般为条件表达式，如果返回值为 false，则继续执行循环体内的语句，否则跳出循环。
```

```shell
#!/bin/bash

a=0

until [ ! $a -lt 10 ]
do
   echo $a
   a=`expr $a + 1`
done
```

###### case

```
case 值 in
模式1)
    command1
    command2
    ...
    commandN
    ;;
模式2）
    command1
    command2
    ...
    commandN
    ;;
esac
case工作方式如上所示。取值后面必须为单词in，每一模式必须以右括号结束。取值可以为变量或常数。匹配发现取值符合某一模式后，其间所有命令开始执行直至 ;;。
取值将检测匹配的每一个模式。一旦模式匹配，则执行完匹配模式相应命令后不再继续其他模式。如果无一匹配模式，使用星号 * 捕获该值，再执行后面的命令。
```

```shell
echo '输入 1 到 4 之间的数字:'
echo '你输入的数字为:'
read aNum
case $aNum in
    1)  echo '你选择了 1'
    ;;
    2)  echo '你选择了 2'
    ;;
    3)  echo '你选择了 3'
    ;;
    4)  echo '你选择了 4'
    ;;
    *)  echo '你没有输入 1 到 4 之间的数字'
    ;;
esac
```

##### 跳出循环

###### break

```
break命令允许跳出所有循环（终止执行后面的所有循环）。
```

```shell
#!/bin/bash
while :
do
    echo -n "输入 1 到 5 之间的数字:"
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"
            break
        ;;
    esac
done
```

###### continue

```
continue命令与break命令类似，只有一点差别，它不会跳出所有循环，仅仅跳出当前循环。
```

```shell
#!/bin/bash
while :
do
    echo -n "输入 1 到 5 之间的数字: "
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的!"
            continue
            echo "游戏结束"
        ;;
    esac
done
```

###### case esac

```
case ... esac 与其他语言中的 switch ... case 语句类似，是一种多分枝选择结构，每个 case 分支用右圆括号开始，用两个分号 ;; 表示 break，即执行结束，跳出整个 case ... esac 语句，esac（就是 case 反过来）作为结束标记。
```

```shell
#!/bin/sh

site="type"

case "$site" in
   "baidu") echo "百度"
   ;;
   "google") echo "Google 搜索"
   ;;
   "taobao") echo "淘宝网"
   ;;
esac
```

### shell函数

##### 函数定义和返回值

1. 可以带function fun() 定义，也可以直接fun() 定义,不带任何参数。

2. 参数返回，可以显示加：return 返回，如果不加，将以最后一条命令运行结果，作为返回值。 return后跟数值n(0-255

3. 函数返回值在调用该函数后通过 $? 来获得。

4. 注意：所有函数在使用前必须定义。这意味着必须将函数放在脚本开始部分，直至shell解释器首次发现它时，才可以使用。调用函数仅使用其函数名即可。

   

```shell
#!/bin/bash
# author:zjd
# 无返回值

demoFun(){
    echo "这是我的第一个 shell 函数!"
}
echo "-----函数开始执行-----"
demoFun
echo "-----函数执行完毕-----"


funWithReturn(){
    echo "这个函数会对输入的两个数字进行相加运算..."
    echo "输入第一个数字: "
    read aNum
    echo "输入第二个数字: "
    read anotherNum
    echo "两个数字分别为 $aNum 和 $anotherNum !"
    return $(($aNum+$anotherNum))
}
funWithReturn
echo "输入的两个数字之和为 $? !"
```

###### 函数参数

==注意，\$10 不能获取第十个参数，获取第十个参数需要\${10}。当n>=10时，需要使用${n}来获取参数。==

```shell
#!/bin/bash
# author:zjd

funWithParam(){
    echo "第一个参数为 $1 !"
    echo "第二个参数为 $2 !"
    echo "第十个参数为 $10 !"
    echo "第十个参数为 ${10} !"
    echo "第十一个参数为 ${11} !"
    echo "参数总数有 $# 个!"
    echo "作为一个字符串输出所有参数 $* !"
}
funWithParam 1 2 3 4 5 6 7 8 9 34 73
```

| 参数处理 | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| $#       | 传递到脚本或函数的参数个数                                   |
| $*       | 以一个单字符串显示所有向脚本传递的参数                       |
| $$       | 脚本运行的当前进程ID号                                       |
| $!       | 后台运行的最后一个进程的ID号                                 |
| $@       | 与$*相同，但是使用时加引号，并在引号中返回每个参数。         |
| $-       | 显示Shell使用的当前选项，与set命令功能相同。                 |
| $?       | 显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。 |



### shell输入/输出重定向(流)

==*需要注意的是文件描述符 0 通常是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）。*==

| 命令            | 说明                                               |
| :-------------- | :------------------------------------------------- |
| command > file  | 将输出重定向到 file。                              |
| command < file  | 将输入重定向到 file。                              |
| command >> file | 将输出以追加的方式重定向到 file。                  |
| n > file        | 将文件描述符为 n 的文件重定向到 file。             |
| n >> file       | 将文件描述符为 n 的文件以追加的方式重定向到 file。 |
| n >& m          | 将输出文件 m 和 n 合并。                           |
| n <& m          | 将输入文件 m 和 n 合并。                           |
| << tag          | 将开始标记 tag 和结束标记 tag 之间的内容作为输入。 |

###### 输出

```
#覆盖内容
echo "wode" > my.txt
#追加内容
echo "wode" >> my.txt
```

###### 输入

```
 wc -l < demo.sh
```



##### 重定向

- 标准输入文件(stdin)：stdin的文件描述符为0，Unix程序默认从stdin读取数据。
- 标准输出文件(stdout)：stdout 的文件描述符为1，Unix程序默认向stdout输出数据。
- 标准错误文件(stderr)：stderr的文件描述符为2，Unix程序会向stderr流中写入错误信息。

```shell
#如果希望 stderr 重定向到 file，可以这样写：
command 2 > file
#如果希望 stderr 追加到 file 文件末尾，可以这样写：
command 2 >> file

```

###### /dev/null 文件

```
如果希望执行某个命令，但又不希望在屏幕上显示输出结果，那么可以将输出重定向到 /dev/null：
$ command > /dev/null
/dev/null 是一个特殊的文件，写入到它的内容都会被丢弃；如果尝试从该文件读取内容，那么什么也读不到。但是 /dev/null 文件非常有用，将命令的输出重定向到它，会起到"禁止输出"的效果。

如果希望屏蔽 stdout 和 stderr，可以这样写：
$ command > /dev/null 2>&1
```



### shell文件包含

```shell
. filename   # 注意点号(.)和文件名中间有一空格

#或

source filename
#test1.sh
#!/bin/bash
# author:zjd

url="https://www.google.com"
#test2.sh
#!/bin/bash
# author:zjd
#使用 . 号来引用test1.sh 文件
. ./test1.sh

# 或者使用以下包含文件代码
# source ./test1.sh

echo "谷歌网址：$url"
```

