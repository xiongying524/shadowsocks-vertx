package shadowsocks.vertxio;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 单例类
 * IV 缓存，防止 Client 重放攻击
 */
public class ClientIvCache {

    private static Logger log = LoggerFactory.getLogger(ClientIvCache.class);

    private ClientIvCache(){}
    private static ClientIvCache single = new ClientIvCache();
    public static ClientIvCache ins(){
        return single;
    }

    private static final int MAX = 1000;

    private final Object lock = new Object();

    private LinkedList<byte[]> ivList = new LinkedList<>();

    /**
     * 添加一个元素，成功返回true，失败返回false
     */
    public boolean add(byte [] iv) {
        synchronized (lock) {
            if (existNow(iv)) {// 已经存在
                return false;
            }

            // 添加到末尾
            ivList.addLast(iv);

            if (ivList.size() > MAX) {// 如果超过 MAX
                // 删除第一个
                 byte[] pollFirst = ivList.pollFirst();
                log.info("ivList.pollFirst   " + Arrays.toString(pollFirst));
            }

            return true;
        }
    }


    /**
     * 检查是否存在此 IV
     */
    private boolean existNow (byte[] iv) {
        // 不能用 ivList.contains() 方法，数组的equals有问题！
        for (byte[] temp : ivList) {
            if (Arrays.equals(temp,iv)) {
                return true;
            }
        }

        return false;
    }

}
