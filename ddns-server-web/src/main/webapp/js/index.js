/**
 * Created by Tianya on 2017/10/10.
 */

var body = $('body');
var mainTable = body.find('#mainTable');

var addRecordForm = $('#addRecordForm');

$(document).ready(function () {
    getDnsRecord();
});

function getDnsRecord() {
    sendAjax('dns/getDnsARecord', null, true, 'fillDnsRecord(data)');
}

function fillDnsRecord(data) {
    if (!!data['errorText']) {
        alert(data['errorText']);
        return;
    }
    if (!data['success']) {
        alert(ajaxErrorText);
        return;
    }

    var tbody = mainTable.find('tbody');
    var dnsARecordSet = data['successInfo'];
    $.each(dnsARecordSet, function (index, dnsARecord) {
        var domain = dnsARecord['domain'];
        var ip = dnsARecord['ip'];
        var button = $('<button type="button" class="btn btn-danger btn-sm">删除</button>');
        button.on('click', function () {
            deleteDnsRecord(domain, ip);
        });
        tbody.append($('<tr>')
            .append($('<td>').text('A'))
            .append($('<td>').text(domain))
            .append($('<td>').text(ip))
            .append($('<td>').append(button)));
    });
}

function addDnsRecord() {
    var ajaxData = addRecordForm.serializeArray();
    var now = moment().format(datePattern);
    ajaxData.push({'name': 'token', 'value': now});
    sendAjax('dns/addDnsARecord', ajaxData, true);
}

function deleteDnsRecord(domain, ip) {
    if (!confirm("确定删除吗？")) {
        return;
    }

    var now = moment().format(datePattern);
    var ajaxData = {
        'domain': domain,
        'ip': ip,
        'token': now
    };
    sendAjax('dns/deleteDnsARecord', ajaxData, true);
}