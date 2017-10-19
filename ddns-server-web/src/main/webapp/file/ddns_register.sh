#!/usr/bin/env bash

domain=$1
token=$(date "+%Y-%m-%d %H:%M:%S")
token=${token// /%20}

curl -s -i -H "Accept: application/json" "http://centos.aynait.com:48080/ddns-server/addDnsARecord?domain=${domain}&token=${token}"
