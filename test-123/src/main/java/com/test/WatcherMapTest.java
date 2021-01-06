package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.*;

public class WatcherMapTest {
    private static Logger logger = LoggerFactory.getLogger(WatcherMapTest.class);

    public void abTest() {
        // Data data = new Data();
        // data.hashSet();
        int confCmd = ByteBuffer.wrap("conf".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int consCmd = ByteBuffer.wrap("cons".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int crstCmd = ByteBuffer.wrap("crst".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int dirsCmd = ByteBuffer.wrap("dirs".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int dumpCmd = ByteBuffer.wrap("dump".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int enviCmd = ByteBuffer.wrap("envi".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int getTraceMaskCmd = ByteBuffer.wrap("gtmk".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int ruokCmd = ByteBuffer.wrap("ruok".getBytes()).getInt();
        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int setTraceMaskCmd = ByteBuffer.wrap("stmk".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int srvrCmd = ByteBuffer.wrap("srvr".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int srstCmd = ByteBuffer.wrap("srst".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int statCmd = ByteBuffer.wrap("stat".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int wchcCmd = ByteBuffer.wrap("wchc".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int wchpCmd = ByteBuffer.wrap("wchp".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int wchsCmd = ByteBuffer.wrap("wchs".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int mntrCmd = ByteBuffer.wrap("mntr".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int isroCmd = ByteBuffer.wrap("isro".getBytes()).getInt();

        /*
         * See <a href="{@docRoot}/../../../docs/zookeeperAdmin.html#sc_zkCommands">
         * Zk Admin</a>. this link is for all the commands.
         */
        int hashCmd = ByteBuffer.wrap("hash".getBytes()).getInt();


        System.out.printf("*********** byte: %s, int: %s\n", ByteBuffer.wrap("hash".getBytes()), hashCmd);
    }

    public void hashSet() {
        Map<String, Set<String>> dataHashSet = new HashMap<String, Set<String>>();
        Set<String> one = new HashSet<String>();
        one.add("abcd-test");
        dataHashSet.put("test", one);

        Set<String> two = dataHashSet.get("test");
        System.out.printf("********** one is:%s\n", one.toString());
        System.out.printf("********** two is:%s\n", two.toString());
        System.out.printf("********** dataHashSet is:%s\n", dataHashSet.toString());

        two.add("123-test");
        System.out.printf("********** one is:%s\n", one.toString());
        System.out.printf("********** two is:%s\n", two.toString());
        System.out.printf("********** dataHashSet is:%s\n", dataHashSet.toString());

        List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("456");
        list.add("789");
        list.add("acv");
        System.out.printf("********** list is:%s\n", list);

        logger.info("************* all list is aaa ok:{}", list);

    }

    public void mapTest() {
        GTest gTest0 = getOne("1", "2");
        GTest gTest1 = getOne("1", "2");
        GTest gTest2 = getOne("1", "2");
        GTest gTest3 = getOne("1", "2");
        GTest gTest4 = getOne("1", "2");

        Map<GTest, GTest> map = new HashMap<GTest, GTest>();
        map.put(gTest0, gTest0);
        map.put(gTest1, gTest1);
        map.put(gTest2, gTest2);
        map.put(gTest3, gTest3);
        map.put(gTest4, gTest4);

        System.out.printf("*********** all size: %d\n data: %s\n", map.size(), map.get(getOne("1", "2")) == null ? "null" : "aaa");
    }

    public GTest getOne(String m, String n) {
        GTest gTest = new GTest();
        gTest.m = m;
        gTest.n = n;
        return gTest;
    }

    public class GTest {
        String m;
        String n;
    }
}
