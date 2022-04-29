

import java.text.ParseException;

/**
 * Twitter的分布式自增ID雪花算法
 **/
public class SnowCharId {
    //    public static char[] chars = new char[]{
//            '#','$','0','1','2','3','4','5','6','7','8','9',
//            'A','B','C','D','E','F','G','H','I','J','K','L','M',
//            'N','O','P','Q','R','S','T','U','V','W','Y','Z',
//            'a','b','c','d','e','f','g','h','i','j','k','l','m',
//            'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static char[] char64s = new char[]{
            '#','$','0','1','2','3','4','5','6','7','8','9',
            'a','A','b','B','c','C','d','D','e','E','f','F','g','G',
            'h','H','i','I','j','J','k','K','l','L','m','M',
            'n','N','o','O','p','P','q','Q','r','R','s','S','t','T',
            'u','U','v','V','w','W','x','X','y','Y','z','Z'};

    public static char[] char32s = new char[]{
            '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G','H','J',
            'K','L','M','N','P','Q','R','S','T',
            'U','V','W','Y'
    };

    /**
     * 起始的时间戳
     */
//    private final static long START_STMP =   1L; // 2020-09-01 00:00:00
    private final static long START_STMP = 1600000000000L; // 2020-09-13 20:26:40
//    private final static long START_STMP = 1609459200000L; // 2021-01-01 00:00:00


    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 2;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 3;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private  long datacenterId = 1;  //数据中心
    private  long machineId = 1;     //机器标识
    private  long sequence = 0L; //序列号
    private  long lastStmp = -1L;//上一次时间戳

    public SnowCharId() {
    }

    public SnowCharId(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = System.currentTimeMillis();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
//        System.out.println("lastStmp:"+ lastStmp+"，currStmp="+currStmp);
        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        lastStmp = currStmp;
        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastStmp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }




    public char[] nextChar64Id() {
        char[] chars =char32s;
        char[] temp = new char[16];
        long id = nextId();
        int len = 0;
        while(len<16 && id >0 ){
            temp[len] = chars[(int)(id & 63)];
            id = id >>> 6;
            ++len;
        }
        char[] charId = new char[len];
        for (int i = 0; i < len; i++) {
            charId[len-i-1] = temp[i] ;
        }
        return charId;
    }

    public char[] nextChar32Id() {
        char[] chars =char32s;

        char[] temp = new char[16];
        long id = nextId();
        int len = 0;
        while(len<16 && id >0 ){
            temp[len] = chars[(int)(id & 31)];
            id = id >>> 5;
            ++len;
        }
        char[] charId = new char[len];
        for (int i = 0; i < len; i++) {
            charId[len-i-1] = temp[i] ;
        }
        return charId;
    }


    public static void main(String[] args) throws ParseException {

        testPerf();

    }
    public  static void testPerf(){

        SnowCharId snowCid = new SnowCharId(0, 0);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
//            System.out.println(snowCid.nextChar32Id());
            System.out.println(testPerfA());
        }
        long end = System.currentTimeMillis();

        System.out.println("start:"+ start);
        System.out.println("end:"+ end);
        System.out.println("cost:"+ (end - start));
        System.out.println(snowCid.nextChar32Id());
    }

    public  static char[] testPerfA() {
        return new SnowCharId(0, 0).nextChar32Id();
    }

}
