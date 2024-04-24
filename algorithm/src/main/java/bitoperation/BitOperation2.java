package bitoperation;

/**
 * 递归转换yy分钟为xx小时xx分钟
 * Test
 */
public class BitOperation2 {

    public static void main(String[] args) {
        System.out.println(convert(2400));
        System.out.println(convert(61));
        
        // 第二种方式更容易理解一些：
        System.out.println(convert2(2400));
        System.out.println(convert2(61));
    }

    static String convert(int waitTime){
        int rtn = convert(waitTime, 0);
        return String.format("%02d%02d", rtn >> 16, rtn & ((1 << 16) -1));
    }

    static int convert(int waitTime, int hours){
        // 不足60分钟，则放置到低16位
        if(waitTime < 60){
            return (hours << 16) | waitTime;
        }

        // 代码走到这里，说明超过了1小时，那么先+上。
        hours += 1;
        // m-60分钟，后再继续算。
        return convert(waitTime - 60, hours);
    }

    static String convert2(int waitTime){
        int rtn = convert2(waitTime, 0);
        return String.format("%02d%02d", rtn / 100, rtn % 100);
    }

    static int convert2(int waitTime, int hours){
        // 不足60分钟，则放置到低16位
        if(waitTime < 60){
            return (hours * 100) + waitTime;
        }

        // 代码走到这里，说明超过了1小时，那么先+上。
        hours += 1;
        // m-60分钟，后再继续算。
        return convert2(waitTime - 60, hours);
    }
}