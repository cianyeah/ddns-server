#!/usr/bin/env bash

# example
# rm -f /tmp/ddns_register.sh && wget -q -P /tmp "http://centos.aynait.com:48080/ddns-server/file/ddns_register.sh" && chmod 777 /tmp/ddns_register.sh && bash /tmp/ddns_register.sh "domain" &

domain=$1
token=$(date "+%Y-%m-%d %H:%M:%S")
token=${token// /%20}

curl -s -i -H "Accept: application/json" "http://centos.aynait.com:48080/ddns-server/addDnsARecord?domain=${domain}&token=${token}"
