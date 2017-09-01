#!/bin/bash

rm -rfv sqlite_sql.tmp
while read line
do
	line=${line// comment*,/,}
	line=${line// comment*;/;}
	line=${line// default*;/;}
	echo $line >> sqlite_sql.tmp
done < platform.sql

while read line
do
	echo $line >> sqlite_sql.tmp
done < platform_demo.sql

sqlite3 phoenix.db -init sqlite_sql.tmp
rm -rfv sqlite_sql.tmp
