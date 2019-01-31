#!/bin/sh

domain=$1
token=$(date "+%Y-%m-%d %H:%M:%S")
token=${token// /%20}

curl -s -i -H "Accept: application/json" -u admin:/*Tianya*/ "http://ddns-server.aynait.com:48080/ddns-server/addDnsARecord?domain=${domain}&token=${token}"
