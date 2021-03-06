package c1;

import java.util.regex.Pattern;

public class RegTheory {
    public static void main(String[] args) {
        //分析正则表达式的底层实现
        String content="1998年12月8日，第二代Java平台的企业版J2EE发布。1999年6月，" +
                "Sun公司发布了第二代Java平台（简称为Java2）的3个版本：J2ME（Java2 Micro Edition，" +
                "Java2平台的微型版），应用于移动、无线及有限资源的环境；J2SE（Java 2 Standard Edition，Java 2平台的标准版）" +
                "，应用于桌面环境；J2EE（Java 2Enterprise Edition，Java 2平台的企业版），应用于基于Java的应用服务器。Java 2平台的发布，" +
                "是Java发展过程中最重要的一个里程碑，标志着Java的应用开始普及。";
        //目标:匹配所有四个数字的字符串
        //1.\\d 表示一个任意的数字
        String regStr="(\\d\\d)(\\d\\d)";
        //2.创建模式对象
        var pattern= Pattern.compile(regStr);
        //3.创建匹配器
        //按照正则表达式的规则去匹配content
        var matcher=pattern.matcher(content);
        //开始匹配
        while(matcher.find()){
            System.out.println("找到:" + matcher.group(0));
            System.out.println("第1组匹配到的值:"+matcher.group(1));
            System.out.println("第2组匹配到的值:"+matcher.group(2));
            //妙哉~
        }
        //问题1:find()方法做了什么工作
        /*
        * matcher.find() 完成的任务
        * 1.根据我们指定的规则，定位满足正则规则的字符串
        * 2.找到后，将子字符串的开始索引和结束索引+1记录到matcher对象的属性int[] groups;
        *   举例，groups[0]=0，groups[1]=4
        * 3.同时记录oldLast的值为子字符串结束索引+1，下次匹配从此开始
        * */
        //matcher.group()方法分析
        /*源代码
        public String group(int group) {
            if (first < 0)
                throw new IllegalStateException("No match found");
            if (group < 0 || group > groupCount())
                throw new IndexOutOfBoundsException("No group " + group);
            if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
                return null;
            return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
        }*/
        /*
        * 1.根据groups[0]=0和groups[1]=4记录的位置，从content开始截取子字符串并返回
        * */
        /*
        * 正则表达式考虑分组
        * What is 正则表达之中的 分组？
        * String regStr="(\\d\\d)(\\d\\d)";
        * 就像上面这样，第一个小括号就表示第一组，第二个小括号就叫第二组
        * 1.根据我们指定的规则，定位满足正则规则的字符串((19)(98))
        * 2.找到后，将子字符串的开始索引和结束索引+1记录到matcher对象的属性int[] groups;
        *   2.1:groups[0]=0,groups[1]=4
        *   2.2:记录第1组匹配到的字符串groups[2]=0,groups[3]=2
        *   2.3:记录第1组匹配到的字符串groups[4]=3,groups[5]=4
        *   2.4:如果有更多的分组，一次类推.......
        * */
        //问题2:为什么结果会被放置在group中
    }
}
