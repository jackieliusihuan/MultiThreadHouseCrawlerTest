#! /bin/sh
exec /opt/apps/jdk/bin/java -jar /opt/apps/resin/lib/resin.jar $* -conf \
/opt/conf/resin/resin4-periodic-house-crawl-8081.xml
