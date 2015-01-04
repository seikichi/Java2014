#!/bin/bash

CLASS=$1
TRIAL=$2
DEADLOCK=0

for i in $(seq 1 $TRIAL); do
    timeout 3 \
            java -classpath JPL/build/classes/main \
            ch14.ex14_08.$1 > /dev/null \
        || ((++DEADLOCK))
done

printf "deadlock: %d / %d\n" $DEADLOCK $TRIAL
