#!/bin/bash
cd src/main/groovy

if [ $# -lt 1 ]
  then
    groovy Container.groovy -h
    exit
fi

case "$1" in
  -p) groovy Container.groovy -p $2;;
   *) groovy Container.groovy $1;;
esac
