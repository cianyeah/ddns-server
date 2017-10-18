#!/usr/bin/env bash

# example
# wget -q -P /tmp "http://centos.aynait.com:48080/ddns-server/file/ddns_register.sh" && chmod 777 /tmp/ddns_register.sh && bash /tmp/ddns_register.sh "domain"

domain=$1
token=$(date "+%Y-%m-%d %H:%M:%S")
token=${token// /%20}

curl -s "http://centos.aynait.com:48080/ddns-server/addDnsARecord?domain=${domain}&token=${token}"
