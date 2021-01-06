#! /bin/bash

kill -l | grep SIGTERM

ps j -A | grep 'data-1.0.0-SNAPSHOT.jar' | grep -v 'grep'

PGID=`ps j -A | grep 'data-1.0.0-SNAPSHOT.jar' | grep -v 'grep' | awk '{print  $3}'`

echo "1 PGID is: ${PGID}"

# PGID=$((10#${PGID} * -1))
PGID=-$PGID

echo "2 PGID is: $PGID"


kill -SIGTERM  -- $PGID

ps j -A | grep 'data-1.0.0-SNAPSHOT.jar' | grep -v 'grep'

echo "end ..."