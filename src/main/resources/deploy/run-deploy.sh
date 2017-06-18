#!/bin/bash
base="/opt/fxb"
name="periodic-house-crawl"
java_home="/opt/apps/jdk"
bin_home="/opt/bin"
data_home="/opt/data/fxb"
conf_home="/opt/conf/resin"
log_home="/opt/logs"
deploy="/opt/fxb/periodic-house-crawl"
webapp="$deploy/webapp"
servers=(10.121.37.174:8081)

serverCount=${#servers[@]}
serial_no=`date +%s`
i=0
while [ $i -lt $serverCount ]
do
    server=${servers[$i]}
    echo "deploy $name on $server"
    host=`echo $server | cut -d: -f1`
    port=`echo $server | cut -d: -f2`

    log_home="$log_home/$name-$port"
    shell="$bin_home/resin-$name-$port.sh"
    conf="$conf_home/resin4-$name-$port.xml"

    ssh resin@$host "mkdir -p $data_home/$name"
    ssh resin@$host "chown -R resin $data_home/$name"

    ssh resin@$host "mkdir -p $bin_home"
    scp *.sh resin@$host:$bin_home
    ssh resin@$host "rm -f $bin_home/run-deploy*.sh && chmod +x $bin_home/*.sh"
    ssh resin@$host "mv -f $bin_home/resin-$name.sh $shell"
    ssh resin@$host "chown resin $shell"
    ssh resin@$host "echo $conf >> $shell"

    ssh resin@$host "mkdir -p $conf_home"
    ssh resin@$host "chown -R resin $conf_home"
    scp resin4-$name.xml resin@$host:$conf
    ssh resin@$host "sed -i 's/##port##/$port/' $conf"

    ssh resin@$host "mkdir -p $log_home/bak"
    ssh resin@$host "chown -R resin $log_home"

    ssh resin@$host "$shell stop" || echo "$shell is not running"

    if [ `ssh resin@$host "if [ -f $deploy/$name-$serial_no.war ];then echo 1;else echo 0; fi"` -eq 0 ] ; then
        ssh resin@$host "mkdir -p $webapp"
        ssh resin@$host "chown -R resin $deploy"

        scp artifacts/$name.war resin@$host:$deploy
        ssh resin@$host "cd $webapp && rm -rf * && $java_home/bin/jar xf $deploy/$name.war"

        ssh resin@$host "cp $deploy/$name.war $deploy/$name-$serial_no.war"
    fi

    while [ `ssh resin@$host "ps -ef | grep $conf | grep -v grep | wc -l"` -gt 0 ]
    do
	    echo "waiting for $shell resin stop"
    	sleep 1
    done

    ssh resin@$host "$shell start"
    i=$((i+1))

done
echo "all done."
