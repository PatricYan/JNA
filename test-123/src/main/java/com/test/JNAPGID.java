package com.test;

public class JNAPGID {

    static {
        System.out.println("******* dir: " + System.getProperty("user.dir"));
        // System.load("/Users/mac/working/javaWorking/program/test-123/target/libHelloWorld.jnilib");

        // System.load("/home/www/test123/libHelloWorld.so")
        // System.load("/Users/mac/working/javaWorking/program/test-123/src/main/java/JNI/libTools.jnilib");

    }


    public static native String sayHello(String name);

    /**
     * setsid()
     * creates a new session if the calling process is not a
     * process group leader.  The calling process is the leader of the
     * new session (i.e., its session ID is made the same as its process
     * ID).  The calling process also becomes the process group leader
     * of a new process group in the session (i.e., its process group ID
     * is made the same as its process ID).
     */
    public static native void setsid();


    /**
     * @param pid
     * @param pgid setpgid() sets the PGID of the process specified by pid to pgid.
     *             If pid is zero, then the process ID of the calling process is
     *             used.  If pgid is zero, then the PGID of the process specified by
     *             pid is made the same as its process ID.  If setpgid() is used to
     *             move a process from one process group to another (as is done by
     *             some shells when creating pipelines), both process groups must be
     *             part of the same session (see setsid(2) and credentials(7)).  In
     *             this case, the pgid specifies an existing process group to be
     *             joined and the session ID of that group must match the session ID
     *             of the joining process.
     */
    public static native void setpgid(int pid, int pgid);

}
