# JNA/JNI
When we need to call the c/c++ program, it is JNA/JNI that we need to complete.


## Kill the progresses of the same PGID
You can kill the progresses, parent, children and grandchildren which are of the same PGID

```sh
PGID=`ps j -A | grep 'xxxxx' | grep -v 'grep' | awk '{print  $3}'`
# safety kill application which could hook the sign to deal something
kill -SIGTERM -- -${PGID}

# just kill all the progresses
kill -9 -${pid}
```
* set the progress [PGID](https://man7.org/linux/man-pages/man2/setpgid.2.html) and [SID](https://man7.org/linux/man-pages/man2/setsid.2.html)
* you can load the JNA/JNI from the jar, and don't need to set it in the classpath
