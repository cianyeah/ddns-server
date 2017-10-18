#!/usr/bin/env bash

# example
# wget -q -P /tmp "http://register.sh" && chmod 777 /tmp/register.sh && bash /tmp/register.sh "domain"

domain=$1
token=$(date "+%Y-%m-%d %H:%M:%S")

curl -s "http://centos.aynait.com:48080/ddns-server/addDnsARecord?domain=${domain}&token=${token}"